package com.edutie.backend.services.studyprogram.creators.lesson;

import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.services.common.ServiceBase;
import com.edutie.backend.services.common.logging.ExternalFailureLog;
import com.edutie.backend.services.studyprogram.initializers.segment.RootSegmentInitializationDetails;
import com.edutie.backend.services.studyprogram.initializers.segment.RootSegmentInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class LessonCreatorImplementation extends ServiceBase implements LessonCreator {
    private final LessonPersistence lessonPersistence;
    private final RootSegmentInitializer rootSegmentInitializer;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public WrapperResult<Lesson> createLesson(LessonCreationDetails creationDetails) {
        if (creationDetails.nextLesson() == null)
            LOGGER.info("Creating a lesson as a lesson tree leaf. Previous elem id: {}", creationDetails.previousLesson().getId());
        else
            LOGGER.info("Creating a lesson in between 2 lessons. Previous elem id: {}, next elem id: {}", creationDetails.previousLesson().getId(), creationDetails.nextLesson().getId());

        Lesson lesson = Lesson.create(creationDetails.educator(), creationDetails.previousLesson());
        lesson.setName(creationDetails.name());
        lesson.setDescription(creationDetails.description() != null ? creationDetails.description() : "");
        // add next elem to the lesson and save it, realigning the lesson tree
        if (creationDetails.nextLesson() != null) {
            Result addElemResult = lesson.addNextElement(creationDetails.nextLesson());
            if (addElemResult.isFailure())
                return addElemResult.map(() -> null);
        }
        Result lessonSaveResult = lessonPersistence.save(lesson);
        if (lessonSaveResult.isFailure()) {
            return ExternalFailureLog.persistenceFailure(lessonSaveResult, LOGGER).map(() -> null);
        }
        // that is a workaround since current solution (prev comment) does not work.
        //TODO: delete once #98 is resolved
        if (creationDetails.nextLesson() != null) {
            Lesson nextLesson = creationDetails.nextLesson();
            nextLesson.setPreviousElement(lesson);
            lessonPersistence.save(nextLesson);
        }
        WrapperResult<Segment> segmentInitializationResult = rootSegmentInitializer.initializeSegment(
                new RootSegmentInitializationDetails().educator(creationDetails.educator())
                        .lesson(lesson).name("The first segment")
        );
        if (segmentInitializationResult.isFailure())
            return segmentInitializationResult.map(o -> null);
        return WrapperResult.successWrapper(lesson);
    }
}

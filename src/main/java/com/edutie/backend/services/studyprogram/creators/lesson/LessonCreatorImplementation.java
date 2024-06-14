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

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonCreatorImplementation extends ServiceBase implements LessonCreator {
    private final LessonPersistence lessonPersistence;
    private final RootSegmentInitializer rootSegmentInitializer;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public WrapperResult<Lesson> createLesson(LessonCreationDetails creationDetails) {
        Lesson lesson = Lesson.create(creationDetails.educator(), creationDetails.previousLesson());
        lesson.setName(creationDetails.name());
        lesson.setDescription(creationDetails.description() != null ? creationDetails.description() : "");
        if (creationDetails.nextLesson() != null)
            lesson.addNextElement(creationDetails.nextLesson());
        Result lessonSaveResult = lessonPersistence.save(lesson);
        if (lessonSaveResult.isFailure()) {
            return ExternalFailureLog.persistenceFailure(lessonSaveResult, LOGGER).map(() -> null);
        }
        WrapperResult<Segment> segmentInitializationResult = rootSegmentInitializer.initializeSegment(
                new RootSegmentInitializationDetails().educator(creationDetails.educator())
                        .lesson(lesson).name("The first segment")
        );
        if (segmentInitializationResult.isFailure())
            return segmentInitializationResult.map(o -> null);
        return WrapperResult.successWrapper(lesson);
    }

    private WrapperResult<Lesson> attachNextLessons(Lesson mainLesson, List<Lesson> nextLessons) {
        for (Lesson nextLesson : nextLessons)
            mainLesson.addNextElement(nextLesson);
        Result lessonSaveResult = lessonPersistence.save(mainLesson);
        if (lessonSaveResult.isFailure()) {
            return ExternalFailureLog.persistenceFailure(lessonSaveResult, LOGGER).map(() -> null);
        }
        return WrapperResult.successWrapper(mainLesson);
    }
}

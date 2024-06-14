package com.edutie.backend.services.studyprogram.initializers.lesson;

import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.services.common.ServiceBase;
import com.edutie.backend.services.common.logging.ExternalFailureLog;
import com.edutie.backend.services.studyprogram.initializers.segment.RootSegmentInitializationDetails;
import com.edutie.backend.services.studyprogram.initializers.segment.RootSegmentInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class RootLessonInitializerImplementation extends ServiceBase implements RootLessonInitializer {
    private final LessonPersistence lessonPersistence;
    private final RootSegmentInitializer rootSegmentInitializer;
    @Override
    public WrapperResult<Lesson> initializeLesson(RootLessonInitializationDetails initializationDetails) {
        Lesson lesson = Lesson.create(initializationDetails.educator(), initializationDetails.course());
        lesson.setName(initializationDetails.name());
        lesson.setDescription(initializationDetails.description());
        Result lessonSaveResult = lessonPersistence.save(lesson);
        if (lessonSaveResult.isFailure()) {
            return ExternalFailureLog.persistenceFailure(lessonSaveResult, LOGGER, "Root segment saving error").map(()->null);
        }
        WrapperResult<Segment> segmentInitializationResult = rootSegmentInitializer.initializeSegment(
                new RootSegmentInitializationDetails().educator(initializationDetails.educator())
                        .lesson(lesson).name("The first segment")
        );
        if (segmentInitializationResult.isFailure())
            return segmentInitializationResult.map(o -> null);
        return WrapperResult.successWrapper(lesson);
    }
}

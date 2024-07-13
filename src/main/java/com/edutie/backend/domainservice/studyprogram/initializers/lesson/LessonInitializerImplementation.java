package com.edutie.backend.domainservice.studyprogram.initializers.lesson;

import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.backend.domainservice.common.ServiceBase;
import com.edutie.backend.domainservice.common.logging.ExternalFailureLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;

@Component
@RequiredArgsConstructor
public class LessonInitializerImplementation extends ServiceBase implements LessonInitializer {
    private final SegmentPersistence segmentPersistence;

    @Override
    public Result initializeLesson(Lesson lesson) {
        LOGGER.info("Initializing lesson with an initial root segment...");
        Segment initialSegment = Segment.create(lesson.getAuthorEducator(), lesson);
        initialSegment.setName("Initial segment");
        initialSegment.setSnippetDescription("Modify this segment as you like.");
        Result segmentSaveResult = segmentPersistence.save(initialSegment);
        if (segmentSaveResult.isFailure())
            return ExternalFailureLog.persistenceFailure(segmentSaveResult, LOGGER);
        LOGGER.info("Lesson initialization success.");
        return Result.success();
    }
}

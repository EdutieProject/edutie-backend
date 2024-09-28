package com.edutie.backend.domainservice.studyprogram.initializers.lesson;

import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.backend.domainservice.common.ServiceBase;
import com.edutie.backend.domainservice.common.logging.ExternalFailureLog;
import validation.Result;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class LessonInitializerImplementation extends ServiceBase implements LessonInitializer {
	private final SegmentPersistence segmentPersistence;

	@Override
	public Result initializeLesson(Lesson lesson) {
		log.info("Initializing lesson with an initial root segment...");
		Segment initialSegment = Segment.create(lesson.getAuthorEducator(), lesson);
		initialSegment.setName("Initial segment");
		initialSegment.setSnippetDescription("Modify this segment as you like.");
		Result segmentSaveResult = segmentPersistence.save(initialSegment);
		if (segmentSaveResult.isFailure())
			return ExternalFailureLog.persistenceFailure(segmentSaveResult, log);
		log.info("Lesson initialization success.");
		return Result.success();
	}
}

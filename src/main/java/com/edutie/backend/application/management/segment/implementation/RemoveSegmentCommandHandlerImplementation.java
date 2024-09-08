package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.RemoveSegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.RemoveSegmentCommand;
import com.edutie.backend.domain.education.EducationError;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import validation.Result;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import lombok.*;
import lombok.extern.slf4j.*;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class RemoveSegmentCommandHandlerImplementation extends HandlerBase implements RemoveSegmentCommandHandler {
	private final SegmentPersistence segmentPersistence;
	private final EducatorPersistence educatorPersistence;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Result handle(RemoveSegmentCommand command) {
		Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
		Segment segment = segmentPersistence.getById(command.segmentId()).getValue();
		if (!educator.isAuthorOf(segment)) {
			log.info("Educator has insufficient permissions to remove this segment");
			return Result.failure(EducationError.educatorMustBeAuthorError(Segment.class));
		}
		Segment previousLesson = segment.getPreviousElement();
		Set<Segment> nextSegments = segment.getNextElements();
		for (Segment nextSegment: nextSegments) {
			nextSegment.setPreviousElement(previousLesson);
			segmentPersistence.save(nextSegment);
		}
		segmentPersistence.remove(segment);
		return Result.success();
	}
}

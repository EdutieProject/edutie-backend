package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.CreateSegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.CreateSegmentCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.education.exercisetype.persistence.ExerciseTypePersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import lombok.*;
import lombok.extern.slf4j.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateSegmentCommandHandlerImplementation extends HandlerBase implements CreateSegmentCommandHandler {
	private final SegmentPersistence segmentPersistence;
	private final EducatorPersistence educatorPersistence;
	private final ExerciseTypePersistence exerciseTypePersistence;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public WrapperResult<Segment> handle(CreateSegmentCommand command) {
		log.info("Creating segment by user of id {} with previous lesson of id {}", command.educatorUserId().identifierValue(), command.previousSegmentId().identifierValue());
		Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
		Segment previousSegment = segmentPersistence.getById(command.previousSegmentId()).getValue();
		Segment segment = Segment.create(educator, previousSegment);
		segment.setName(command.segmentName());
		segment.setSnippetDescription(command.snippetDescription() != null ? command.snippetDescription() : "");
		segmentPersistence.save(segment).throwIfFailure();
		if (command.nextSegmentId() == null) {
			log.info("No next segment Id specified - New segment of id {} created as a segment tree leaf.", segment.getId());
			return WrapperResult.successWrapper(segment);
		}
		Segment nextSegment = segmentPersistence.getById(command.nextSegmentId()).getValue();
		nextSegment.setPreviousElement(segment);
		segment.addNextElement(nextSegment);
		segmentPersistence.save(nextSegment).throwIfFailure();
		log.info("New segment of id {} created in between 2 other segments.", segment.getId());
		return WrapperResult.successWrapper(segment);
	}
}

package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.ModifySegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.ModifySegmentCommand;
import com.edutie.backend.domain.education.EducationError;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class ModifySegmentCommandHandlerImplementation extends HandlerBase implements ModifySegmentCommandHandler {
    private final SegmentPersistence segmentPersistence;
    private final EducatorPersistence educatorPersistence;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public WrapperResult<Segment> handle(ModifySegmentCommand command) {
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        Segment segment = segmentPersistence.getById(command.segmentId()).getValue();
        if (!educator.isAuthorOf(segment)) {
            LOGGER.info("Educator has insufficient permissions to modify the segment");
            return Result.failureWrapper(EducationError.educatorMustBeAuthorError(Segment.class));
        }
        if (command.segmentName() != null) {
            segment.setName(command.segmentName());
        }
        if (command.segmentSnippetDescription() != null) {
            segment.setSnippetDescription(command.segmentSnippetDescription());
        }
        if (command.previousSegmentId() != null) {
            Segment previousSegment = segmentPersistence.getById(command.previousSegmentId()).getValue();
            segment.setPreviousElement(previousSegment);
        }
        for (SegmentId id : command.nextSegmentIds()) {
            Segment nextSegment = segmentPersistence.getById(id).getValue();
            segment.addNextElement(nextSegment);
        }
        segmentPersistence.save(segment);
        return WrapperResult.successWrapper(segment);
    }
}

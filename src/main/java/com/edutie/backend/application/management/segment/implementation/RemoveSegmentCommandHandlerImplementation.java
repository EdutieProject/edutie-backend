package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.RemoveSegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.RemoveSegmentCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.EducationError;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import validation.Result;
import validation.WrapperResult;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class RemoveSegmentCommandHandlerImplementation extends HandlerBase implements RemoveSegmentCommandHandler {
    private final SegmentPersistence segmentPersistence;
    private final EducatorPersistence educatorPersistence;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Result handle(RemoveSegmentCommand command) {
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        WrapperResult<Segment> segmentWrapperResult = segmentPersistence.getById(command.segmentId());
        if (segmentWrapperResult.isFailure())
            return segmentWrapperResult;
        Segment segment = segmentWrapperResult.getValue();
        if (!segment.getAuthorEducator().equals(educator)) {
            LOGGER.info("Educator has insufficient permissions to remove this segment");
            return Result.failure(EducationError.educatorMustBeAuthorError(Segment.class));
        }
        Segment previousLesson = segment.getPreviousElement();
        Set<Segment> nextSegments = segment.getNextElements();
        for (Segment nextSegment : nextSegments) {
            nextSegment.setPreviousElement(previousLesson);
            segmentPersistence.save(nextSegment);
        }
        segmentPersistence.remove(segment);
        return Result.success();
    }
}

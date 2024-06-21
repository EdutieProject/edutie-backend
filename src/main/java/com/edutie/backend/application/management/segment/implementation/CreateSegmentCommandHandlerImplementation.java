package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.CreateSegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.CreateSegmentCommand;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.backend.domain.education.exercisetype.persistence.ExerciseTypePersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.backend.services.common.logging.ExternalFailureLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateSegmentCommandHandlerImplementation extends HandlerBase implements CreateSegmentCommandHandler {
    private final SegmentPersistence segmentPersistence;
    private final EducatorPersistence educatorPersistence;
    private final ExerciseTypePersistence exerciseTypePersistence;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public WrapperResult<Segment> handle(CreateSegmentCommand command) {
        LOGGER.info("Creating segment by user of id {} with previous lesson of id {}",
                command.educatorUserId().identifierValue(),
                command.previousSegmentId().identifierValue());
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        WrapperResult<Segment> previousSegmentResult = segmentPersistence.getById(command.previousSegmentId());
        if (previousSegmentResult.isFailure()) {
            return ExternalFailureLog.persistenceFailure(previousSegmentResult, LOGGER);
        }
        Segment segment = Segment.create(educator, previousSegmentResult.getValue());
        segment.setName(command.segmentName());
        segment.setSnippetDescription(command.snippetDescription() != null ? command.snippetDescription() : "");
        segment.setTheoryDescription(PromptFragment.of(command.segmentTheoryDescription()));
        segment.setExerciseDescription(PromptFragment.of(command.segmentExerciseDescription()));
        if (command.exerciseTypeId() != null) {
            WrapperResult<ExerciseType> exerciseTypeWrapperResult = exerciseTypePersistence.getById(command.exerciseTypeId());
            if (exerciseTypeWrapperResult.isFailure())
                return ExternalFailureLog.persistenceFailure(exerciseTypeWrapperResult, LOGGER).map(o -> null);
            segment.setExerciseType(exerciseTypeWrapperResult.getValue());
        }
        segmentPersistence.save(segment);
        if (command.nextSegmentId() == null) {
            LOGGER.info("No next segment Id specified - New segment of id {} created as a segment tree leaf.", segment.getId());
            return WrapperResult.successWrapper(segment);
        }
        WrapperResult<Segment> nextSegmentWrapperResult = segmentPersistence.getById(command.nextSegmentId());
        if (nextSegmentWrapperResult.isFailure()) {
            return ExternalFailureLog.persistenceFailure(nextSegmentWrapperResult, LOGGER);
        }
        Segment nextSegment = nextSegmentWrapperResult.getValue();
        nextSegment.setPreviousElement(segment);
        Result nextSegmentSaveResult = segmentPersistence.save(nextSegment);
        if (nextSegmentSaveResult.isFailure())
            return ExternalFailureLog.persistenceFailure(nextSegmentWrapperResult, LOGGER);
        segment.addNextElement(nextSegment);
        LOGGER.info("New segment of id {} created in between 2 other segments.", segment.getId());
        return WrapperResult.successWrapper(segment);
    }
}

package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.ModifySegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.ModifySegmentCommand;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.backend.domain.education.exercisetype.persistence.ExerciseTypePersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class ModifySegmentCommandHandlerImplementation extends HandlerBase implements ModifySegmentCommandHandler {
    private final SegmentPersistence segmentPersistence;
    private final EducatorPersistence educatorPersistence;
    private final ExerciseTypePersistence exerciseTypePersistence;

    @Override
    public WrapperResult<Segment> handle(ModifySegmentCommand command) {
        Educator educator = educatorPersistence.getByUserId(command.educatorUserId());
        WrapperResult<Segment> segmentWrapperResult = segmentPersistence.getById(command.segmentId());
        if (segmentWrapperResult.isFailure())
            return segmentWrapperResult;
        if (!segmentWrapperResult.getValue().getEducator().equals(educator))
            return Result.failureWrapper(new Error("SEGMENT-1", "To modify segment you must be a creator of it."));

        Segment segment = segmentWrapperResult.getValue();
        if (command.segmentName() != null) {
            segment.setName(command.segmentName());
        }
        if (command.segmentSnippetDescription() != null) {
            segment.setSnippetDescription(command.segmentSnippetDescription());
        }
        if (command.segmentTheoryDescription() != null) {
            segment.setTheoryDescription(PromptFragment.of(command.segmentTheoryDescription()));
        }
        if (command.segmentExerciseDescription() != null) {
            segment.setExerciseDescription(PromptFragment.of(command.segmentExerciseDescription()));
        }
        if (command.exerciseTypeId() != null) {
            WrapperResult<ExerciseType> exerciseTypeWrapperResult = exerciseTypePersistence.getById(command.exerciseTypeId());
            if (exerciseTypeWrapperResult.isFailure())
                return exerciseTypeWrapperResult.map(o -> null);
            segment.setExerciseType(exerciseTypeWrapperResult.getValue());
        }
        if (command.previousSegmentId() != null) {
            WrapperResult<Segment> previousSegmentWrapperResult = segmentPersistence.getById(command.previousSegmentId());
            if (previousSegmentWrapperResult.isFailure())
                return previousSegmentWrapperResult;
            segment.setPreviousElement(previousSegmentWrapperResult.getValue());
        }
        for (SegmentId id : command.nextSegmentIds()) {
            WrapperResult<Segment> nextSegmentWrapper = segmentPersistence.getById(id);
            if (nextSegmentWrapper.isFailure())
                return nextSegmentWrapper;
            segment.addNextElement(nextSegmentWrapper.getValue());
        }
        segmentPersistence.save(segment);
        return WrapperResult.successWrapper(segment);
    }
}

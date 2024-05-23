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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateSegmentCommandHandlerImplementation extends HandlerBase implements CreateSegmentCommandHandler {
    private final SegmentPersistence segmentPersistence;
    private final EducatorPersistence educatorPersistence;
    private final ExerciseTypePersistence exerciseTypePersistence;

    @Override
    public WrapperResult<Segment> handle(CreateSegmentCommand command) {
        Educator educator = educatorPersistence.getByUserId(command.educatorUserId());
        WrapperResult<Segment> previousSegmentWrapperResult = segmentPersistence.getById(command.previousSegmentId());
        if (previousSegmentWrapperResult.isFailure())
            return previousSegmentWrapperResult;
        Segment segment = Segment.create(educator, previousSegmentWrapperResult.getValue().getLesson());
        segment.setPreviousElement(previousSegmentWrapperResult.getValue());
        WrapperResult<Segment> nextSegmentWrapperResult = segmentPersistence.getById(command.nextSegmentId());
        if (nextSegmentWrapperResult.isSuccess()) {
            segment.addNextElement(nextSegmentWrapperResult.getValue());
        }
        segment.setName(command.segmentName());
        segment.setSnippetDescription(command.snippetDescription() != null ? command.snippetDescription() : "");
        segment.setTheoryDescription(PromptFragment.of(command.segmentTheoryDescription()));
        segment.setExerciseDescription(PromptFragment.of(command.segmentExerciseDescription()));
        if (command.exerciseTypeId() != null) {
            WrapperResult<ExerciseType> exerciseTypeWrapperResult = exerciseTypePersistence.getById(command.exerciseTypeId());
            if (exerciseTypeWrapperResult.isFailure())
                return exerciseTypeWrapperResult.map(o -> null);
            segment.setExerciseType(exerciseTypeWrapperResult.getValue());
        }
        segmentPersistence.save(segment);
        return WrapperResult.successWrapper(segment);
    }
}

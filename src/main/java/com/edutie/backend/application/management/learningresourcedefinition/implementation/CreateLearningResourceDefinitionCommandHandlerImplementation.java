package com.edutie.backend.application.management.learningresourcedefinition.implementation;

import com.edutie.backend.application.management.learningresourcedefinition.CreateLearningResourceDefinitionCommandHandler;
import com.edutie.backend.application.management.learningresourcedefinition.commands.CreateLearningResourceDefinitionCommand;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CreateLearningResourceDefinitionCommandHandlerImplementation implements CreateLearningResourceDefinitionCommandHandler {
    private final EducatorPersistence educatorPersistence;
    private final LearningRequirementPersistence learningRequirementPersistence;
    private final LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    private final SegmentPersistence segmentPersistence;

    @Override
    public WrapperResult<StaticLearningResourceDefinition> handle(CreateLearningResourceDefinitionCommand command) {
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        StaticLearningResourceDefinition staticLearningResourceDefinition = StaticLearningResourceDefinition.create(
                educator,
                PromptFragment.of(command.theoryDescription()),
                PromptFragment.of(command.exerciseDescription()),
                command.learningRequirementIds().stream().map(o -> learningRequirementPersistence.getById(o).getValue()).collect(Collectors.toSet())
        );
        learningResourceDefinitionPersistence.save(staticLearningResourceDefinition).throwIfFailure();
        if (command.segmentId() != null) {
            Segment segment = segmentPersistence.getById(command.segmentId()).getValue();
            segment.setLearningResourceDefinitionId(staticLearningResourceDefinition.getId());
            segmentPersistence.save(segment).throwIfFailure();
        }
        return WrapperResult.successWrapper(staticLearningResourceDefinition);
    }
}

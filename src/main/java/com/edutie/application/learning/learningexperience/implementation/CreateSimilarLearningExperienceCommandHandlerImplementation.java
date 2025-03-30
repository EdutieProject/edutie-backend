package com.edutie.application.learning.learningexperience.implementation;

import com.edutie.application.learning.learningexperience.CreateSimilarLearningExperienceCommandHandler;
import com.edutie.application.learning.learningexperience.command.CreateSimilarLearningExperienceCommand;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.domain.core.education.learningsubject.service.DynamicRequirementSelectionService;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.domain.core.learning.learningexperience.service.LearningExperiencePersonalizationService;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.persistence.StudentPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateSimilarLearningExperienceCommandHandlerImplementation implements CreateSimilarLearningExperienceCommandHandler {
    private final LearningExperiencePersistence learningExperiencePersistence;
    private final LearningSubjectPersistence learningSubjectPersistence;
    private final StudentPersistence studentPersistence;
    private final DynamicRequirementSelectionService dynamicRequirementSelectionService;
    private final LearningExperiencePersonalizationService learningExperiencePersonalizationService;

    @Override
    public WrapperResult<LearningExperience<?>> handle(CreateSimilarLearningExperienceCommand command) {
        log.info("Creating similar learning experience to previous experience of id {} for user of id {}", command.learningExperienceId(), command.studentUserId());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        LearningExperience<?> previousLearningExperience = learningExperiencePersistence.getById(command.learningExperienceId()).getValue();
        // Not viable for multi requirement setups
        ElementalRequirementId elementalRequirementId = previousLearningExperience.getRequirements().stream().findFirst().get().getElementalRequirementId();
        LearningSubject learningSubject = learningSubjectPersistence.getLearningSubjectByElementalRequirementId(elementalRequirementId).getValue();
        ElementalRequirement chosenRequirement = learningSubject.chooseLearningSubjectRequirement(elementalRequirementId, dynamicRequirementSelectionService);
        LearningExperience<?> learningExperience = learningExperiencePersonalizationService.createPersonalized(student, learningSubject.getKnowledgeOrigin(), chosenRequirement).getValue();
        learningExperiencePersistence.save(learningExperience).throwIfFailure();
        return WrapperResult.successWrapper(learningExperience);
    }
}

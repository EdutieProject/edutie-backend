package com.edutie.application.learning.learningexperience.implementation;

import com.edutie.application.learning.learningexperience.CreateLearningExperienceCommandHandler;
import com.edutie.application.learning.learningexperience.command.CreateLearningExperienceCommand;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
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
public class CreateLearningExperienceCommandHandlerImplementation implements CreateLearningExperienceCommandHandler {
    private final LearningExperiencePersistence learningExperiencePersistence;
    private final LearningSubjectPersistence learningSubjectPersistence;
    private final StudentPersistence studentPersistence;
    private final DynamicRequirementSelectionService dynamicRequirementSelectionService;
    private final LearningExperiencePersonalizationService learningExperiencePersonalizationService;

    @Override
    public WrapperResult<LearningExperience<?>> handle(CreateLearningExperienceCommand command) {
        log.info("Creating learning experience for user of id {} using learning subject {} and elemental req {}", command.studentUserId(), command.learningSubjectId(), command.elementalRequirementId());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        LearningSubject learningSubject = learningSubjectPersistence.getById(command.learningSubjectId()).getValue();
        ElementalRequirement chosenRequirement = learningSubject.chooseLearningSubjectRequirement(command.elementalRequirementId(), dynamicRequirementSelectionService);
        LearningExperience<?> learningExperience = learningExperiencePersonalizationService.createPersonalized(student, learningSubject.getKnowledgeOrigin(), chosenRequirement).getValue();
        learningExperiencePersistence.save(learningExperience).throwIfFailure();
        return WrapperResult.successWrapper(learningExperience);
    }
}

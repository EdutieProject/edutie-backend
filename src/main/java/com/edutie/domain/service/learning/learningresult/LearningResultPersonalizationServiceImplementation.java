package com.edutie.domain.service.learning.learningresult;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.LearningEvaluation;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmission;
import com.edutie.domain.core.learning.learningresult.service.LearningResultPersonalizationService;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.GetKnowledgeContextService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.GetKnowledgeContextSchema;
import com.edutie.infrastructure.llm.learningresult.LearningEvaluationGenerationService;
import com.edutie.infrastructure.llm.learningresult.schema.LearningEvaluationGenerationSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class LearningResultPersonalizationServiceImplementation implements LearningResultPersonalizationService {
    private final LearningSubjectPersistence learningSubjectPersistence;
    private final GetKnowledgeContextService getKnowledgeContextService;
    private final LearningEvaluationGenerationService learningEvaluationGenerationService;

    /**
     * Jest problem bo to nie obsługuje brzegowego przypadku gdzie usuniety zostaje requirement podczas robienia zadania ????
     */
    //TODO: FIX & REFACTOR THAT SHIT
    @Override
    public <T extends SolutionSubmission> WrapperResult<LearningResult<T>> createPersonalized(Student student, LearningExperience<?> learningExperience, T solutionSubmission) {
        ElementalRequirementId elementalRequirementId = learningExperience.getRequirements().stream().findFirst().get().getElementalRequirementId(); //TODO fix - optional and works for single lsub
        LearningSubject learningSubject = learningSubjectPersistence.getLearningSubjectByElementalRequirementId(elementalRequirementId).getValue();
        ElementalRequirement<?> elementalRequirement = learningSubject.getRequirements().stream().filter(o -> o.getId().equals(elementalRequirementId)).findFirst().get();
        PromptFragment knowledgeContext = getKnowledgeContextService.getContext(new GetKnowledgeContextSchema(learningSubject.getKnowledgeOrigin(), elementalRequirement)).getValue();
        LearningEvaluationGenerationSchema<?> generationSchema = new LearningEvaluationGenerationSchema<>(solutionSubmission, knowledgeContext,
                learningSubject.getRequirements().stream().filter(o -> o.getId().equals(elementalRequirementId)).collect(Collectors.toSet()));
        LearningEvaluation learningEvaluation = learningEvaluationGenerationService.generate(generationSchema).getValue();
        LearningResult<?> learningResult = LearningResult.create(learningExperience, solutionSubmission, learningEvaluation);
        return WrapperResult.successWrapper((LearningResult<T>) learningResult);
    }
}

package com.edutie.domain.service.learning.learningresult;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.LearningExperienceRequirement;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.LearningEvaluation;
import com.edutie.domain.core.learning.learningresult.entities.submission.SimpleProblemActivitySolutionSubmission;
import com.edutie.domain.core.learning.learningresult.service.LearningResultPersonalizationService;
import com.edutie.mocks.MockUser;
import com.edutie.mocks.learningexperience.SampleLearningExperience;
import com.edutie.mocks.persistence.learningsubject.PersonalizeLearningResultLearningSubjectPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LearningResultPersonalizationServiceImplementationTest {
    private final LearningSubjectPersistence learningSubjectPersistence = new PersonalizeLearningResultLearningSubjectPersistence();
    @Autowired
    private MockUser mockUser;

    LearningResultPersonalizationService learningResultPersonalizationService = new LearningResultPersonalizationServiceImplementation(
            learningSubjectPersistence,
            (schema) -> WrapperResult.successWrapper(PromptFragment.of("")),
            (schema) -> WrapperResult.successWrapper(LearningEvaluation.create(Set.of()))
    );

    private final LearningExperience<?> learningExperience = new SampleLearningExperience();

    @BeforeEach
    void setUp() {
        mockUser.saveToPersistence();
        LearningSubject learningSubject = learningSubjectPersistence.getLearningSubjectByElementalRequirementId(new ElementalRequirementId()).getValue();
        learningExperience.getRequirements().add(LearningExperienceRequirement.from(learningSubject.getRequirements().getFirst()));
    }

    @Test
    void createPersonalized() {
        WrapperResult<LearningResult<SimpleProblemActivitySolutionSubmission>> result = learningResultPersonalizationService.createPersonalized(
                mockUser.getStudentProfile(), learningExperience, new SimpleProblemActivitySolutionSubmission()
        );

        assertTrue(result.isSuccess());
        assertInstanceOf(SimpleProblemActivitySolutionSubmission.class, result.getValue().getSolutionSubmission());
    }
}
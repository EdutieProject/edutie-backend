package com.edutie.domain.service.learning.learningexperience;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.activity.SimpleProblemActivity;
import com.edutie.domain.core.learning.learningexperience.entities.activity.base.Activity;
import com.edutie.domain.core.learning.learningexperience.service.LearningExperiencePersonalizationService;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.common.PersonalizationRule;
import com.edutie.domain.core.personalization.engine.ActivityTypeChoicePersonalizationEngine;
import com.edutie.mocks.learningexperience.SampleLearningExperience;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import validation.WrapperResult;

import java.util.List;
import java.util.Set;

class LearningExperiencePersonalizationServiceImplementationTest {
    private final ActivityTypeChoicePersonalizationEngine activityTypeChoicePersonalizationEngine =
            new ActivityTypeChoicePersonalizationEngine(List.of()) {
                @Override
                public PersonalizationRule<? extends Class<? extends Activity>> chooseRule(
                        Student student, Set<LearningSubject> learningSubjects
                ) {
                    return (PersonalizationRule<Class<? extends Activity>>) () -> SimpleProblemActivity.class;
                }
            };
    private final Student student = Student.create(new UserId());
    private final LearningExperiencePersonalizationService learningExperiencePersonalizationService = new LearningExperiencePersonalizationServiceImplementation(
            (schema) -> WrapperResult.successWrapper(PromptFragment.of("")),
            activityTypeChoicePersonalizationEngine,
            (schema) -> WrapperResult.successWrapper(SampleLearningExperience.create())
    );


    @Test
    void createPersonalized() {
        ElementalRequirement elementalRequirement = new ElementalRequirement() {
            @Override
            public ElementalRequirementId getId() {
                return new ElementalRequirementId();
            }
        };
        KnowledgeOrigin knowledgeOrigin = new KnowledgeOrigin();
        knowledgeOrigin.setKnowledgeSubjectId(new KnowledgeSubjectId());

        WrapperResult<LearningExperience<?>> result = learningExperiencePersonalizationService.createPersonalized(
                student, knowledgeOrigin, elementalRequirement
        );

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertInstanceOf(SampleLearningExperience.class, result.getValue());

    }
}
package com.edutie.domain.service.learning.learningexperience;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.activity.base.Activity;
import com.edutie.domain.core.learning.learningexperience.service.LearningExperiencePersonalizationService;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.engine.ActivityTypeChoicePersonalizationEngine;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.GetKnowledgeContextService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.GetKnowledgeContextSchema;
import com.edutie.infrastructure.llm.learningexperience.LearningExperienceGenerationService;
import com.edutie.infrastructure.llm.learningexperience.schema.LearningExperienceGenerationSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class LearningExperiencePersonalizationServiceImplementation implements LearningExperiencePersonalizationService {
    private final GetKnowledgeContextService knowledgeContextService;
    private final ActivityTypeChoicePersonalizationEngine activityTypeChoiceEngine;
    private final LearningExperienceGenerationService learningExperienceGenerationService;

    @Override
    public WrapperResult<LearningExperience<?>> createPersonalized(Student student, KnowledgeOrigin knowledgeOrigin, ElementalRequirement elementalRequirement) {
        PromptFragment knowledgeContext = knowledgeContextService.getContext(
                new GetKnowledgeContextSchema(knowledgeOrigin.getKnowledgeSubjectId(), elementalRequirement.getStudentObjective())
        ).getValue();
        Class<? extends Activity> activityClass = activityTypeChoiceEngine.chooseRule(student, Set.of()).getPersonalizationContext();
        LearningExperienceGenerationSchema schema = new LearningExperienceGenerationSchema(
                student, knowledgeContext, activityClass, elementalRequirement, Set.of()
        );
        return learningExperienceGenerationService.generate(schema);
    }
}

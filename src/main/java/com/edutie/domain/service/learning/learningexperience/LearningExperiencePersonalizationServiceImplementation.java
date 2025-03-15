package com.edutie.domain.service.learning.learningexperience;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.service.LearningExperiencePersonalizationService;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.GetKnowledgeContextService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.GetKnowledgeContextSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class LearningExperiencePersonalizationServiceImplementation implements LearningExperiencePersonalizationService {
    private final GetKnowledgeContextService knowledgeContextService;


    @Override
    public WrapperResult<LearningExperience<?>> createPersonalized(Student student, KnowledgeOrigin knowledgeOrigin, ElementalRequirement elementalRequirement) {
        PromptFragment knowledgeContext = knowledgeContextService.getContext(
                new GetKnowledgeContextSchema(knowledgeOrigin.getKnowledgeSubjectId(), elementalRequirement.getStudentObjective())
        ).getValue();
        L

        return null;
    }
}

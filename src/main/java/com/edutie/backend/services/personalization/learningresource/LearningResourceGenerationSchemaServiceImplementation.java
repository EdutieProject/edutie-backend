package com.edutie.backend.services.personalization.learningresource;

import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities.PersonalizationRule;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities.ProblemDescriptor;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class LearningResourceGenerationSchemaServiceImplementation implements LearningResourceGenerationSchemaService {
    private final KnowledgeMapService knowledgeMapService;
    private final LargeLanguageModelService largeLanguageModelService;

    @Override
    public WrapperResult<LearningResource> generateLearningResource(LearningResourceDefinition lResDef, Student student) {
        LearningResourceGenerationSchema learningResourceGenerationSchema = LearningResourceGenerationSchema.create(lResDef, student.getId());
        for (ProblemDescriptor problemDescriptor : learningResourceGenerationSchema.getProblemDescriptors()) {
            for (KnowledgeCorrelation knowledgeCorrelation : knowledgeMapService.getKnowledgeCorrelations(problemDescriptor.getKnowledgeSubjectId()).getValue()) {
                PersonalizationRule personalizationRule = PersonalizationRule.create(problemDescriptor.getLearningRequirementId(), knowledgeCorrelation, student);
                problemDescriptor.addPersonalizationRule(personalizationRule);
            }
            problemDescriptor.calculateQualifiedSubRequirements(lResDef.getLearningRequirements().size());
        }
        LearningResource learningResource = largeLanguageModelService.generateLearningResource(learningResourceGenerationSchema).getValue();
        return WrapperResult.successWrapper(learningResource);
    }
}

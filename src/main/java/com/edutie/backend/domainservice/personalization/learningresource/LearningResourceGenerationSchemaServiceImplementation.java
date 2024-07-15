package com.edutie.backend.domainservice.personalization.learningresource;

import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities.PersonalizationRule;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities.ProblemDescriptor;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class LearningResourceGenerationSchemaServiceImplementation implements LearningResourceGenerationSchemaService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final KnowledgeMapService knowledgeMapService;

    @Override
    public WrapperResult<LearningResourceGenerationSchema> createSchema(LearningResourceDefinition lResDef, Student student) {
        LOGGER.info("Creating L.Res. Generation Schema with definition of id {} for student of id {}", lResDef.getId(), student.getId());
        LearningResourceGenerationSchema learningResourceGenerationSchema = LearningResourceGenerationSchema.create(lResDef, student);
        for (ProblemDescriptor problemDescriptor : learningResourceGenerationSchema.getProblemDescriptors()) {
            LOGGER.info("Creating problem descriptor...");
            for (KnowledgeCorrelation knowledgeCorrelation : knowledgeMapService.getKnowledgeCorrelations(problemDescriptor.getKnowledgeSubjectId()).getValue()) {
                PersonalizationRule personalizationRule = PersonalizationRule.create(knowledgeCorrelation, student);
                if (!personalizationRule.getLearningResults().isEmpty())
                    problemDescriptor.addPersonalizationRule(personalizationRule);
            }
            problemDescriptor.calculateQualifiedSubRequirements(lResDef.getLearningRequirements().size());
        }
        return WrapperResult.successWrapper(learningResourceGenerationSchema);
    }
}

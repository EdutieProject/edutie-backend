package com.edutie.backend.domainservice.personalization.learningresource;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities.PersonalizationRule;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities.GenerationSchemaProblemDescriptor;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
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
        for (GenerationSchemaProblemDescriptor problemDescriptor : learningResourceGenerationSchema.getProblemDescriptors()) {
            LOGGER.info("Creating problem descriptor...");
            for (KnowledgeCorrelation knowledgeCorrelation : knowledgeMapService.getKnowledgeCorrelations(problemDescriptor.getKnowledgeSubjectId()).getValue()) {
                PersonalizationRule personalizationRule = PersonalizationRule.create(knowledgeCorrelation, student);
                if (!personalizationRule.getLearningResults().isEmpty())
                    problemDescriptor.addPersonalizationRule(personalizationRule);
            }
            problemDescriptor.calculateQualifiedSubRequirements(lResDef
                    .getLearningRequirements().stream().filter(o -> o.getId().equals(problemDescriptor.getLearningRequirementId()))
                    .findFirst().orElseThrow().getSubRequirements().size()
            );
        }
        return WrapperResult.successWrapper(learningResourceGenerationSchema);
    }
}

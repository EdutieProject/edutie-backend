package com.edutie.backend.domainservice.personalization.learningresource;

import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities.PersonalizationRule;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities.ProblemDescriptor;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class LearningResourceGenerationServiceImplementation implements LearningResourceGenerationService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final KnowledgeMapService knowledgeMapService;
    private final LargeLanguageModelService largeLanguageModelService;

    @Override
    public WrapperResult<LearningResource> generateLearningResource(LearningResourceDefinition lResDef, Student student) {
        LOGGER.info("Creating L.Res. Generation Schema with definition of id {} for student of id {}", lResDef.getId(), student.getId());
        LearningResourceGenerationSchema learningResourceGenerationSchema = LearningResourceGenerationSchema.create(lResDef, student);
        for (ProblemDescriptor problemDescriptor : learningResourceGenerationSchema.getProblemDescriptors()) {
            LOGGER.info("Creating problem descriptor...");
            for (KnowledgeCorrelation knowledgeCorrelation : knowledgeMapService.getKnowledgeCorrelations(problemDescriptor.getKnowledgeSubjectId()).getValue()) {
                PersonalizationRule personalizationRule = PersonalizationRule.create(problemDescriptor.getLearningRequirementId(), knowledgeCorrelation, student);
                problemDescriptor.addPersonalizationRule(personalizationRule);
            }
            problemDescriptor.calculateQualifiedSubRequirements(lResDef.getLearningRequirements().size());
        }
        try {
            LOGGER.info("STUDENT LEARNING HISTORY:\n" + new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writeValueAsString(student.getLearningHistory()));
        } catch (Exception exception) {
            LOGGER.info(exception.getMessage());
        }
        //TODO: abstract logger away
        String lrgsLogInfo;
        try {
            lrgsLogInfo = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writeValueAsString(learningResourceGenerationSchema);
        } catch (Exception ex) {
            lrgsLogInfo = "Log error. Could not serialize LRGS, More here: " + ex.getMessage();
        }
        LOGGER.info("L.Res.Generation schema creation success. LRGS: \n" + lrgsLogInfo);
        LearningResource learningResource = largeLanguageModelService.generateLearningResource(learningResourceGenerationSchema).getValue();
        return WrapperResult.successWrapper(learningResource);
    }
}

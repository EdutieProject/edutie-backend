package com.edutie.backend.domainservice.personalization.learningresource.schema;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.personalization.common.PersonalizationRule;
import com.edutie.backend.domain.personalization.common.PersonalizationSchema;
import com.edutie.backend.domain.personalization.learningresourcedefinition.base.LearningResourceDefinitionBase;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Schema used in personalized Learning Resource generation.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter(AccessLevel.PRIVATE)
public class LearningResourceGenerationSchema implements PersonalizationSchema {
    private Set<ElementalRequirement> qualifiedRequirements = new HashSet<>();
    private Set<PersonalizationRule> personalizationRules = new HashSet<>();
    private AdditionalInstructions additionalInstructions;
    @JsonIgnore
    private Student studentMetadata;
    @JsonIgnore
    private LearningResourceDefinitionId learningResourceDefinitionId;
    @JsonIgnore
    private DefinitionType learningResourceDefinitionType;

    /**
     * Creation method for learning resource generation schema
     *
     * @param student                    student which is the recipient of the latter learning result
     * @param learningResultPersistence  persistence of learning results used to fetch past results of the student
     * @param knowledgeCorrelations      knowledge correlation list
     * @param definition learning resource definition
     * @return new Learning Resource Generation Schema
     */
    public static LearningResourceGenerationSchema create(
            Student student,
            LearningResultPersistence learningResultPersistence,
            Set<KnowledgeCorrelation> knowledgeCorrelations,
            LearningResourceDefinitionBase definition
    ) {
        LearningResourceGenerationSchema generationSchema = new LearningResourceGenerationSchema();
        generationSchema.setLearningResourceDefinitionType(definition.getDefinitionType());
        generationSchema.setStudentMetadata(student);
        generationSchema.setAdditionalInstructions(AdditionalInstructions.fromDefinition(definition));
        for (LearningRequirement learningRequirement : definition.getLearningRequirements()) {
            List<LearningResult> learningResultsOfRequirement = student.getLearningHistoryByKnowledgeSubject(learningResultPersistence, learningRequirement.getKnowledgeSubjectId());
            generationSchema.qualifiedRequirements.addAll(learningRequirement.calculateQualifiedElementalRequirements(learningResultsOfRequirement));
        }
        generationSchema.setLearningResourceDefinitionId(definition.getId());
        return generationSchema;
    }

}

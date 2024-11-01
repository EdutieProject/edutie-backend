package com.edutie.backend.domainservice.personalization.learningresource.schema;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.base.LearningResourceDefinitionBase;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domainservice.personalization.common.PersonalizationSchema;
import com.edutie.backend.domainservice.personalization.learningresource.schema.details.ActivityPersonalizedDetails;
import com.edutie.backend.domainservice.personalization.learningresource.schema.details.TheoryPersonalizedDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
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
    private ActivityPersonalizedDetails activityDetails;
    private TheoryPersonalizedDetails theoryDetails;
    @JsonIgnore
    private Student studentMetadata;
    private LearningResourceDefinitionId learningResourceDefinitionId;

    /**
     * Creation method for learning resource generation schema
     *
     * @param student                    student which is the recipient of the latter learning result
     * @param learningResultPersistence  persistence of learning results used to fetch past results of the student
     * @param knowledgeCorrelations      knowledge correlation list
     * @param learningResourceDefinition learning resource definition
     * @return new Learning Resource Generation Schema
     */
    public static LearningResourceGenerationSchema create(
            Student student,
            LearningResultPersistence learningResultPersistence,
            Set<KnowledgeCorrelation> knowledgeCorrelations,
            LearningResourceDefinitionBase learningResourceDefinition
    ) {
        LearningResourceGenerationSchema learningResourceGenerationSchema = new LearningResourceGenerationSchema();
        learningResourceGenerationSchema.setStudentMetadata(student);
        learningResourceGenerationSchema.setTheoryDetails(
                TheoryPersonalizedDetails.create(learningResourceDefinition.getTheoryDetails(), student, learningResultPersistence, knowledgeCorrelations)
        );
        learningResourceGenerationSchema.setActivityDetails(
                ActivityPersonalizedDetails.create(learningResourceDefinition.getActivityDetails(), student, learningResultPersistence, knowledgeCorrelations)
        );
        for (LearningRequirement learningRequirement : learningResourceDefinition.getLearningRequirements()) {
            List<LearningResult> learningResultsOfRequirement = student.getLearningHistoryByKnowledgeSubject(learningResultPersistence, learningRequirement.getKnowledgeSubjectId());
            learningResourceGenerationSchema.qualifiedRequirements.addAll(learningRequirement.calculateQualifiedElementalRequirements(learningResultsOfRequirement));
        }
        learningResourceGenerationSchema.setLearningResourceDefinitionId(learningResourceDefinition.getId());
        return learningResourceGenerationSchema;
    }

}

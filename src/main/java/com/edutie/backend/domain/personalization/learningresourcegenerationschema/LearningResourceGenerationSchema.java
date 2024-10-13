package com.edutie.backend.domain.personalization.learningresourcegenerationschema;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.details.ActivityPersonalizedDetails;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.details.TheoryPersonalizedDetails;
import com.edutie.backend.domain.personalization.student.Student;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Schema used in personalized Learning Resource generation.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter(AccessLevel.PRIVATE)
public class LearningResourceGenerationSchema {
    private Set<ElementalRequirement> qualifiedRequirements = new HashSet<>();
    private ActivityPersonalizedDetails activityDetails;
    private TheoryPersonalizedDetails theoryDetails;

    /**
     * Creation method for learning resource generation schema
     *
     * @param student               student which is the recipient of the latter learning result
     * @param learningRequirements  learning requirements (usually form the learning resource definition)
     * @param knowledgeCorrelations knowledge correlation list
     * @param activityDetails       personalized activity details
     * @param theoryDetails         personalized theory details
     * @return new Learning Resource Generation Schema
     */
    public static LearningResourceGenerationSchema create(
            Student student,
            Set<LearningRequirement> learningRequirements,
            Set<KnowledgeCorrelation> knowledgeCorrelations, // for the possible future purposes
            ActivityPersonalizedDetails activityDetails,
            TheoryPersonalizedDetails theoryDetails
    ) {
        LearningResourceGenerationSchema learningResourceGenerationSchema = new LearningResourceGenerationSchema();
        learningResourceGenerationSchema.setTheoryDetails(theoryDetails);
        learningResourceGenerationSchema.setActivityDetails(activityDetails);
        for (LearningRequirement learningRequirement : learningRequirements) {
            learningResourceGenerationSchema.qualifiedRequirements = new HashSet<>(
                    learningRequirement.calculateQualifiedElementalRequirements(student.getLearningHistoryByKnowledgeSubject(learningRequirement.getKnowledgeSubjectId()))
            );
        }
        return learningResourceGenerationSchema;
    }

}

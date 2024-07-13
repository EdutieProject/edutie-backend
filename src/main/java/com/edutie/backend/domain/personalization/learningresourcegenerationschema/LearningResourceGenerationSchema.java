package com.edutie.backend.domain.personalization.learningresourcegenerationschema;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities.ProblemDescriptor;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.identities.LearningResourceGenerationSchemaId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class LearningResourceGenerationSchema extends EntityBase<LearningResourceGenerationSchemaId> {
    private StudentId studentId;
    private LearningResourceDefinition learningResourceDefinition;
    private List<ProblemDescriptor> problemDescriptors = new ArrayList<>();

    /**
     * Initialize Learning Resource Generation Schema with empty problem descriptors
     * @param learningResourceDefinition learning resource definition
     * @return LearningResourceGenerationSchema
     */
    public static LearningResourceGenerationSchema create(LearningResourceDefinition learningResourceDefinition, StudentId studentId) {
        LearningResourceGenerationSchema learningResourceGenerationSchema = new LearningResourceGenerationSchema();
        learningResourceGenerationSchema.setLearningResourceDefinition(learningResourceDefinition);
        for (LearningRequirement learningRequirement : learningResourceDefinition.getLearningRequirements()) {
            learningResourceGenerationSchema.addProblemDescriptor(new ProblemDescriptor(learningRequirement));
        }
        return learningResourceGenerationSchema;
    }

    private void addProblemDescriptor(ProblemDescriptor problemDescriptor) {
        problemDescriptors.add(problemDescriptor);
    }
}

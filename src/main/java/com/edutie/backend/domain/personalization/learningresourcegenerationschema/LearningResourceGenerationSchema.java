package com.edutie.backend.domain.personalization.learningresourcegenerationschema;

import com.edutie.backend.api.serialization.serializers.IdOnlySerializer;
import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities.GenerationSchemaProblemDescriptor;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.identities.LearningResourceGenerationSchemaId;
import com.edutie.backend.domain.personalization.student.Student;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Schema used in personalized Learning Resource generation.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class LearningResourceGenerationSchema extends AuditableEntityBase<LearningResourceGenerationSchemaId> {
    @JsonSerialize(using = IdOnlySerializer.class)
    private Student student;
    private LearningResourceDefinition learningResourceDefinition;
    private List<GenerationSchemaProblemDescriptor> problemDescriptors = new ArrayList<>();

    /**
     * Initialize Learning Resource Generation Schema with empty problem descriptors
     * Valid Learning Resource Generation Schema creation requires usage of external system, thus
     * to create a valid L.R.G.S. LearningResourceGenerationSchemaService must be used.
     *
     * @param learningResourceDefinition learning resource definition
     * @return LearningResourceGenerationSchema
     */
    public static LearningResourceGenerationSchema create(LearningResourceDefinition learningResourceDefinition, Student student) {
        LearningResourceGenerationSchema learningResourceGenerationSchema = new LearningResourceGenerationSchema();
        learningResourceGenerationSchema.setId(new LearningResourceGenerationSchemaId());
        learningResourceGenerationSchema.setLearningResourceDefinition(learningResourceDefinition);
        learningResourceGenerationSchema.setStudent(student);
        learningResourceGenerationSchema.setCreatedBy(student.getOwnerUserId());
        for (LearningRequirement learningRequirement : learningResourceDefinition.getLearningRequirements()) {
            learningResourceGenerationSchema.addProblemDescriptor(new GenerationSchemaProblemDescriptor(learningRequirement));
        }
        return learningResourceGenerationSchema;
    }

    private void addProblemDescriptor(GenerationSchemaProblemDescriptor problemDescriptor) {
        problemDescriptors.add(problemDescriptor);
    }
}

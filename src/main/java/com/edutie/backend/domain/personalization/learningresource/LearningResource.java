package com.edutie.backend.domain.personalization.learningresource;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A singular form of learning in the application.
 * This is the resource which is used by the learner to exercise
 * his knowledge. It may be exclusively generated for a given student.
 */
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
public class LearningResource extends AuditableEntityBase<LearningResourceId> {
    @ManyToMany(targetEntity = ElementalRequirement.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ElementalRequirement> qualifiedRequirements = new HashSet<>();
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "student_id"))
    private StudentId studentId;
    @OneToOne(targetEntity = Activity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Activity activity;
    @OneToOne(targetEntity = Theory.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Theory theory;

    @AttributeOverride(name = "identifierValue", column = @Column(name = "definition_id"))
    private LearningResourceDefinitionId definitionId;
    private DefinitionType definitionType;
    /**
     * Recommended constructor that creates learning resource from L.R.G.S. and other different details.
     *
     * @param generationSchema generation schema
     * @param activity         activity
     * @param theory           theory
     * @return new Learning Resource
     */
    public static LearningResource create(
            LearningResourceGenerationSchema generationSchema,
            Activity activity,
            Theory theory
    ) {
        LearningResource learningResource = new LearningResource();
        learningResource.setId(new LearningResourceId());
        learningResource.setCreatedBy(generationSchema.getStudentMetadata().getOwnerUserId());
        learningResource.setStudentId(generationSchema.getStudentMetadata().getId());
        learningResource.setDefinitionId(generationSchema.getLearningResourceDefinitionId());
        learningResource.setDefinitionType(generationSchema.getLearningResourceDefinitionType());
        learningResource.setQualifiedRequirements(generationSchema.getQualifiedRequirements());
        learningResource.setActivity(activity);
        learningResource.setTheory(theory);
        return learningResource;
    }

    @JsonProperty("learningRequirementNames")
    public Set<String> getLearningRequirementNames() {
        return qualifiedRequirements.stream().map(o -> o.getLearningRequirement().getName()).collect(Collectors.toSet());
    }

}

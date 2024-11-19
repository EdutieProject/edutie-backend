package com.edutie.backend.domain.personalization.learningresource;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.TheoryCard;
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
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<ElementalRequirement> qualifiedRequirements = new HashSet<>();
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "student_id"))
    private StudentId studentId;
    @OneToOne(targetEntity = Activity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Activity activity;
    @OneToMany(targetEntity = TheoryCard.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TheoryCard> theoryCards;
    @Column(columnDefinition = "TEXT")
    private String mermaidVisualisationString;
    @AttributeOverride(name = "identifierValue", column = @Column(name = "definition_id"))
    private LearningResourceDefinitionId definitionId;
    private DefinitionType definitionType;

    /**
     * Recommended constructor that creates learning resource from L.R.G.S. and other different details.
     *
     * @param generationSchema generation schema
     * @param activity         activity
     * @param theoryCards      theory cards
     * @return new Learning Resource
     */
    public static LearningResource create(
            LearningResourceGenerationSchema generationSchema,
            String mermaidVisualisationString,
            Activity activity,
            Set<TheoryCard> theoryCards
    ) {
        LearningResource learningResource = new LearningResource();
        learningResource.setId(new LearningResourceId());
        learningResource.setCreatedBy(generationSchema.getStudentMetadata().getOwnerUserId());
        learningResource.setStudentId(generationSchema.getStudentMetadata().getId());
        learningResource.setDefinitionId(generationSchema.getLearningResourceDefinitionId());
        learningResource.setDefinitionType(generationSchema.getLearningResourceDefinitionType());
        learningResource.setQualifiedRequirements(generationSchema.getQualifiedRequirements());
        learningResource.setActivity(activity);
        learningResource.setMermaidVisualisationString(mermaidVisualisationString);
        learningResource.setTheoryCards(theoryCards);
        return learningResource;
    }

    @JsonProperty("learningRequirementNames")
    public Set<String> getLearningRequirementNames() {
        return qualifiedRequirements.stream().map(o -> o.getLearningRequirement().getName()).collect(Collectors.toSet());
    }

}

package com.edutie.backend.domain.personalization.learningresource;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.TheoryCard;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresource.valueobjects.Visualisation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.base.LearningResourceDefinitionBase;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
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
    @Embedded
    private Visualisation visualisation;
    @AttributeOverride(name = "identifierValue", column = @Column(name = "definition_id"))
    private LearningResourceDefinitionId definitionId;
    private DefinitionType definitionType;

    /**
     * Recommended constructor that creates learning resource using provided details.
     *
     * @param student                    student for which Learning Resource is created
     * @param learningResourceDefinition the definition used in the creation process
     * @param activity                   activity
     * @param theoryCards                theory cards
     * @param visualisation              visualisation
     * @param qualifiedRequirements      qualified elemental requirements
     * @return new Learning Resource
     */
    public static LearningResource create(
            Student student,
            LearningResourceDefinitionBase learningResourceDefinition,
            Set<ElementalRequirement> qualifiedRequirements,
            Activity activity,
            Set<TheoryCard> theoryCards,
            Visualisation visualisation
    ) {
        LearningResource learningResource = new LearningResource();
        learningResource.setId(new LearningResourceId());
        learningResource.setCreatedBy(student.getOwnerUserId());
        learningResource.setStudentId(student.getId());
        learningResource.setDefinitionId(learningResourceDefinition.getId());
        learningResource.setDefinitionType(learningResourceDefinition.getDefinitionType());
        learningResource.setQualifiedRequirements(qualifiedRequirements);
        learningResource.setActivity(activity);
        learningResource.setVisualisation(visualisation);
        learningResource.setTheoryCards(theoryCards);
        return learningResource;
    }


    public record LearningRequirementSnippet(LearningRequirementId learningRequirementId, String name) {
        //TODO: better include json view for Learning Requirements
    }

    @JsonProperty("learningRequirements")
    public Set<LearningRequirementSnippet> getAssociatedLearningRequirements() {
        return qualifiedRequirements.stream().map(ElementalRequirement::getLearningRequirement)
                .map(o -> new LearningRequirementSnippet(o.getId(), o.getName())).collect(Collectors.toSet());
    }

}

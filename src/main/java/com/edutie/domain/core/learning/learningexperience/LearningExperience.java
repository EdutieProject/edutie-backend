package com.edutie.domain.core.learning.learningexperience;

import com.edutie.domain.core.common.base.AuditableEntityBase;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.domain.core.learning.learningexperience.entities.Activity;
import com.edutie.domain.core.learning.learningexperience.entities.TheoryCard;
import com.edutie.domain.core.learning.learningexperience.identities.LearningResourceId;
import com.edutie.domain.core.learning.learningexperience.valueobjects.Visualisation;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.identities.StudentId;
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
public class LearningExperience extends AuditableEntityBase<LearningResourceId> {
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

    /**
     * Recommended constructor that creates learning resource using provided details.
     *
     * @param student                    student for which Learning Resource is created
     * @param activity                   activity
     * @param theoryCards                theory cards
     * @param visualisation              visualisation
     * @param qualifiedRequirements      qualified elemental requirements
     * @return new Learning Resource
     */
    public static LearningExperience create(
            Student student,
            Set<ElementalRequirement> qualifiedRequirements,
            Activity activity,
            Set<TheoryCard> theoryCards,
            Visualisation visualisation
    ) {
        LearningExperience learningExperience = new LearningExperience();
        learningExperience.setId(new LearningResourceId());
        learningExperience.setCreatedBy(student.getOwnerUserId());
        learningExperience.setStudentId(student.getId());
        learningExperience.setQualifiedRequirements(qualifiedRequirements);
        learningExperience.setActivity(activity);
        learningExperience.setVisualisation(visualisation);
        learningExperience.setTheoryCards(theoryCards);
        return learningExperience;
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

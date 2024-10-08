package com.edutie.backend.domain.personalization.learningresourcedefinition;

import com.edutie.backend.domain.common.base.EducatorCreatedAuditableEntity;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Learning Resource Definition is a definition of how Learning Resource should be generated
 * from the knowledge and educational perspective. LRs can be generated using only prompt fragments, but
 * the learning requirements are imperative regarding the LR creation.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class LearningResourceDefinition extends EducatorCreatedAuditableEntity<LearningResourceDefinitionId> {
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private final Set<LearningRequirement> learningRequirements = new HashSet<>();
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "theory_description", columnDefinition = "TEXT"))
    private PromptFragment theoryDescription;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "graph_description", columnDefinition = "TEXT"))
    private PromptFragment graphDescription;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "exercise_description", columnDefinition = "TEXT"))
    private PromptFragment exerciseDescription;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "hints_description", columnDefinition = "TEXT"))
    private PromptFragment hintsAdditionalDescription;

    /**
     * Creates a Learning Resource Definition with full specification provided.
     *
     * @param educator                   educator profile
     * @param theoryDescription          theory description prompt fragment
     * @param exerciseDescription        exercise description prompt fragment
     * @param graphDescription           graph description prompt fragment
     * @param hintsAdditionalDescription hints additional description prompt fragment
     * @param learningRequirements       learning requirements set
     * @return new Learning Resource Definition
     */
    public static LearningResourceDefinition create(
            Educator educator,
            PromptFragment theoryDescription,
            PromptFragment exerciseDescription,
            PromptFragment graphDescription,
            PromptFragment hintsAdditionalDescription,
            Set<LearningRequirement> learningRequirements
    ) {
        LearningResourceDefinition lrd = new LearningResourceDefinition();
        lrd.setId(new LearningResourceDefinitionId());
        lrd.setAuthorEducator(educator);
        lrd.setCreatedBy(educator.getOwnerUserId());
        lrd.setTheoryDescription(theoryDescription);
        lrd.setExerciseDescription(exerciseDescription);
        lrd.setGraphDescription(graphDescription);
        lrd.setHintsAdditionalDescription(hintsAdditionalDescription);
        lrd.learningRequirements.addAll(learningRequirements);
        return lrd;
    }

    /**
     * Creates an LRD with only mandatory descriptors but without learning requirements.
     *
     * @param educator            educator profile
     * @param theoryDescription   theory description prompt fragment
     * @param exerciseDescription exercise description prompt fragment
     * @return new Learning Resource Definition
     */
    public static LearningResourceDefinition create(Educator educator, PromptFragment theoryDescription, PromptFragment exerciseDescription) {
        return create(educator, theoryDescription, exerciseDescription, null, null, Set.of());
    }

    /**
     * Retrieves learning requirement of the given id if it is associated with this LRD.
     *
     * @param learningRequirementId learning requirement Id
     * @return Optional Learning Requirement
     */
    public Optional<LearningRequirement> getLearningRequirementOfId(LearningRequirementId learningRequirementId) {
        return learningRequirements.stream().filter(o -> o.getId().equals(learningRequirementId)).findFirst();
    }

    /**
     * Adds learning requirement to this LRD
     *
     * @param learningRequirement learning requirement to add
     */
    public void addLearningRequirement(LearningRequirement learningRequirement) {
        learningRequirements.add(learningRequirement);
    }

    /**
     * Removes learning requirement given its id
     *
     * @param learningRequirementId learning requirement Id
     */
    public void removeLearningRequirement(LearningRequirementId learningRequirementId) {
        learningRequirements.removeIf(o -> o.getId().equals(learningRequirementId));
    }
}

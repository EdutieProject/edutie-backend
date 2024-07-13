package com.edutie.backend.domain.personalization.learningresourcedefinition;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningResourceDefinition extends AuditableEntityBase<LearningResourceDefinitionId> {
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "theory_description"))
    private PromptFragment theoryDescription;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "theory_summary_description"))
    private PromptFragment theorySummaryAdditionalDescription;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "exercise_description"))
    private PromptFragment exerciseDescription;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "hints_description"))
    private PromptFragment hintsAdditionalDescription;
    @ManyToMany
    private final Set<LearningRequirement> learningRequirements = new HashSet<>();

    public static LearningResourceDefinition create(
            PromptFragment theoryDescription,
            PromptFragment exerciseDescription,
            Set<LearningRequirement> learningRequirements
    ) {
        LearningResourceDefinition learningResourceDefinition = new LearningResourceDefinition();
        learningResourceDefinition.setTheoryDescription(theoryDescription);
        learningResourceDefinition.setExerciseDescription(exerciseDescription);
        learningResourceDefinition.learningRequirements.addAll(learningRequirements);
        return learningResourceDefinition;
    }

    public void addLearningRequirement(LearningRequirement learningRequirement) {
        learningRequirements.add(learningRequirement);
    }

    public void removeLearningRequirement(LearningRequirementId learningRequirementId) {
        learningRequirements.removeIf(o -> o.getId().equals(learningRequirementId));
    }
}

package com.edutie.backend.domain.personalization.learningresourcedefinition;

import com.edutie.backend.domain.common.base.EducatorCreatedAuditableEntity;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Learning Resource Definition is an absolute definition of how Learning Resource should be generated
 * from the knowledge and educational perspective.
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
	@AttributeOverride(name = "text", column = @Column(name = "theory_summary_description", columnDefinition = "TEXT"))
	private PromptFragment theorySummaryAdditionalDescription;
	@Embedded
	@AttributeOverride(name = "text", column = @Column(name = "exercise_description", columnDefinition = "TEXT"))
	private PromptFragment exerciseDescription;
	@Embedded
	@AttributeOverride(name = "text", column = @Column(name = "hints_description", columnDefinition = "TEXT"))
	private PromptFragment hintsAdditionalDescription;

	public static LearningResourceDefinition create(Educator educator, PromptFragment theoryDescription, PromptFragment exerciseDescription, PromptFragment theorySummaryAdditionalDescription, PromptFragment hintsAdditionalDescription) {
		LearningResourceDefinition lrd = new LearningResourceDefinition();
		lrd.setId(new LearningResourceDefinitionId());
		lrd.setAuthorEducator(educator);
		lrd.setCreatedBy(educator.getOwnerUserId());
		lrd.setTheoryDescription(theoryDescription);
		lrd.setExerciseDescription(exerciseDescription);
		lrd.setTheorySummaryAdditionalDescription(theorySummaryAdditionalDescription);
		lrd.setHintsAdditionalDescription(hintsAdditionalDescription);
		return lrd;
	}

	public static LearningResourceDefinition create(PromptFragment theoryDescription, PromptFragment exerciseDescription, Set<LearningRequirement> learningRequirements) {
		LearningResourceDefinition learningResourceDefinition = new LearningResourceDefinition();
		learningResourceDefinition.setId(new LearningResourceDefinitionId());
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

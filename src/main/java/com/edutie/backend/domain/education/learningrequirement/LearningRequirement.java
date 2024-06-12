package com.edutie.backend.domain.education.learningrequirement;

import com.edutie.backend.domain.common.base.EducatorCreatedAuditableEntity;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.learningrequirement.entities.SubRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.education.learningrequirement.identities.SubRequirementId;
import com.edutie.backend.domain.studyprogram.science.Science;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.WrapperResult;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class LearningRequirement extends EducatorCreatedAuditableEntity<LearningRequirementId> {
    private String name;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "description"))
    private PromptFragment description = new PromptFragment();
    @ManyToOne(targetEntity = Science.class, fetch = FetchType.EAGER)
    private Science science;
    @OneToMany(targetEntity = SubRequirement.class, fetch = FetchType.EAGER)
    private Set<SubRequirement> subRequirements = new HashSet<>();

    /**
     * Recommended constructor associating Learning Requirement with an educator and a science
     *
     * @param educator creator reference
     * @return Learning Requirement
     */
    public static WrapperResult<LearningRequirement> create(Educator educator, Science science) {
        LearningRequirement learningRequirement = new LearningRequirement();
        learningRequirement.setId(new LearningRequirementId());
        learningRequirement.setCreatedBy(educator.getOwnerUserId());
        learningRequirement.setEducator(educator);
        learningRequirement.setScience(science);
        return WrapperResult.successWrapper(learningRequirement);
    }

    public void addSubRequirement(String name, String description, int orderIndex) {
        subRequirements.stream().filter(o -> o.getOrderIndex() >= orderIndex).forEach(o -> o.setOrderIndex(o.getOrderIndex() + 1));
        subRequirements.add(SubRequirement.create(name, PromptFragment.of(description), orderIndex));
    }


    public void removeSubRequirement(SubRequirementId subRequirementId) {
        subRequirements.removeIf(o -> o.getId().equals(subRequirementId));
    }
}

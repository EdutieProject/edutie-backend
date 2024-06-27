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
import validation.Error;
import validation.Result;
import validation.WrapperResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @OrderBy("ordinal")
    private List<SubRequirement> subRequirements = new ArrayList<>();

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

    /**
     * Appends sub requirement at the end of the sub requirement list
     * @param name sub requirement name
     * @param description sub requirement description
     */
    public void appendSubRequirement(String name, String description) {
        subRequirements.add(SubRequirement.create(name, PromptFragment.of(description), subRequirements.size()));
    }

    /**
     * Moves sub requirement in the list to the desired index
     * @param currentIndex current sub requirement index
     * @param desiredIndex desired sub requirement index
     * @return Result object
     */
    public Result moveSubRequirement(int currentIndex, int desiredIndex) {
        if (currentIndex < 0 || currentIndex >= subRequirements.size() || desiredIndex < 0 || desiredIndex >= subRequirements.size())
            //TODO
            return Result.failure(new Error("TODO!", this.getClass().getSimpleName()));
        SubRequirement subRequirement = subRequirements.get(currentIndex);
        subRequirements.remove(subRequirement);
        subRequirements.add(desiredIndex, subRequirement);
        for (int i = 0; i < subRequirements.size(); i++) {
            subRequirements.get(i).setOrdinal(i);
        }
        return Result.success();
    }

    /**
     * Removes sub requirement at given index
     * @param index index
     * @return Result object
     */
    public Result removeSubRequirement(int index) {
        if (subRequirements.remove(index) == null)
            return Result.failure(new Error("TODO!", this.getClass().getSimpleName()));
        for (int i = 0; i < subRequirements.size(); i++) {
            subRequirements.get(i).setOrdinal(i);
        }
        return Result.success();
    }
}

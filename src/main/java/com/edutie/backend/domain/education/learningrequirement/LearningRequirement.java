package com.edutie.backend.domain.education.learningrequirement;

import com.edutie.backend.domain.common.base.EducatorCreatedAuditableEntity;
import com.edutie.backend.domain.common.DomainErrors;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.learningrequirement.entities.SubRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.Result;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(targetEntity = SubRequirement.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("ordinal")
    private List<SubRequirement> subRequirements = new ArrayList<>();
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "knowledge_node_id"))
    private KnowledgeSubjectId knowledgeSubjectId;

    /**
     * Recommended constructor associating Learning Requirement with an educator and a science
     *
     * @param educator creator reference
     * @return Learning Requirement
     */
    public static LearningRequirement create(Educator educator) {
        LearningRequirement learningRequirement = new LearningRequirement();
        learningRequirement.setId(new LearningRequirementId());
        learningRequirement.setCreatedBy(educator.getOwnerUserId());
        learningRequirement.setAuthorEducator(educator);
        return learningRequirement;
    }

    /**
     * Appends sub requirement at the end of the sub requirement list
     *
     * @param subRequirementDescriptor sub requirement descriptor
     */
    public void appendSubRequirement(String subRequirementDescriptor) {
        subRequirements.add(SubRequirement.create(PromptFragment.of(subRequirementDescriptor), subRequirements.size()));
    }

    /**
     * Inserts sub requirement into given index, moving all the next sub requirements forward and
     * updating their ordinal
     *
     * @param subRequirementDescriptor sub requirement descriptor
     * @param desiredIndex             desired index
     * @return Result object
     */
    public Result insertSubRequirement(String subRequirementDescriptor, int desiredIndex) {
        appendSubRequirement(subRequirementDescriptor);
        return moveSubRequirement(subRequirements.size() - 1, desiredIndex);
    }

    /**
     * Moves sub requirement in the list to the desired index
     *
     * @param currentIndex current sub requirement index
     * @param desiredIndex desired sub requirement index
     * @return Result object
     */
    public Result moveSubRequirement(int currentIndex, int desiredIndex) {
        if (currentIndex < 0 || currentIndex >= subRequirements.size() || desiredIndex < 0 || desiredIndex >= subRequirements.size())
            return Result.failure(DomainErrors.invalidIndex(SubRequirement.class));
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
     *
     * @param index index
     * @return Result object
     */
    public Result removeSubRequirement(int index) {
        if (subRequirements.remove(index) == null)
            return Result.failure(DomainErrors.invalidIndex(SubRequirement.class));
        for (int i = 0; i < subRequirements.size(); i++) {
            subRequirements.get(i).setOrdinal(i);
        }
        return Result.success();
    }
}

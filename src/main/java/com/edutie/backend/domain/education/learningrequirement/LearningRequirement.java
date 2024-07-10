package com.edutie.backend.domain.education.learningrequirement;

import com.edutie.backend.domain.common.base.EducatorCreatedAuditableEntity;
import com.edutie.backend.domain.common.errors.CommonErrors;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.learningrequirement.entities.SubRequirement;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.education.learningrequirement.identities.SubRequirementId;
import com.edutie.backend.domain.studyprogram.science.Science;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @OneToMany(targetEntity = SubRequirement.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("ordinal")
    private List<SubRequirement> subRequirements = new ArrayList<>();

    /**
     * Recommended constructor associating Learning Requirement with an educator and a science
     *
     * @param educator creator reference
     * @return Learning Requirement
     */
    public static LearningRequirement create(Educator educator, Science science) {
        LearningRequirement learningRequirement = new LearningRequirement();
        learningRequirement.setId(new LearningRequirementId());
        learningRequirement.setCreatedBy(educator.getOwnerUserId());
        learningRequirement.setAuthorEducator(educator);
        learningRequirement.setScience(science);
        return learningRequirement;
    }

    /**
     * Appends sub requirement at the end of the sub requirement list
     *
     * @param name        sub requirement name
     * @param description sub requirement description
     */
    public void appendSubRequirement(String name, String description) {
        subRequirements.add(SubRequirement.create(name, PromptFragment.of(description), subRequirements.size()));
    }

    /**
     * Inserts sub requirement into given index, moving all the next sub requirements forward and
     * updating their ordinal
     *
     * @param name         sub requirement name
     * @param description  sub requirement description
     * @param desiredIndex desired index
     * @return Result object
     */
    public Result insertSubRequirement(String name, String description, int desiredIndex) {
        appendSubRequirement(name, description);
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
            return Result.failure(CommonErrors.invalidIndex(SubRequirement.class));
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
            return Result.failure(CommonErrors.invalidIndex(SubRequirement.class));
        for (int i = 0; i < subRequirements.size(); i++) {
            subRequirements.get(i).setOrdinal(i);
        }
        return Result.success();
    }

    public Result assignKnowledgeNodeId(SubRequirementId subRequirementId, KnowledgeSubjectId knowledgeSubjectId) {
        Optional<SubRequirement> subRequirement = subRequirements.stream()
                .filter(o -> o.getId().equals(subRequirementId)).findFirst();
        if (subRequirement.isEmpty())
            return Result.failure(CommonErrors.invalidIdentifier());
        subRequirement.get().setKnowledgeSubjectId(knowledgeSubjectId);
        return Result.success();
    }
}

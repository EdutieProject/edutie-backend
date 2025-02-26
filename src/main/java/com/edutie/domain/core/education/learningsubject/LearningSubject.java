package com.edutie.domain.core.education.learningsubject;

import com.edutie.domain.core.common.DomainErrors;
import com.edutie.domain.core.common.base.EducatorCreatedAuditableEntity;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import com.edutie.domain.core.education.learningsubject.entities.LearningSubjectRequirement;
import com.edutie.domain.core.education.learningsubject.identities.LearningRequirementId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Learning Subject entity represents the subject that student may learn from to gain understanding of the knowledge.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class LearningSubject extends EducatorCreatedAuditableEntity<LearningRequirementId> {
    private String name;
    @OneToMany(targetEntity = LearningSubjectRequirement.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("ordinal")
    private List<LearningSubjectRequirement> requirements = new ArrayList<>();
    @Embedded
    private KnowledgeOrigin knowledgeOrigin = new KnowledgeOrigin();

    /**
     * Recommended constructor associating Learning Requirement with an educator and a science
     *
     * @param educator creator reference
     * @return Learning Requirement
     */
    public static LearningSubject createBlank(Educator educator, String name) {
        LearningSubject learningSubject = new LearningSubject();
        learningSubject.setId(new LearningRequirementId());
        learningSubject.setCreatedBy(educator.getOwnerUserId());
        learningSubject.setAuthorEducator(educator);
        learningSubject.setName(name);
        return learningSubject;
    }

    /**
     * Returns whether knowledge origin is empty (unusable)
     *
     * @return true/false
     */
    public boolean isKnowledgeOriginEmpty() {
        return this.knowledgeOrigin.getKnowledgeSubjectId() == null;
    }

    /**
     * Appends requirement at the end of the requirement list
     *
     * @param title                 title of learning subject
     * @param scientificDescription description of the sub requirement
     */
    public void appendSubRequirement(String title, PromptFragment scientificDescription) {
        requirements.add(LearningSubjectRequirement.create(this, title, scientificDescription, requirements.size()));
    }

    /**
     * Inserts sub requirement into given index, moving all the next sub requirements forward and
     * updating their ordinal
     *
     * @param title            sub requirement text
     * @param studentObjective sub requirement descriptor
     * @param desiredIndex     desired index
     * @return Result object
     */
    public Result insertSubRequirement(String title, PromptFragment studentObjective, int desiredIndex) {
        this.appendSubRequirement(title, studentObjective);
        return moveSubRequirement(requirements.size() - 1, desiredIndex);
    }

    /**
     * Moves sub requirement in the list to the desired index
     *
     * @param currentIndex current sub requirement index
     * @param desiredIndex desired sub requirement index
     * @return Result object
     */
    public Result moveSubRequirement(int currentIndex, int desiredIndex) {
        if (currentIndex < 0 || currentIndex >= requirements.size() || desiredIndex < 0 || desiredIndex >= requirements.size())
            return Result.failure(DomainErrors.invalidIndex(LearningSubjectRequirement.class));
        LearningSubjectRequirement requirement = requirements.get(currentIndex);
        requirements.remove(requirement);
        requirements.add(desiredIndex, requirement);
        for (int i = 0; i < requirements.size(); i++) {
            requirements.get(i).setOrdinal(i);
        }
        return Result.success();
    }

    /**
     * Removes requirements by its id
     *
     * @param requirementId requirement id
     */
    public void removeRequirement(ElementalRequirementId requirementId) {
        this.requirements.removeIf(o -> o.getId().equals(requirementId));
    }
}

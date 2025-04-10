package com.edutie.domain.core.education.learningsubject;

import com.edutie.domain.core.common.DomainErrors;
import com.edutie.domain.core.common.base.EducatorCreatedAuditableEntity;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.KnowledgeProvider;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import com.edutie.domain.core.education.learningsubject.entities.LearningSubjectRequirement;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.domain.core.education.learningsubject.service.DynamicRequirementSelectionService;
import com.edutie.domain.core.education.learningsubject.service.StudentObjectiveInferringService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.Result;
import validation.WrapperResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Learning Subject entity represents the subject that student may learn from to gain understanding of the knowledge.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class LearningSubject extends EducatorCreatedAuditableEntity<LearningSubjectId>
        implements KnowledgeProvider<KnowledgeOrigin> {
    private String name;
    @OneToMany(targetEntity = LearningSubjectRequirement.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordinal")
    private List<LearningSubjectRequirement> requirements = new ArrayList<>();
    @Embedded
    private KnowledgeOrigin knowledgeOrigin;

    /**
     * Recommended constructor associating Learning Requirement with an educator and a science
     *
     * @param educator creator reference
     * @return Learning Requirement
     */
    public static LearningSubject createBlank(Educator educator, String name) {
        LearningSubject learningSubject = new LearningSubject();
        learningSubject.setId(new LearningSubjectId());
        learningSubject.setCreatedBy(educator.getOwnerUserId());
        learningSubject.setAuthorEducator(educator);
        learningSubject.setName(name);
        learningSubject.setKnowledgeOrigin(new KnowledgeOrigin());
        return learningSubject;
    }

    /**
     * Choose elemental requirement. If there is an elemental requirement id specified, returns corresponding. When null,
     * the elemental requirement is chosen dynamically using service.
     *
     * @param elementalRequirementId             elemental req id
     * @param dynamicRequirementSelectionService dynamic selection service
     * @return chosen Learning Subject Requirement
     */
    public LearningSubjectRequirement chooseLearningSubjectRequirement(ElementalRequirementId elementalRequirementId, DynamicRequirementSelectionService dynamicRequirementSelectionService) {
        if (elementalRequirementId != null) {
            return requirements.stream().filter(o -> o.getId().equals(elementalRequirementId)).findFirst().orElseThrow();
        }
        return dynamicRequirementSelectionService.chooseRequirement(this).getValue();
    }

    /**
     * Returns whether knowledge origin is empty (unusable)
     *
     * @return true/false
     */
    public boolean isKnowledgeOriginEmpty() {
        return knowledgeOrigin == null || knowledgeOrigin.isEmpty();
    }

    /**
     * Returns whether learning subject is eligible for learning.
     *
     * @return true/false
     */
    public boolean isLearningEligible() {
        return !isKnowledgeOriginEmpty() && !requirements.isEmpty();
    }

    /**
     * Sets knowledge origin's knowledge subject id
     *
     * @param knowledgeSubjectId knowledge subject id
     */
    public void setRelatedKnowledgeSubjectId(KnowledgeSubjectId knowledgeSubjectId) {
        if (knowledgeOrigin == null) {
            knowledgeOrigin = new KnowledgeOrigin();
        }
        knowledgeOrigin.setKnowledgeSubjectId(knowledgeSubjectId);
    }

    /**
     * Infer the requirement objective using provided service and insert it into the given ordinal.
     *
     * @param title            title of the requirement
     * @param ordinal          desired ordinal
     * @param inferringService inferring service
     * @return Result of the operation
     */
    public WrapperResult<LearningSubject> inferAndInsertRequirement(String title, int ordinal, StudentObjectiveInferringService inferringService) {
        PromptFragment studentObjective = inferringService.getStudentObjective(title, knowledgeOrigin).getValue();
        insertRequirement(title, studentObjective, ordinal).throwIfFailure();
        return WrapperResult.successWrapper(this);
    }

    /**
     * Appends requirement at the end of the requirement list
     *
     * @param title                 title of learning subject
     * @param scientificDescription description of the sub requirement
     */
    public void appendRequirement(String title, PromptFragment scientificDescription) {
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
    public Result insertRequirement(String title, PromptFragment studentObjective, int desiredIndex) {
        this.appendRequirement(title, studentObjective);
        return moveRequirement(requirements.size() - 1, desiredIndex);
    }

    /**
     * Moves sub requirement in the list to the desired index
     *
     * @param currentIndex current sub requirement index
     * @param desiredIndex desired sub requirement index
     * @return Result object
     */
    public Result moveRequirement(int currentIndex, int desiredIndex) {
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

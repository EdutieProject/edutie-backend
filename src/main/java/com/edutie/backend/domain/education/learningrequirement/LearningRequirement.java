package com.edutie.backend.domain.education.learningrequirement;

import com.edutie.backend.domain.common.DomainErrors;
import com.edutie.backend.domain.common.base.EducatorCreatedAuditableEntity;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Learning Requirement entity represents the requirements that student exercises to gain knowledge.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class LearningRequirement extends EducatorCreatedAuditableEntity<LearningRequirementId> {
    @OneToMany(targetEntity = ElementalRequirement.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("ordinal")
    private List<ElementalRequirement> elementalRequirements = new ArrayList<>();
    private String name;
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "knowledge_node_id"))
    private KnowledgeSubjectId knowledgeSubjectId;

    /**
     * The value which limits the amount of elemental requirements
     * assigned to the learning resources created with learning requirements
     */
    private static final int MAX_ELEMENTAL_REQUIREMENTS = 2;

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
     * @param requirementText       text of what is required
     * @param scientificDescription description of the sub requirement
     */
    public void appendSubRequirement(String requirementText, PromptFragment scientificDescription) {
        elementalRequirements.add(ElementalRequirement.create(this, PromptFragment.of(requirementText), scientificDescription, elementalRequirements.size()));
    }

    /**
     * Inserts sub requirement into given index, moving all the next sub requirements forward and
     * updating their ordinal
     *
     * @param requirementText                     sub requirement text
     * @param subRequirementScientificDescription sub requirement descriptor
     * @param desiredIndex                        desired index
     * @return Result object
     */
    public Result insertSubRequirement(String requirementText, PromptFragment subRequirementScientificDescription, int desiredIndex) {
        appendSubRequirement(requirementText, subRequirementScientificDescription);
        return moveSubRequirement(elementalRequirements.size() - 1, desiredIndex);
    }

    /**
     * Moves sub requirement in the list to the desired index
     *
     * @param currentIndex current sub requirement index
     * @param desiredIndex desired sub requirement index
     * @return Result object
     */
    public Result moveSubRequirement(int currentIndex, int desiredIndex) {
        if (currentIndex < 0 || currentIndex >= elementalRequirements.size() || desiredIndex < 0 || desiredIndex >= elementalRequirements.size())
            return Result.failure(DomainErrors.invalidIndex(ElementalRequirement.class));
        ElementalRequirement elementalRequirement = elementalRequirements.get(currentIndex);
        elementalRequirements.remove(elementalRequirement);
        elementalRequirements.add(desiredIndex, elementalRequirement);
        for (int i = 0; i < elementalRequirements.size(); i++) {
            elementalRequirements.get(i).setOrdinal(i);
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
        if (elementalRequirements.remove(index) == null)
            return Result.failure(DomainErrors.invalidIndex(ElementalRequirement.class));
        for (int i = 0; i < elementalRequirements.size(); i++) {
            elementalRequirements.get(i).setOrdinal(i);
        }
        return Result.success();
    }

    /**
     * Retrieves sub requirements with respect to their ordinal.
     *
     * @param qualifiedOrdinal as end index
     * @return list of Sub Requirements
     */
    public List<ElementalRequirement> getQualifiedSubRequirements(int qualifiedOrdinal) {
        return elementalRequirements.stream().filter(o -> o.getOrdinal() <= qualifiedOrdinal).toList();
    }

    /**
     * Retrieves the qualified elemental requirements based on the past results provided.
     *
     * @param pastResults past results provided as list
     * @return list of elemental requirements.
     */
    public List<ElementalRequirement> calculateQualifiedElementalRequirements(List<LearningResult> pastResults) {
        double meanOverallGrade = pastResults.stream().flatMap(o -> o.getAssessments().stream())
                .map(o -> o.getGrade().gradeNumber()).mapToInt(Integer::intValue).average().orElse(1d); // TODO: or else should give the value from correlated results
        double gradeAsPercentage = meanOverallGrade / Grade.MAX_GRADE.gradeNumber();
        int maxQualifiedRequirementOrdinal = (int) Math.ceil(gradeAsPercentage * elementalRequirements.size());
        return elementalRequirements.stream().filter(o ->
                o.getOrdinal() <= maxQualifiedRequirementOrdinal &&
                        o.getOrdinal() > maxQualifiedRequirementOrdinal - MAX_ELEMENTAL_REQUIREMENTS
        ).toList();
    }
}

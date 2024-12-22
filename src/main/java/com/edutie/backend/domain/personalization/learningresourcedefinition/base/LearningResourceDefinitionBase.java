package com.edutie.backend.domain.personalization.learningresourcedefinition.base;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.common.AbsoluteDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@MappedSuperclass
public abstract class LearningResourceDefinitionBase extends AuditableEntityBase<LearningResourceDefinitionId> implements AbsoluteDefinition {
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    protected final Set<LearningRequirement> learningRequirements = new HashSet<>();

    /**
     * Retrieves the definition type.
     * @return definition type
     */
    @JsonProperty("definitionType")
    public abstract DefinitionType getDefinitionType();

    /**
     * Retrieves all knowledge subject ids assigned to the associated learning requirements
     *
     * @return Set of knowledge subject ids
     */
    public Set<KnowledgeSubjectId> getKnowledgeSubjectIds() {
        return learningRequirements.stream().map(LearningRequirement::getKnowledgeSubjectId).collect(Collectors.toSet());
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

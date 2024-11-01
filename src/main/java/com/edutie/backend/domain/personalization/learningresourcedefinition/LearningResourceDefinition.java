package com.edutie.backend.domain.personalization.learningresourcedefinition;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.base.EducatorCreated;
import com.edutie.backend.domain.common.base.EducatorCreatedAuditableEntity;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.common.AbsoluteDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.base.LearningResourceDefinitionBase;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Learning Resource Definition is a definition of how Learning Resource should be generated
 * from the knowledge and educational perspective. LRs can be generated using only prompt fragments, but
 * the learning requirements are imperative regarding the LR creation.
 */
@Getter
@NoArgsConstructor
@Entity
public class LearningResourceDefinition extends LearningResourceDefinitionBase implements EducatorCreated {
    @ManyToOne(targetEntity = Educator.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    private Educator authorEducator;

    public static LearningResourceDefinition create(
            Educator educator,
            TheoryDetails theoryDetails,
            ActivityDetails activityDetails,
            Set<LearningRequirement> learningRequirements
    ) {
        LearningResourceDefinition lrd = new LearningResourceDefinition();
        lrd.setId(new LearningResourceDefinitionId());
        lrd.setAuthorEducator(educator);
        lrd.setCreatedBy(educator.getOwnerUserId());
        lrd.setActivityDetails(activityDetails);
        lrd.setTheoryDetails(theoryDetails);
        lrd.learningRequirements.addAll(learningRequirements);
        return lrd;
    }

    /**
     * Creates an LRD with only mandatory descriptors but without learning requirements.
     *
     * @param educator             educator profile
     * @param theoryDescription    theory description prompt fragment
     * @param exerciseDescription  exercise description prompt fragment
     * @param learningRequirements learning requirements
     * @return new Learning Resource Definition
     */
    public static LearningResourceDefinition create(
            Educator educator,
            PromptFragment theoryDescription,
            PromptFragment exerciseDescription,
            Set<LearningRequirement> learningRequirements
    ) {
        return create(educator,
                TheoryDetails.create(theoryDescription, PromptFragment.empty()),
                ActivityDetails.create(exerciseDescription, PromptFragment.empty()),
                learningRequirements
        );
    }

    //TODO ? DO sth with this shit it should not be that way
    public LearningResourceDefinition adjustRandomFactExercise(String randomFact) {
        this.activityDetails.setExerciseDescription(
                PromptFragment.of(String.format("""
                        Exercise must be related to the provided random fact:
                        <random-fact>%s</random-fact>
                        Exercise should utilize the provided data and utilize it to create an exercise in a creative way.
                        All problems in this exercise should refer to the random fact and similar topics.
                        """, randomFact)));
        return this;
    }
}

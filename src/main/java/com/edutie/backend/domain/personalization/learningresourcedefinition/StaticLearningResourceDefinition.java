package com.edutie.backend.domain.personalization.learningresourcedefinition;

import com.edutie.backend.domain.common.base.EducatorCreated;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresourcedefinition.base.LearningResourceDefinitionBase;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Learning Resource Definition is a definition of how Learning Resource should be generated
 * from the knowledge and educational perspective. LRs can be generated using only prompt fragments, but
 * the learning requirements are imperative regarding the LR creation.
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
public class StaticLearningResourceDefinition extends LearningResourceDefinitionBase implements EducatorCreated {
    @ManyToOne(targetEntity = Educator.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    private Educator authorEducator;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected ActivityDetails activityDetails;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected TheoryDetails theoryDetails;

    public static StaticLearningResourceDefinition create(
            Educator educator,
            TheoryDetails theoryDetails,
            ActivityDetails activityDetails,
            Set<LearningRequirement> learningRequirements
    ) {
        StaticLearningResourceDefinition lrd = new StaticLearningResourceDefinition();
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
    public static StaticLearningResourceDefinition create(
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

    @Override
    public DefinitionType getDefinitionType() {
        return DefinitionType.STATIC;
    }
}

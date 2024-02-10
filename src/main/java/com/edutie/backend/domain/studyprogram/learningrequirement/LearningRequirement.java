package com.edutie.backend.domain.studyprogram.learningrequirement;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.studyprogram.creator.Creator;
import com.edutie.backend.domain.studyprogram.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.studyprogram.science.Science;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class LearningRequirement extends AuditableEntityBase<LearningRequirementId> {
    private String name;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "description"))
    private PromptFragment description = new PromptFragment();
    @ManyToOne(targetEntity = Science.class, fetch = FetchType.EAGER)
    private Science science;
    @ManyToOne(targetEntity = Creator.class, fetch = FetchType.EAGER)
    private Creator creator;

    /**
     * Recommended constructor associating Learning Requirement with a creator
     * @param creator creator reference
     * @return Learning Requirement
     */
    public static LearningRequirement create(Creator creator) {
        LearningRequirement learningRequirement = new LearningRequirement();
        learningRequirement.setId(new LearningRequirementId());
        learningRequirement.setCreatedBy(creator.getCreatedBy());
        learningRequirement.setCreator(creator);
        return learningRequirement;
    }

    /**
     * Recommended constructor associating Learning Requirement with creator and science
     * @param creator creator reference
     * @param science science reference
     * @return Learning Requirement
     */
    public static LearningRequirement create(Creator creator, Science science) {
        LearningRequirement learningRequirement = create(creator);
        learningRequirement.setScience(science);
        return learningRequirement;
    }
}

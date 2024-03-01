package com.edutie.backend.domain.education.learningrequirement;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
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
    @ManyToOne(targetEntity = Educator.class, fetch = FetchType.EAGER)
    private Educator educator;

    /**
     * Recommended constructor associating Learning Requirement with a creator
     * @param educator creator reference
     * @return Learning Requirement
     */
    public static LearningRequirement create(Educator educator) {
        LearningRequirement learningRequirement = new LearningRequirement();
        learningRequirement.setId(new LearningRequirementId());
        learningRequirement.setCreatedBy(educator.getCreatedBy());
        learningRequirement.setEducator(educator);
        return learningRequirement;
    }

    /**
     * Recommended constructor associating Learning Requirement with creator and science
     * @param educator creator reference
     * @param science science reference
     * @return Learning Requirement
     */
    public static LearningRequirement create(Educator educator, Science science) {
        LearningRequirement learningRequirement = create(educator);
        learningRequirement.setScience(science);
        return learningRequirement;
    }
}

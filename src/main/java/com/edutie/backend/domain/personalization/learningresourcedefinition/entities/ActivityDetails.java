package com.edutie.backend.domain.personalization.learningresourcedefinition.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.common.Personalizable;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.ActivityDetailsId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ActivityDetails extends EntityBase<ActivityDetailsId> implements Personalizable {
    @AttributeOverride(name = "text", column = @Column(name = "exercise_description", columnDefinition = "TEXT"))
    private PromptFragment exerciseDescription;
    @AttributeOverride(name = "text", column = @Column(name = "hints_description", columnDefinition = "TEXT"))
    private PromptFragment hintsDescription;

    public static ActivityDetails create(PromptFragment exerciseDescription, PromptFragment hintsDescription) {
        ActivityDetails activityDetails = new ActivityDetails();
        activityDetails.setId(new ActivityDetailsId());
        activityDetails.setExerciseDescription(exerciseDescription);
        activityDetails.setHintsDescription(hintsDescription);
        return activityDetails;
    }
}

package com.edutie.backend.domain.personalization.learningresource.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.backend.domain.personalization.learningresource.identities.ActivityId;
import com.edutie.backend.domain.personalization.learningresource.identities.HintId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Activity extends EntityBase<ActivityId> {
    @OneToMany(targetEntity = Hint.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Hint> hints = new HashSet<>();
    @ManyToOne(targetEntity = ExerciseType.class, fetch = FetchType.EAGER)
    private ExerciseType exerciseType;
    private String activityText = "Text of the activity";

    public static Activity create() {
        Activity activity = new Activity();
        activity.setId(new ActivityId());
        return activity;
    }

    public void addHint(String hintText) {
        Hint hint = Hint.create(hintText);
        hints.add(hint);
    }
}

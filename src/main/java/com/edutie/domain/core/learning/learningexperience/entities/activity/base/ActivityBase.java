package com.edutie.domain.core.learning.learningexperience.entities.activity.base;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.learning.learningexperience.identities.ActivityId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class ActivityBase extends EntityBase<ActivityId> implements Activity {

    @JsonProperty
    public String getActivityName() {
        return this.getClass().getSimpleName();
    }
}

package com.edutie.domain.core.learning.learningexperience.entities.activity.base;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.learning.learningexperience.identities.ActivityId;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class ActivityBase extends EntityBase<ActivityId> implements Activity {
}

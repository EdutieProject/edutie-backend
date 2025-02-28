package com.edutie.domain.core.learning.learningexperience.entities.activity.common;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.learning.learningexperience.identities.ActivityId;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ActivityBase extends EntityBase<ActivityId> implements Activity {
}

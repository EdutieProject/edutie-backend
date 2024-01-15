package com.edutie.edutiebackend.dummy;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import jakarta.persistence.Entity;

@Entity
public class SampleEntity extends EntityBase<SampleEntityId> {
    String text;
}

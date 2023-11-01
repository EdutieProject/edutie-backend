package com.edutie.edutiebackend.domain.studyprogram;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.ScienceId;
import jakarta.persistence.Entity;

/**
 * Science entity - the category that each course should have
 */
@Entity
public class Science extends EntityBase<ScienceId> {
    private String name;
}

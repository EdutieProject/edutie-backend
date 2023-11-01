package com.edutie.edutiebackend.domain.studyprogram.entites;

import com.edutie.edutiebackend.domain.common.EntityBase;
import jakarta.persistence.Entity;

/**
 * Science entity - the category that each course should have
 */
@Entity
public class Science extends EntityBase<Science> {
    private String name;
}

package com.edutie.edutiebackend.domain.studyprogram;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.ScienceId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Science entity - the category that each course should have
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Science extends EntityBase<ScienceId> {
    private String name;
    private String description;
}

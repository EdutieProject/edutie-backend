package com.edutie.edutiebackend.domain.core.science;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.science.identities.ScienceId;

import jakarta.persistence.Entity;
import lombok.*;

/**
 * Science entity - the category that each course is assigned to.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Science extends EntityBase<ScienceId> {
    private String name;
    private String description;
}

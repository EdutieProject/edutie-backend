package com.edutie.backend.domain.studyprogram.science;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;

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
@Entity
public class Science extends EntityBase<ScienceId> {
    private String name;
    private String description;
}

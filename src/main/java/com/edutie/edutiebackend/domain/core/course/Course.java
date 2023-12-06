package com.edutie.edutiebackend.domain.core.course;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;
import com.edutie.edutiebackend.domain.core.science.identities.ScienceId;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A group of lessons with a tree-like structure. There are many fundamental lessons, and
 * each of those have a number of lessons assigned as next.
 * Technically a group of Lesson trees.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Course extends AuditableEntityBase<CourseId> {
    private String name;
    private String description;
    private ScienceId scienceId;
    private Boolean accessible = false;
}

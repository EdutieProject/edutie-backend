package com.edutie.edutiebackend.domain.core.course;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;
import com.edutie.edutiebackend.domain.core.science.identities.ScienceId;

import jakarta.persistence.Entity;
import lombok.*;

/**
 * A group of lessons with a tree-like structure. There are many fundamental lessons, and
 * each of those have a number of lessons assigned as next.
 * Technically a Lesson tree
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Course extends AuditableEntityBase<CourseId> {
    private String name;
    private String description;
    private boolean accessible = false;
    // many-to-one relationship
    private ScienceId scienceId;

    /**
     * Recommended constructor for course associating it
     * with given science.
     * @param scienceId science id
     */
    public Course(ScienceId scienceId)
    {
        this.scienceId = scienceId;
    }
}


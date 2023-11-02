package com.edutie.edutiebackend.domain.studyprogram;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.CourseId;
import com.edutie.edutiebackend.domain.common.identities.ScienceId;

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
public class Course extends EntityBase<CourseId> {
    private ScienceId scienceId;
}

package com.edutie.edutiebackend.domain.studyprogram;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.CourseId;
import jakarta.persistence.Entity;

/**
 * A group of lessons with a tree-like structure. There are many fundamental lessons, and
 * each of those have a number of lessons assigned as next.
 * Technically a group of Lesson trees.
 */
@Entity
public class Course extends EntityBase<CourseId> {
}

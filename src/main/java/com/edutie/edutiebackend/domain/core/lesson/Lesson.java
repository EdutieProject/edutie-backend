package com.edutie.edutiebackend.domain.core.lesson;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.studynavigation.LearningTreeNavigator;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;
import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A group of lesson segments with a tree-like structure. All lessons are part
 * of a course referenced by CourseId.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Lesson extends AuditableEntityBase<LessonId> {
    private String name;
    private String description;
    // one-to-many relationship
    private CourseId courseId;
    // Embed learning navigation
    private LearningTreeNavigator<LessonId> navigation;
}

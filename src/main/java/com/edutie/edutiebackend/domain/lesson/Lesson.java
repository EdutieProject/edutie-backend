package com.edutie.edutiebackend.domain.lesson;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.course.identities.CourseId;
import com.edutie.edutiebackend.domain.lesson.identities.LessonId;
import com.edutie.edutiebackend.domain.common.studynavigation.LearningTreeNavigator;
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
public class Lesson extends EntityBase<LessonId> {
    private CourseId courseId;
    private String name;
    private String description;
    private LearningTreeNavigator<LessonId> navigation;
}

package com.edutie.edutiebackend.domain.core.lesson;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.studynavigation.LearningTreeNavigator;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;
import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;

import jakarta.persistence.Entity;
import lombok.*;

/**
 * A group of lesson segments with a tree-like structure. All lessons are part
 * of a course referenced by CourseId.
 */
@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Lesson extends AuditableEntityBase<LessonId> {
    @Setter
    private String name;
    @Setter
    private String description;
    // many-to-one relationship
    private CourseId courseId;
    // Embed learning navigation
    public final LearningTreeNavigator<LessonId> navigation = new LearningTreeNavigator<>();

    /**
     * Recommended constructor assigning lesson to
     * the provided Course.
     * @param courseId course id
     */
    public Lesson(CourseId courseId)
    {
        this.courseId = courseId;
    }
}

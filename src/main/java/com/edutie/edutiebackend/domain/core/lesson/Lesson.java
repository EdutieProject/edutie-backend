package com.edutie.edutiebackend.domain.core.lesson;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.studynavigation.LearningTreeNavigator;
import com.edutie.edutiebackend.domain.core.course.Course;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;
import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

/**
 * A group of lesson segments with a tree-like structure. All lessons are part
 * of a course referenced by CourseId.
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Lesson extends AuditableEntityBase<LessonId> {
    private String name;
    private String description;
    @MapsId("id")
    @ManyToOne(targetEntity = Course.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    @JsonIgnore
    @Setter(AccessLevel.PRIVATE)
    private Course course;
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "course_id"))
    private CourseId courseId;
    @Transient
    public final LearningTreeNavigator<Lesson, LessonId> navigation = new LearningTreeNavigator<>();

    @MapsId("id")
    @ManyToOne(targetEntity = Lesson.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_element_id", updatable = false, insertable = false)
    private Lesson previousElement;
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "previous_element_id"))
    private LessonId previousElementId;

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

package com.edutie.edutiebackend.domain.core.lesson;

import com.edutie.edutiebackend.domain.core.common.base.NavigableEntityBase;
import com.edutie.edutiebackend.domain.core.course.Course;
import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Lesson extends NavigableEntityBase<Lesson, LessonId> {
    private String name;
    private String description;
    @ManyToOne(targetEntity = Course.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    /**
     * Recommended constructor assigning lesson to
     * the provided Course.
     * @param course course entity
     */
    public Lesson(Course course)
    {
        this.course = course;
    }


    /**
     * Adds next element. Does nothing if element is not encompassed within
     * same course.
     * @param lesson
     */
    //TODO: introduce rule ?
    @Override
    public void addNextElement(Lesson lesson) {
        if (lesson.getCourse() != course) return;
        nextElements.add(lesson);
    }


}

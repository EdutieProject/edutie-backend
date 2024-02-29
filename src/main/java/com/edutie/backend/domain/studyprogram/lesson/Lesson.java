package com.edutie.backend.domain.studyprogram.lesson;

import com.edutie.backend.domain.common.base.NavigableEntityBase;
import com.edutie.backend.domain.common.errors.NavigationErrors;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.Result;

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

    @ManyToOne
    private Educator educator;

    /**
     * Recommended constructor associating lesson with a creator
     *
     * @param educator creator profile reference
     * @return Lesson
     */
    public static Lesson create(Educator educator) {
        Lesson lesson = new Lesson();
        lesson.setId(new LessonId());
        lesson.setCreatedBy(educator.getCreatedBy());
        lesson.setEducator(educator);
        return lesson;
    }

    /**
     * Recommended constructor associating Lesson with a creator and course
     *
     * @param educator creator reference
     * @param course  course reference
     * @return Lesson
     */
    public static Lesson create(Educator educator, Course course) {
        Lesson lesson = create(educator);
        lesson.setCourse(course);
        return lesson;
    }


    /**
     * Adds next element. Does nothing if element is not encompassed within
     * same course.
     *
     * @param lesson lesson to be added as next
     */
    @Override
    public Result addNextElement(Lesson lesson) {
        if (lesson.getCourse() != course)
            return Result.failure(NavigationErrors.invalidParentEntity());
        nextElements.add(lesson);
        return Result.success();
    }


}

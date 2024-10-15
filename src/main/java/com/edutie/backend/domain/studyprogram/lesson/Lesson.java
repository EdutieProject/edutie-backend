package com.edutie.backend.domain.studyprogram.lesson;

import com.edutie.backend.api.serialization.serializers.IdOnlySerializer;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.StudyProgramError;
import com.edutie.backend.domain.studyprogram.common.TreeElementEntityBase;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * A group of lesson segments with a tree-like structure. All lessons are part
 * of a course referenced by CourseId.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Lesson extends TreeElementEntityBase<Lesson, LessonId> {
    private String name;
    private String description;
    @OneToMany(mappedBy = "lesson")
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private List<Segment> segments = new ArrayList<>();
    @ManyToOne(targetEntity = Course.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    @Setter(AccessLevel.PRIVATE)
    @JsonSerialize(using = IdOnlySerializer.class)
    private Course course;

    /**
     * Recommended constructor associating Lesson with a creator and course
     *
     * @param educator creator reference
     * @param course   course reference
     * @return Lesson
     */
    public static Lesson create(Educator educator, Course course) {
        Lesson lesson = new Lesson();
        lesson.setId(new LessonId());
        lesson.setCreatedBy(educator.getOwnerUserId());
        lesson.setAuthorEducator(educator);
        lesson.setCourse(course);
        return lesson;
    }

    /**
     * Recommended constructor associating Lesson with a creator, course and a previous lesson
     *
     * @param educator       creator reference
     * @param previousLesson previous lesson reference
     * @return Lesson
     */
    public static Lesson create(Educator educator, Lesson previousLesson) {
        Lesson lesson = new Lesson();
        lesson.setId(new LessonId());
        lesson.setCreatedBy(educator.getOwnerUserId());
        lesson.setAuthorEducator(educator);
        lesson.setCourse(previousLesson.getCourse());
        lesson.setPreviousElement(previousLesson);
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
            return Result.failure(StudyProgramError.invalidParentEntity());
        nextElements.add(lesson);
        return Result.success();
    }

    @Override
    public Result setPreviousElement(Lesson lesson) {
        if (!lesson.getCourse().equals(this.course)) {
            return Result.failure(StudyProgramError.invalidParentEntity());
        }
        this.previousElement = lesson;
        return Result.success();
    }
}

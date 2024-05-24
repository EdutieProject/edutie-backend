package com.edutie.backend.domain.studyprogram.lesson;

import com.edutie.backend.api.serialization.IdOnlySerializer;
import com.edutie.backend.domain.common.base.NavigableEntityBase;
import com.edutie.backend.domain.common.errors.NavigationErrors;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class Lesson extends NavigableEntityBase<Lesson, LessonId> {
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
    @ManyToOne(targetEntity = Educator.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    private Educator educator;

    /**
     * Recommended constructor associating Lesson with a creator and course
     *
     * @param educator creator reference
     * @param course  course reference
     * @return Lesson
     */
    public static Lesson create(Educator educator, Course course) {
        Lesson lesson = new Lesson();
        lesson.setId(new LessonId());
        lesson.setCreatedBy(educator.getOwnerUserId());
        lesson.setEducator(educator);
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

package com.edutie.backend.domain.studyprogram.course;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.science.Science;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * A group of lessons with a tree-like structure. There are many fundamental lessons, and
 * each of those have a number of lessons assigned as next.
 * Technically a Lesson tree
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Course extends AuditableEntityBase<CourseId> {
    private String name;
    private String description;
    private boolean accessible = false;
    @ManyToOne(targetEntity = Educator.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    private Educator educator;
    @ManyToOne(targetEntity = Science.class)
    @JoinColumn(name = "science_id")
    @JsonIgnore
    private Science science;

    /**
     * Recommended constructor associating course with a creator
     * @param educator creator reference
     * @return Course
     */
    public static Course create(Educator educator) {
        Course course = new Course();
        course.setId(new CourseId());
        course.setCreatedBy(educator.getCreatedBy());
        course.setEducator(educator);
        return course;
    }

    /**
     * Recommended constructor associating course with a creator and science
     * @param educator creator reference
     * @param science science reference
     * @return Course
     */
    public static Course create(Educator educator, Science science) {
        Course course = create(educator);
        course.setScience(science);
        return course;
    }
}


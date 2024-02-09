package com.edutie.backend.domain.studyprogram.course;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.creator.Creator;
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
    @ManyToOne(targetEntity = Creator.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    private Creator creator;
    @ManyToOne(targetEntity = Science.class)
    @JoinColumn(name = "science_id")
    @JsonIgnore
    private Science science;

    /**
     * Recommended constructor
     * @param creator creator profile reference
     */
    public static Course create(Creator creator) {
        Course course = new Course();
        course.setId(new CourseId());
        course.setCreatedBy(creator.getCreatedBy());
        course.setCreator(creator);
        return course;
    }
}


package com.edutie.backend.domain.studyprogram.course;

import com.edutie.backend.api.serialization.serializers.IdOnlySerializer;
import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A group of lessons with a tree-like structure. There are many fundamental lessons, and
 * each of those have a number of lessons assigned as next.
 * Technically a Lesson tree
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class Course extends AuditableEntityBase<CourseId> {
    private String name;
    private String description;
    private boolean accessible = false;
    @OneToMany(mappedBy = "course")
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private List<Lesson> lessons = new ArrayList<>();
    @ManyToOne(targetEntity = Educator.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    private Educator educator;
    @ManyToOne(targetEntity = Science.class)
    @JoinColumn(name = "science_id")
    @Setter(AccessLevel.PRIVATE)
    @JsonSerialize(using = IdOnlySerializer.class)
    private Science science;

    /**
     * Recommended constructor associating course with a creator and science
     *
     * @param educator creator reference
     * @param science  science reference
     * @return Course
     */
    public static Course create(Educator educator, Science science) {
        Course course = new Course();
        course.setId(new CourseId());
        course.setCreatedBy(educator.getOwnerUserId());
        course.setEducator(educator);
        course.setScience(science);
        return course;
    }
}


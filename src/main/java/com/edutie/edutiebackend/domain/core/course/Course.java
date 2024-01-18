package com.edutie.edutiebackend.domain.core.course;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;
import com.edutie.edutiebackend.domain.core.science.Science;
import com.edutie.edutiebackend.domain.core.science.identities.ScienceId;

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
    @ManyToOne(targetEntity = Science.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "science_id")
    @JsonIgnore
    private Science science;
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "science_id", insertable = false, updatable = false))
    private ScienceId scienceId;

    /**
     * Recommended constructor for course associating it
     * with given science.
     * @param science science category of the course
     */
    public Course(Science science)
    {
        this.science = science;
    }
}


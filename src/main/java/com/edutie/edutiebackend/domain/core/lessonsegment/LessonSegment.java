package com.edutie.edutiebackend.domain.core.lessonsegment;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.edutiebackend.domain.core.common.studynavigation.LearningTreeNavigator;
import com.edutie.edutiebackend.domain.core.lesson.Lesson;
import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;
import com.edutie.edutiebackend.domain.core.lessonsegment.entities.ExerciseType;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.skill.Skill;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


/**
 * A wrapper around the learning resource. It can manage navigation through navigation property.
 * It has all necessities to provide learning resource by generation or by selection.
 */
@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
public class LessonSegment extends AuditableEntityBase<LessonSegmentId> {
    @Setter
    private String name;
    @Embedded
    public final LearningTreeNavigator<LessonSegment, LessonSegmentId> navigation = new LearningTreeNavigator<>();
//    @Embedded
    @Setter
    private String overviewDescription;
//    @Embedded
    @Setter
    private String exerciseDescription;

    @ManyToOne
    @Setter
    private ExerciseType exerciseType;

    @ManyToMany
    @JsonIgnore
    private final Set<Skill> skills = new HashSet<>();

    @JoinColumn(name = "lesson_id", updatable = false, insertable = false)
    @ManyToOne(targetEntity = Lesson.class, fetch = FetchType.EAGER)
    @JsonIgnore
    private Lesson lesson;
    @Column(name = "lesson_id")
    private LessonId lessonId;

    /**
     * Recommended constructor assigning the segment
     * to the given lesson
     * @param lessonId lesson id
     */
    public LessonSegment(LessonId lessonId)
    {
        this.lessonId = lessonId;
    }


    /**
     * Adds common skill to the common skills list
     * @param skill skill entity
     */
    public void addSkill(Skill skill)
    {
        skills.add(skill);
    }

    /**
     * Removes common skill from the common skills list
     * @param skill skill entity
     */
    public void removeSkill(Skill skill)
    {
        skills.remove(skill);
    }
}

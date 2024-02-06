package com.edutie.edutiebackend.domain.core.lessonsegment;

import com.edutie.edutiebackend.domain.core.common.base.NavigableEntityBase;
import com.edutie.edutiebackend.domain.core.lesson.Lesson;
import com.edutie.edutiebackend.domain.core.lessonsegment.entities.ExerciseType;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.common.errors.NavigationErrors;
import com.edutie.edutiebackend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.edutiebackend.domain.core.skill.Skill;
import com.edutie.edutiebackend.domain.rule.Result;
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
@Entity
//TODO: add learning requirements
public class LessonSegment extends NavigableEntityBase<LessonSegment, LessonSegmentId> {
    @Setter
    private String name;
    @Setter
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "overview_description"))
    private PromptFragment overviewDescription;
    @Setter
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "exercise_description"))
    private PromptFragment exerciseDescription;

    @ManyToOne
    @Setter
    private ExerciseType exerciseType;

    @ManyToMany
    @JsonIgnore
    private final Set<Skill> skills = new HashSet<>();

    @JoinColumn(name = "lesson_id")
    @ManyToOne(targetEntity = Lesson.class, fetch = FetchType.EAGER)
    @JsonIgnore
    @Setter
    private Lesson lesson;

    /**
     * Recommended constructor assigning the segment
     * to the given lesson
     * @param lesson lesson
     */
    public LessonSegment(Lesson lesson)
    {
        this.lesson = lesson;
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

    /**
     * @param lessonSegment
     */
    @Override
    public Result addNextElement(LessonSegment lessonSegment) {
        if(lessonSegment.getLesson() != lesson)
            return Result.failure(NavigationErrors.elementNotFound(this.getClass()));
        nextElements.add(lessonSegment);
        return Result.success();
    }
}

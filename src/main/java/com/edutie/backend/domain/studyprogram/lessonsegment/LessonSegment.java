package com.edutie.backend.domain.studyprogram.lessonsegment;

import com.edutie.backend.domain.common.base.NavigableEntityBase;
import com.edutie.backend.domain.common.errors.NavigationErrors;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.studyprogram.creator.Creator;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.psychology.skill.Skill;
import com.edutie.backend.domain.studyprogram.lessonsegment.entities.ExerciseType;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;
import validation.Result;
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
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
//TODO: add learning requirements
public class LessonSegment extends NavigableEntityBase<LessonSegment, LessonSegmentId> {
    private String name;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "overview_description"))
    private PromptFragment overviewDescription;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "exercise_description"))
    private PromptFragment exerciseDescription;
    @ManyToOne
    private ExerciseType exerciseType;
    @ManyToMany
    @JsonIgnore
    private final Set<Skill> skills = new HashSet<>();
    @JoinColumn(name = "lesson_id")
    @ManyToOne(targetEntity = Lesson.class, fetch = FetchType.EAGER)
    @JsonIgnore
    @Setter(AccessLevel.PRIVATE)
    private Lesson lesson;
    @ManyToOne
    @Setter(AccessLevel.PRIVATE)
    private Creator creator;

    /**
     * Recommended constructor associating Lesson Segment with a creator
     * @param creator creator profile reference
     * @return Lesson Segment
     */
    public static LessonSegment create(Creator creator) {
        LessonSegment lessonSegment = new LessonSegment();
        lessonSegment.setId(new LessonSegmentId());
        lessonSegment.setCreatedBy(creator.getCreatedBy());
        lessonSegment.setCreator(creator);
        return lessonSegment;
    }

    /**
     * Recommended constructor associating Lesson Segment with a creator and lesson
     * @param creator creator reference
     * @param lesson lesson reference
     * @return Lesson Segment
     */
    public static LessonSegment create(Creator creator, Lesson lesson) {
        LessonSegment lessonSegment = create(creator);
        lessonSegment.setLesson(lesson);
        return lessonSegment;
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

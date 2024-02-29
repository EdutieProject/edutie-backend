package com.edutie.backend.domain.studyprogram.lessonsegment;

import com.edutie.backend.domain.common.base.NavigableEntityBase;
import com.edutie.backend.domain.common.errors.NavigationErrors;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.psychology.skill.Skill;
import com.edutie.backend.domain.studyprogram.creator.Creator;
import com.edutie.backend.domain.studyprogram.exercisetype.ExerciseType;
import com.edutie.backend.domain.studyprogram.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import validation.Result;

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
    //TODO: remove skill references
    private final Set<Skill> skills = new HashSet<>();
    @ManyToMany
    @JsonIgnore
    private final Set<LearningRequirement> learningRequirements = new HashSet<>();
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
     *
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
     *
     * @param creator creator reference
     * @param lesson  lesson reference
     * @return Lesson Segment
     */
    public static LessonSegment create(Creator creator, Lesson lesson) {
        LessonSegment lessonSegment = create(creator);
        lessonSegment.setLesson(lesson);
        return lessonSegment;
    }


    /**
     * Adds skill association
     *
     * @param skill skill entity
     */
    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    /**
     * Removes skill association
     *
     * @param skill skill entity
     */
    public void removeSkill(Skill skill) {
        skills.remove(skill);
    }

    /**
     * Adds learning requirement association
     *
     * @param learningRequirement learning requirement
     */
    public void addLearningRequirement(LearningRequirement learningRequirement) {
        learningRequirements.add(learningRequirement);
    }

    /**
     * Removes learning requirement association
     *
     * @param learningRequirement learning requirement
     */
    public void removeLearningRequirement(LearningRequirement learningRequirement) {
        learningRequirements.remove(learningRequirement);
    }

    /**
     * Adds next element to the lesson segment tree
     *
     * @param lessonSegment segment to be added as next
     * @return Result of the operation
     */
    @Override
    public Result addNextElement(LessonSegment lessonSegment) {
        if (lessonSegment.getLesson() != lesson)
            return Result.failure(NavigationErrors.invalidParentEntity());
        nextElements.add(lessonSegment);
        return Result.success();
    }
}

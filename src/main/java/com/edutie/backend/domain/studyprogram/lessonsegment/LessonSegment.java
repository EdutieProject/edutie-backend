package com.edutie.backend.domain.studyprogram.lessonsegment;

import com.edutie.backend.domain.common.base.NavigableEntityBase;
import com.edutie.backend.domain.common.errors.NavigationErrors;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.skill.Skill;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import validation.Result;

import java.util.HashSet;
import java.util.Set;


/**
 * A segment of a lesson. Most atomic part of learning which is responsible for describing the goals
 * and requirements for the student to make. Segment is responsible for providing the student with the
 * learning resource adjusted for their needs.
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
    private Educator educator;

    /**
     * Recommended constructor associating Lesson Segment with a creator
     *
     * @param educator creator profile reference
     * @return Lesson Segment
     */
    public static LessonSegment create(Educator educator) {
        LessonSegment lessonSegment = new LessonSegment();
        lessonSegment.setId(new LessonSegmentId());
        lessonSegment.setCreatedBy(educator.getCreatedBy());
        lessonSegment.setEducator(educator);
        return lessonSegment;
    }

    /**
     * Recommended constructor associating Lesson Segment with a creator and lesson
     *
     * @param educator creator reference
     * @param lesson  lesson reference
     * @return Lesson Segment
     */
    public static LessonSegment create(Educator educator, Lesson lesson) {
        LessonSegment lessonSegment = create(educator);
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

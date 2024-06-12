package com.edutie.backend.domain.studyprogram.segment;

import com.edutie.backend.domain.common.base.TreeElementEntityBase;
import com.edutie.backend.domain.common.errors.NavigationErrors;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class Segment extends TreeElementEntityBase<Segment, SegmentId> {
    private String name;
    private String snippetDescription;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "theory_description"))
    @JsonIgnore
    private PromptFragment theoryDescription;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "exercise_description"))
    @JsonIgnore
    private PromptFragment exerciseDescription;
    @ManyToOne
    private ExerciseType exerciseType;
    @ManyToMany
    @JsonIgnore
    private final Set<LearningRequirement> learningRequirements = new HashSet<>();
    @JoinColumn(name = "lesson_id")
    @ManyToOne(targetEntity = Lesson.class, fetch = FetchType.EAGER)
    @JsonIgnore
    @Setter(AccessLevel.PRIVATE)
    private Lesson lesson;

    /**
     * Recommended constructor associating Lesson Segment with a creator and lesson
     *
     * @param educator creator reference
     * @param lesson   lesson reference
     * @return Lesson Segment
     */
    public static Segment create(Educator educator, Lesson lesson) {
        Segment segment = new Segment();
        segment.setId(new SegmentId());
        segment.setCreatedBy(educator.getOwnerUserId());
        segment.setEducator(educator);
        segment.setLesson(lesson);
        return segment;
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
     * @param segment segment to be added as next
     * @return Result of the operation
     */
    @Override
    public Result addNextElement(Segment segment) {
        if (segment.getLesson() != lesson)
            return Result.failure(NavigationErrors.invalidParentEntity());
        nextElements.add(segment);
        return Result.success();
    }

    @Override
    public Result setPreviousElement(Segment segment) {
        if (!segment.getLesson().equals(this.lesson)) {
            return Result.failure(NavigationErrors.invalidParentEntity());
        }
        this.previousElement = segment;
        return Result.success();
    }
}

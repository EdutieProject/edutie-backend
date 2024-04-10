package com.edutie.backend.domain.learner.learningresult;

import com.edutie.backend.domain.common.Utilities;
import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.learner.learningresult.entities.LearningAssessment;
import com.edutie.backend.domain.learner.learningresult.entities.base.Assessment;
import com.edutie.backend.domain.learner.learningresult.errors.LearningResultErrors;
import com.edutie.backend.domain.learner.learningresult.identities.AssessmentId;
import com.edutie.backend.domain.learner.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.learner.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.studyprogram.lessonsegment.Segment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import validation.Result;

import java.util.HashSet;
import java.util.Set;

/**
 * A result of learning activities that is used
 * to adapt students learning parameters. Even though it is
 * adjusted for modifications, it should stay immutable and be
 * used only as a learning history fragment.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningResult extends AuditableEntityBase<LearningResultId> {
    @ManyToOne(targetEntity = Segment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_segment_id")
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private Segment segment;
    @Setter(AccessLevel.PRIVATE)
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;
    private String reportText;
    @Embedded
    private Feedback feedback = new Feedback();
    @OneToMany(targetEntity = LearningAssessment.class)
    private final Set<LearningAssessment> learningAssessments = new HashSet<>();

    /**
     * Recommended constructor associating learning result with Student and lesson segment
     *
     * @param student       student reference
     * @param segment lesson segment reference
     * @return new Learning Result
     */
    public static LearningResult create(Student student, Segment segment) {
        LearningResult learningResult = new LearningResult();
        learningResult.setId(new LearningResultId());
        learningResult.setCreatedBy(student.getCreatedBy());
        learningResult.setStudent(student);
        learningResult.setSegment(segment);
        return learningResult;
    }

    /**
     * Retrieves all assessments associated with this learning result
     *
     * @return Assessment set
     */
    public Set<Assessment<?>> getAllAssessments() {
        return new HashSet<>(learningAssessments);
    }

    /**
     * Adds provided assessment to the learning result.
     *
     * @param assessment      assessment entity
     * @param assessmentClass assessment class
     * @param <T>             concrete type of assessment
     * @return Result object
     */
    //TODO: Implement validation after #31 VALIDATION FRAMEWORK REFACTORING
    public <T extends Assessment<?>> Result addAssessment(T assessment, Class<T> assessmentClass) {
        var assessmentSet = Utilities.findSetOf(assessmentClass, this).orElseThrow();
        var searchedAssessment = assessmentSet.stream().filter(o -> o.getEntity() == assessment.getEntity()).findFirst();
        searchedAssessment.ifPresent(assessmentSet::remove);
        assessmentSet.add(assessment);
        return Result.success();
    }

    /**
     * Constructs an assessment based on provided data and bounds it with this learning result.
     *
     * @param assessedEntity   entity which is assessed eg. Skill or learning requirement
     * @param assessmentPoints assessment points
     * @param assessmentClass  concrete class of assessment
     * @param <T>              type of assessed entity
     * @param <U>              type of assessment
     * @return Result object
     */
    @SneakyThrows
    public <T, U extends Assessment<T>> Result addAssessment(T assessedEntity, int assessmentPoints, Class<U> assessmentClass) {
        var newAssessment = assessmentClass.getConstructor().newInstance();
        newAssessment.setAssessmentPoints(assessmentPoints);
        newAssessment.setEntity(assessedEntity);
        newAssessment.setId(new AssessmentId());
        return addAssessment(newAssessment, assessmentClass);
    }

    /**
     * Removes learning requirement assessment
     *
     * @param learningRequirement learning requirement reference
     * @return Result object
     */
    public Result removeAssessment(LearningRequirement learningRequirement) {
        var searchedAssessment = learningAssessments.stream().filter(o -> o.getEntity() == learningRequirement).findFirst();
        searchedAssessment.ifPresent(learningAssessments::remove);
        return searchedAssessment.isPresent() ? Result.success() : Result.failure(LearningResultErrors.noAssessmentFound());
    }


}

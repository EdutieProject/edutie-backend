package com.edutie.backend.domain.personalization.learningresult;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

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
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class LearningResult extends AuditableEntityBase<LearningResultId> {
    @OneToMany(targetEntity = Assessment.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final Set<Assessment> assessments = new HashSet<>();
    @Embedded
    private Feedback feedback = new Feedback();
    @ManyToOne(targetEntity = SolutionSubmission.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private SolutionSubmission solutionSubmission;
    @Setter(AccessLevel.PRIVATE)
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    /**
     * Recommended constructor associating learning result with Student and solution submission
     *
     * @param solutionSubmission solution submission reference
     * @param feedback           feedback
     * @param assessments        assessments
     * @return new Learning Result
     */
    public static LearningResult create(
            SolutionSubmission solutionSubmission,
            Feedback feedback,
            Set<Assessment> assessments
    ) {
        LearningResult learningResult = new LearningResult();
        learningResult.setId(new LearningResultId());
        learningResult.setCreatedBy(solutionSubmission.getStudent().getOwnerUserId());
        learningResult.setStudent(solutionSubmission.getStudent());
        learningResult.setSolutionSubmission(solutionSubmission);
        learningResult.setFeedback(feedback);
        learningResult.assessments.addAll(assessments);
        return learningResult;
    }

    /**
     * Adds assessment to this Learning Result
     *
     * @param assessment assessment to be added
     */
    public void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }

    /**
     * Getter added mainly for json serialization purposes. Retrieves associated LRD id
     *
     * @return Learning Resource Definition Identifier
     */
    @JsonProperty("learningResourceDefinitionId")
    public LearningResourceDefinitionId getLearningResourceDefinitionId() {
        return solutionSubmission.getLearningResource().getDefinitionId();
    }

    /**
     * Getter added mainly for serialization purposes. Retrieves LRD type.
     *
     * @return LRD type.
     */
    @JsonProperty("learningResourceDefinitionType")
    public DefinitionType getLearningResourceDefinitionType() {
        return solutionSubmission.getLearningResource().getDefinitionType();
    }

    /**
     * Indicates whether learning result is successful based on the grade that needs to be met in order
     * to be successful
     *
     * @return boolean true/false
     */
    public boolean isSuccessful() {
        return this.getAssessments().stream().allMatch(a -> a.getGrade().greaterThanOrEqual(Grade.SUCCESS_GRADE));
    }

    /**
     * Returns the average grade as a double
     *
     * @return average grade as double
     */
    @JsonProperty("averageGrade")
    public double getAverageGrade() {
        return (double) assessments.stream().map(o -> o.getGrade().gradeNumber()).mapToInt(Integer::intValue).sum() / assessments.size();
    }

}

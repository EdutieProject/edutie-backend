package com.edutie.backend.domain.personalization.learningresult;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
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
import java.util.stream.Collectors;

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
     * Indicates whether learning result is successful based on the grade that needs to be met in order
     * to be successful
     *
     * @return boolean true/false
     */
    public boolean isSuccessful() {
        return this.getAssessments().stream().allMatch(a -> a.getGrade().greaterThanOrEqual(Grade.SUCCESS_GRADE));
    }

    /**
     * Retrieve ids of associated learning requirements.
     *
     * @return set of learning requirement ids
     */
    public Set<LearningRequirementId> getLearningRequirementIds() {
        return assessments.stream().map(Assessment::getLearningRequirementId).collect(Collectors.toSet());
    }

    /**
     * Retrieves a set of associated learning requirements.
     *
     * @return Set of learning requirements associated with the L. Resource of the result.
     */
    public Set<LearningRequirement> getAssociatedLearningRequirements() {
        return assessments.stream()
                .flatMap(o -> o.getQualifiedElementalRequirements().stream())
                .map(ElementalRequirement::getLearningRequirement)
                .collect(Collectors.toSet());
    }

    public boolean hasOverlappingLearningRequirements(LearningResult otherResult) {
        return this.getLearningRequirementIds().stream().anyMatch(otherResult.getLearningRequirementIds()::contains);
    }

    /**
     * Returns the average grade as a double
     *
     * @return average grade as double
     */
    @JsonProperty("averageGrade")
    public double getAverageGradeAsDouble() {
        return (double) assessments.stream().map(o -> o.getGrade().gradeNumber()).mapToInt(Integer::intValue).sum() / assessments.size();
    }

    @JsonProperty("averageGradeRounded")
    public Grade getAverageGrade() {
        return new Grade((int) Math.round(getAverageGradeAsDouble()));
    }

    public LearningResourceId getAssociatedLearningResourceId() {
        return solutionSubmission.getLearningResourceId();
    }
}

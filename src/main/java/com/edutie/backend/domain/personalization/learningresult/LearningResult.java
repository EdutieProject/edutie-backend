package com.edutie.backend.domain.personalization.learningresult;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningResult extends AuditableEntityBase<LearningResultId> {
    @ManyToOne(targetEntity = SolutionSubmission.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private SolutionSubmission solutionSubmission;
    @Setter(AccessLevel.PRIVATE)
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;
    @Embedded
    private Feedback feedback = new Feedback();
    @OneToMany(targetEntity = Assessment.class, fetch = FetchType.EAGER)
    private final Set<Assessment> assessments = new HashSet<>();

    /**
     * Recommended constructor associating learning result with Student and solution submission
     *
     * @param student student reference
     * @param solutionSubmission solution submission reference
     * @return new Learning Result
     */
    public static LearningResult create(
            Student student,
            SolutionSubmission solutionSubmission,
            Feedback feedback
    ) {
        LearningResult learningResult = new LearningResult();
        learningResult.setId(new LearningResultId());
        learningResult.setCreatedBy(student.getOwnerUserId());
        learningResult.setStudent(student);
        learningResult.setSolutionSubmission(solutionSubmission);
        learningResult.setFeedback(feedback);
        return learningResult;
    }

    public void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }

}

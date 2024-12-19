package com.edutie.backend.domain.personalization.solutionsubmission;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.personalization.common.AbsoluteDefinition;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.solutionsubmission.identities.SolutionSubmissionId;
import com.edutie.backend.domain.personalization.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A submitted solution for the learning resource.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SolutionSubmission extends AuditableEntityBase<SolutionSubmissionId> implements AbsoluteDefinition {
    private int hintsRevealed = 0;
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private Student student;
    @AttributeOverride(name = "identifierValue", column = @Column(name = "learning_resource_id"))
    private LearningResourceId learningResourceId;
    @Column(columnDefinition = "TEXT")
    private String reportText;

    public static SolutionSubmission create(
            Student student,
            LearningResourceId learningResourceId,
            String reportText,
            int hintsRevealed
    ) {
        SolutionSubmission solutionSubmission = new SolutionSubmission();
        solutionSubmission.setId(new SolutionSubmissionId());
        solutionSubmission.setCreatedBy(student.getOwnerUserId());
        solutionSubmission.setStudent(student);
        solutionSubmission.setLearningResourceId(learningResourceId);
        solutionSubmission.setHintsRevealed(hintsRevealed);
        solutionSubmission.setReportText(reportText);
        return solutionSubmission;
    }
}

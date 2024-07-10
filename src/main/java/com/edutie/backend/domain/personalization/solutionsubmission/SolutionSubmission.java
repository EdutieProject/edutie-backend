package com.edutie.backend.domain.personalization.solutionsubmission;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.solutionsubmission.identities.SolutionSubmissionId;
import com.edutie.backend.domain.personalization.student.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class SolutionSubmission extends AuditableEntityBase<SolutionSubmissionId> {
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    private Student student;
    @ManyToOne(targetEntity = LearningResourceDefinition.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    private LearningResourceDefinition learningResourceDefinition;
    private String reportText = "";
    private int hintsRevealed = 0;

    public static SolutionSubmission create(Student student, LearningResourceDefinition learningResourceDefinition, String reportText, int hintsRevealed) {
        SolutionSubmission solutionSubmission = new SolutionSubmission();
        solutionSubmission.setId(new SolutionSubmissionId());
        solutionSubmission.setCreatedBy(student.getOwnerUserId());
        solutionSubmission.setStudent(student);
        solutionSubmission.setLearningResourceDefinition(learningResourceDefinition);
        solutionSubmission.setHintsRevealed(hintsRevealed);
        solutionSubmission.setReportText(reportText);
        return solutionSubmission;
    }
}

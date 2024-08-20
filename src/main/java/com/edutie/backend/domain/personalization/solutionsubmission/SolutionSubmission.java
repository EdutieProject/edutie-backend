package com.edutie.backend.domain.personalization.solutionsubmission;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.solutionsubmission.identities.SolutionSubmissionId;
import com.edutie.backend.domain.personalization.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne(targetEntity = LearningResource.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private LearningResource learningResource;
    private String reportText;
    private int hintsRevealed = 0;

    public static SolutionSubmission create(Student student, LearningResource learningResource, String reportText, int hintsRevealed) {
        SolutionSubmission solutionSubmission = new SolutionSubmission();
        solutionSubmission.setId(new SolutionSubmissionId());
        solutionSubmission.setCreatedBy(student.getOwnerUserId());
        solutionSubmission.setStudent(student);
        solutionSubmission.setLearningResource(learningResource);
        solutionSubmission.setHintsRevealed(hintsRevealed);
        solutionSubmission.setReportText(reportText);
        return solutionSubmission;
    }
}

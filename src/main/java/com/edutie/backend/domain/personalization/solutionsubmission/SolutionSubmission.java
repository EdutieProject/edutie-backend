package com.edutie.backend.domain.personalization.solutionsubmission;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.solutionsubmission.identities.SolutionSubmissionId;
import com.edutie.backend.domain.personalization.student.Student;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

/**
 * A submitted solution for the learning resource.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SolutionSubmission extends AuditableEntityBase<SolutionSubmissionId> {
	private int hintsRevealed = 0;
	@ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
	@Setter(AccessLevel.PRIVATE)
	@JsonIgnore
	private Student student;
	@ManyToOne(targetEntity = LearningResource.class, fetch = FetchType.EAGER)
	@Setter(AccessLevel.PRIVATE)
	@JsonIgnore
	private LearningResource learningResource;
	@Column(columnDefinition = "TEXT")
	private String reportText;

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

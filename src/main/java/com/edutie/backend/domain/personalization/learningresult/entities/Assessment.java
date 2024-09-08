package com.edutie.backend.domain.personalization.learningresult.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresult.identities.AssessmentId;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Assessment extends EntityBase<AssessmentId> {
	@Embedded
	@AttributeOverride(name = "identifierValue", column = @Column(name = "learning_requirement_id"))
	LearningRequirementId learningRequirementId;
	@Embedded
	@AttributeOverride(name = "gradeNumber", column = @Column(name = "grade_number"))
	Grade grade;

	public static Assessment create(LearningRequirementId learningRequirementId, Grade grade) {
		Assessment assessment = new Assessment();
		assessment.setId(new AssessmentId());
		assessment.setLearningRequirementId(learningRequirementId);
		assessment.setGrade(grade);
		return assessment;
	}

}

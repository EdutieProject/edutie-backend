package com.edutie.backend.infrastucture.llm.dto.learningresult;

import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.fasterxml.jackson.annotation.*;

public class AssessmentDto {
	@JsonProperty
	LearningRequirementId learningRequirementId;
	@JsonProperty
	int gradeNumber;
}

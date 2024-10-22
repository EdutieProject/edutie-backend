package com.edutie.backend.infrastucture.external.llm.dto.learningresource;

import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.fasterxml.jackson.annotation.*;

public class ProblemDetailDto {
	@JsonProperty
	LearningRequirementId learningRequirementId;
	@JsonProperty
	int qualifiedSubRequirements;
}

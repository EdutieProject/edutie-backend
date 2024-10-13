package com.edutie.backend.infrastucture.llm.dto.learningresource;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.*;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.fasterxml.jackson.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO used for communication with LLM service. This DTO is the published language entity used between
 * LLM service and Edutie Backend for Learning Resource creation
 */
public class LearningResourceCreationDto {
	@JsonProperty
	private String activityText;
	@JsonProperty
	private String theoryOverviewText;
	@JsonProperty
	private String mermaidGraphString;
	@JsonProperty
	private Set<String> hints;

	/**
	 * Converts DTO into domain Learning Resource entity
	 *
	 * @param generationSchema L.R.G.S.
	 * @return Learning Resource
	 */
	public LearningResource intoLearningResource(LearningResourceGenerationSchema generationSchema) {
		Set<Hint> hints = this.hints.stream().map(Hint::create).collect(Collectors.toSet());
		return LearningResource.create(generationSchema, Activity.create(activityText, hints), Theory.create(theoryOverviewText, mermaidGraphString));
	}
}

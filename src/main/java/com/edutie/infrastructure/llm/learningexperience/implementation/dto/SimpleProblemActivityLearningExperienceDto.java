package com.edutie.infrastructure.llm.learningexperience.implementation.dto;

import com.edutie.domain.core.common.paragraph.TextContent;
import com.edutie.domain.core.common.paragraph.TextContentType;
import com.edutie.domain.core.learning.learningexperience.entities.activity.SimpleProblemActivity;
import com.edutie.domain.core.learning.learningexperience.entities.notes.LearningNotes;
import com.edutie.domain.core.learning.learningexperience.implementations.SimpleProblemActivityLearningExperience;
import com.edutie.domain.core.learning.learningexperience.valueobjects.Visualisation;
import com.edutie.domain.core.learning.learningexperience.valueobjects.VisualisationType;
import com.edutie.infrastructure.common.ExternalServiceDto;
import com.edutie.infrastructure.llm.learningexperience.schema.LearningExperienceGenerationSchema;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class SimpleProblemActivityLearningExperienceDto implements ExternalServiceDto<SimpleProblemActivityLearningExperience, LearningExperienceGenerationSchema> {
    @JsonProperty
    private String notesText;
    @JsonProperty
    private String mermaidGraphString;
    @JsonProperty
    private String activityIntroductionText;
    @JsonProperty
    private String simpleProblemText;

    @Override
    public SimpleProblemActivityLearningExperience intoDomainEntity(LearningExperienceGenerationSchema schema) {
        SimpleProblemActivityLearningExperience learningExperience = SimpleProblemActivityLearningExperience.create(
                schema.student(),
                SimpleProblemActivity.create(activityIntroductionText, simpleProblemText),
                LearningNotes.create(new TextContent(TextContentType.MARKDOWN, notesText), new Visualisation(VisualisationType.MERMAID, mermaidGraphString)),
                Set.of(schema.elementalRequirement())
        );
        return learningExperience;
    }
}

package com.edutie.backend.domain.personalization.learningresourcegenerationschema;

import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities.PersonalizationRule;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.student.Student;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class LearningResourceGenerationSchema {
    private LearningResourceDefinition learningResourceDefinition;
    private List<PersonalizationRule> personalizationRules = new ArrayList<>();

    public static LearningResourceGenerationSchema create(LearningResourceDefinition learningResourceDefinition) {
        LearningResourceGenerationSchema learningResourceGenerationSchema = new LearningResourceGenerationSchema();
        learningResourceGenerationSchema.setLearningResourceDefinition(learningResourceDefinition);
        return learningResourceGenerationSchema;
    }

    public void addPersonalizationRule(PersonalizationRule personalizationRule) {
        personalizationRules.add(personalizationRule);
    }
}

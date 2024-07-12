package com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities;

import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProblemDescriptor {
    private final LearningRequirementId learningRequirementId;
    private final int qualifiedSubRequirements = 0;
    private final List<PersonalizationRule> personalizationRules = new ArrayList<>();

    public ProblemDescriptor(LearningRequirementId learningRequirementId) {
        this.learningRequirementId = learningRequirementId;
    }
}

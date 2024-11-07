package com.edutie.backend.domain.personalization.rule.selectionengine;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.infrastructure.external.knowledgemap.KnowledgeMapService;

import java.util.Set;

public class PersonalizationRuleSelectionEngine {
    private final Student student;
    private final KnowledgeMapService knowledgeMapService;

    public PersonalizationRuleSelectionEngine(Student student, KnowledgeMapService knowledgeMapService) {
        this.student = student;
        this.knowledgeMapService = knowledgeMapService;
    }

    public Set<PersonalizationRule<?>> chooseRulesForRequirements(Set<LearningRequirement> learningRequirements) {
        return Set.of();
    }
}

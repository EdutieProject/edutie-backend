package com.edutie.backend.mocks;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;

import java.util.Set;


public class LearningResourceMocks {

    public static LearningResourceDefinition sampleLearningResourceDefinition(Educator educator) {
        return LearningResourceDefinition.create(educator,
                PromptFragment.of("Sample theory description."),
                PromptFragment.of("Sample activity educator followups"),
                Set.of(EducationMocks.independentLearningRequirement(educator))
        );
    }

    public static LearningResourceGenerationSchema sampleLearningResourceGenerationSchema(Student student, LearningResultPersistence learningResultPersistence, Educator educator) {
        return LearningResourceGenerationSchema.create(
                student,
                learningResultPersistence,
                sampleLearningResourceDefinition(educator), Set.of()
        );
    }

    public static LearningResource sampleLearningResource(Student student, LearningResultPersistence learningResultPersistence, Educator educator) {
                return LearningResource.create(
                sampleLearningResourceGenerationSchema(student, learningResultPersistence, educator),
                Activity.create("Hello", Set.of(Hint.create("Wrld!"))),
                Theory.create("Theory overview", "mermaid diagram!")
        );
    }
}

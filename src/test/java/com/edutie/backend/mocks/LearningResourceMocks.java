package com.edutie.backend.mocks;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.TheoryCard;
import com.edutie.backend.domain.personalization.learningresource.valueobjects.Visualisation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.DynamicLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;

import java.util.List;
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

    public static LearningResource sampleLearningResource(Student student, Educator educator) {
        LearningRequirement learningRequirement = EducationMocks.independentLearningRequirement(educator);
        DynamicLearningResourceDefinition learningResourceDefinition = DynamicLearningResourceDefinition.createRandomFact("Czy wiesz, że sok z brzozy, zwany bzowiną, jest od wieków wykorzystywany w medycynie naturalnej? Jest bogaty w witaminy i minerały!", Set.of(learningRequirement));

        return LearningResource.create(
                student,
                learningResourceDefinition,
                learningRequirement.calculateQualifiedElementalRequirements(List.of(), 2),
                Activity.create("Wykorzystanie brzozy w gospodarce - analiza rynkowa soku z brzozy. Aktywność...", Set.of()),
                Set.of(TheoryCard.create(learningRequirement.getId(), "Dzięki temu brzozowa kora była wykorzystywana przez rdzennych mieszkańców Ameryki Północnej do budowy wodoodpornych canoe oraz jako materiał do pisania w Europie i Azji.")),
                new Visualisation("graph TD A->B")
        );
    }
}

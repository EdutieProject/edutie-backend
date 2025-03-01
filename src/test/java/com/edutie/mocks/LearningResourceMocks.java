package com.edutie.mocks;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.activity.base.ActivityBase;
import com.edutie.domain.core.learning.learningexperience.valueobjects.Visualisation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.DynamicLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DynamicContextType;
import com.edutie.backend.domain.personalization.learningresourcedefinition.valueobjects.DynamicContext;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.service.personalization.learningresource.schema.LearningResourceGenerationSchema;

import java.util.List;
import java.util.Set;


public class LearningResourceMocks {

    public static StaticLearningResourceDefinition sampleLearningResourceDefinition(Educator educator) {
        return StaticLearningResourceDefinition.create(educator,
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

    public static LearningExperience sampleLearningResource(Student student, Educator educator) {
        LearningSubject learningSubject = EducationMocks.independentLearningRequirement(educator);
        DynamicLearningResourceDefinition learningResourceDefinition = DynamicLearningResourceDefinition.create(
                new DynamicContext(PromptFragment.of("Czy wiesz, że sok z brzozy, zwany bzowiną, jest od wieków wykorzystywany w medycynie naturalnej? Jest bogaty w witaminy i minerały!"), DynamicContextType.RANDOM_FACT),
                Set.of(learningSubject)
        );

        return LearningExperience.create(
                student,
                learningResourceDefinition,
                learningSubject.calculateQualifiedElementalRequirements(List.of(), 2),
                ActivityBase.create("Wykorzystanie brzozy w gospodarce - analiza rynkowa soku z brzozy. Aktywność...", Set.of()),
                Set.of(TheoryCard.create(learningSubject.getId(), "Dzięki temu brzozowa kora była wykorzystywana przez rdzennych mieszkańców Ameryki Północnej do budowy wodoodpornych canoe oraz jako materiał do pisania w Europie i Azji.")),
                new Visualisation("graph TD A->B")
        );
    }
}

package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleLawOfSinesLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);

        learningRequirement.appendSubRequirement(
                "Uczeń rozumie treść Twierdzenia Sinusów i jego zastosowanie w trójkątach.",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi zapisać Twierdzenie Sinusów jako \\( \\frac{a}{\\sin(A)} = \\frac{b}{\\sin(B)} = \\frac{c}{\\sin(C)} \\).",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna zastosowanie Twierdzenia Sinusów do obliczania długości boków w trójkącie, gdy dane są kąty i jeden bok.",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi używać Twierdzenia Sinusów do znajdowania miar kątów w trójkącie, gdy dane są długości boków i przynajmniej jeden kąt.",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie zastosowanie Twierdzenia Sinusów w przypadku trójkątów rozwartokątnych.",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi rozwiązywać zadania tekstowe związane z Twierdzeniem Sinusów, w tym z zastosowaniem w rzeczywistych sytuacjach geometrycznych.",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );

        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleLawOfSinesLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Twierdzenie Sinusów";

    public static final String SUB_REQUIREMENT_1 = """
            ### Treść Twierdzenia Sinusów
            Twierdzenie Sinusów mówi, że w dowolnym trójkącie stosunek długości boku do sinusa przeciwległego kąta jest stały:
            \\[
            \\frac{a}{\\sin(A)} = \\frac{b}{\\sin(B)} = \\frac{c}{\\sin(C)}
            \\]
            Gdzie \\(a, b, c\\) to długości boków trójkąta, a \\(A, B, C\\) to odpowiednie kąty.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Zapis Twierdzenia Sinusów
            Twierdzenie można wyrazić w równaniu:
            \\[
            \\frac{a}{\\sin(A)} = \\frac{b}{\\sin(B)} = \\frac{c}{\\sin(C)}
            \\]
            Ten zapis jest kluczowy do zastosowań obliczeniowych w geometrii.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Zastosowanie do obliczania długości boków
            Jeśli znasz miary dwóch kątów i długość jednego boku, możesz obliczyć długości pozostałych boków w trójkącie za pomocą Twierdzenia Sinusów.
            Przykład:
            \\[
            a = \\frac{c \\cdot \\sin(A)}{\\sin(C)}
            \\]
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Zastosowanie do obliczania kątów
            Twierdzenie Sinusów pozwala obliczać kąty w trójkącie, jeśli znasz długości dwóch boków i miarę jednego kąta.
            Przykład:
            \\[
            \\sin(A) = \\frac{a \\cdot \\sin(C)}{c}
            \\]
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Twierdzenie Sinusów w trójkątach rozwartokątnych
            Twierdzenie Sinusów ma zastosowanie również w trójkątach rozwartokątnych, ponieważ funkcja sinus jest dodatnia dla kątów od \\(0^\\circ\\) do \\(180^\\circ\\).
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Zadania tekstowe z Twierdzeniem Sinusów
            Przykład zastosowania:
            - Znalezienie odległości między dwoma punktami na podstawie kątów i długości odcinka obserwacyjnego.
            - Obliczanie wysokości trójkąta w kontekście zastosowań praktycznych, np. w budownictwie lub kartografii.
            """;
}

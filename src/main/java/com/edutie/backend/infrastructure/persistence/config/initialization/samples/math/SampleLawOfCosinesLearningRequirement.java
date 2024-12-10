package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleLawOfCosinesLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);

        learningRequirement.appendSubRequirement(
                "Uczeń rozumie treść Twierdzenia Cosinusów i jego zastosowanie w trójkątach.",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi zapisać Twierdzenie Cosinusów jako \\( c^2 = a^2 + b^2 - 2ab\\cos(C) \\).",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna zastosowanie Twierdzenia Cosinusów do obliczania długości boków w trójkącie, gdy znane są dwie długości boków i kąt między nimi.",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi używać Twierdzenia Cosinusów do znajdowania miar kątów w trójkącie, gdy znane są długości wszystkich boków.",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie zależność między Twierdzeniem Cosinusów a Twierdzeniem Pitagorasa w przypadku trójkątów prostokątnych.",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi rozwiązywać zadania tekstowe związane z Twierdzeniem Cosinusów, w tym z zastosowaniem w rzeczywistych sytuacjach geometrycznych.",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );

        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleLawOfCosinesLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Twierdzenie Cosinusów";

    public static final String SUB_REQUIREMENT_1 = """
            ### Treść Twierdzenia Cosinusów
            Twierdzenie Cosinusów opisuje zależność między długościami boków trójkąta i miarami jego kątów:
            \\[
            c^2 = a^2 + b^2 - 2ab\\cos(C)
            \\]
            Gdzie \\(a, b, c\\) to długości boków, a \\(C\\) to kąt naprzeciw boku \\(c\\).
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Zapis Twierdzenia Cosinusów
            Twierdzenie można zapisać w postaci ogólnej dla dowolnego boku:
            \\[
            c^2 = a^2 + b^2 - 2ab\\cos(C)
            \\]
            Lub analogicznie dla innych boków:
            \\[
            a^2 = b^2 + c^2 - 2bc\\cos(A) \\\\
            b^2 = a^2 + c^2 - 2ac\\cos(B)
            \\]
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Zastosowanie do obliczania długości boków
            Twierdzenie Cosinusów pozwala obliczyć długość boku w trójkącie, jeśli znasz długości dwóch innych boków i kąt między nimi:
            \\[
            c = \\sqrt{a^2 + b^2 - 2ab\\cos(C)}
            \\]
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Zastosowanie do obliczania kątów
            Jeśli znasz długości wszystkich boków w trójkącie, możesz obliczyć dowolny kąt, używając Twierdzenia Cosinusów:
            \\[
            \\cos(C) = \\frac{a^2 + b^2 - c^2}{2ab}
            \\]
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Zależność z Twierdzeniem Pitagorasa
            W przypadku trójkąta prostokątnego \\(\\cos(90^\\circ) = 0\\), więc Twierdzenie Cosinusów upraszcza się do Twierdzenia Pitagorasa:
            \\[
            c^2 = a^2 + b^2
            \\]
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Zadania tekstowe z Twierdzeniem Cosinusów
            Przykładowe zastosowania obejmują:
            - Obliczanie długości boku w nieregularnych trójkątach w konstrukcjach.
            - Znajdowanie miar kątów w trójkątach skalnych lub kartograficznych.
            """;
}

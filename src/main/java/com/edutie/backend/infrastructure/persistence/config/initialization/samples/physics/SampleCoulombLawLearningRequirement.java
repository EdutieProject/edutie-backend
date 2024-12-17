package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleCoulombLawLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        if (learningRequirementPersistence.getRepository().findAll().stream().anyMatch(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME))) {
            requirement = learningRequirementPersistence.getRepository().findAll().stream().filter(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME)).findFirst().get();
            isSeeded = true;
            return;
        }
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Uczeń zna definicję prawa Coulomba i jego matematyczną formułę",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczyć siłę elektrostatyczną między dwoma ładunkami za pomocą wzoru Coulomba",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie, jak odległość między ładunkami wpływa na siłę oddziaływania",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna znaczenie stałej elektrostatycznej i jej wpływ na obliczenia",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi zastosować prawo Coulomba w kontekście różnych układów ładunków",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleCoulombLawLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Prawo Coulomba";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja prawa Coulomba
            Prawo Coulomba opisuje siłę elektrostatyczną między dwoma ładunkami elektrycznymi. Siła ta jest proporcjonalna do iloczynu ładunków i odwrotnie proporcjonalna do kwadratu odległości między nimi.
            
            **Formuła prawa Coulomba:**
            \\[
            F = k_e \\frac{|q_1 q_2|}{r^2}
            \\]
            gdzie:
            - \\(F\\): siła elektrostatyczna,
            - \\(q_1, q_2\\): ładunki elektryczne,
            - \\(r\\): odległość między ładunkami,
            - \\(k_e\\): stała elektrostatyczna.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Obliczanie siły elektrostatycznej
            Siłę elektrostatyczną między dwoma ładunkami można obliczyć, stosując wzór Coulomba:
            
            **Wzór:**
            \\[
            F = k_e \\frac{|q_1 q_2|}{r^2}
            \\]
            
            Przykład: Jeśli \\(q_1 = 5 \\times 10^{-6} C\\) i \\(q_2 = 3 \\times 10^{-6} C\\), a odległość między nimi wynosi 0.2 m, oblicz siłę elektrostatyczną.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Wpływ odległości między ładunkami
            Siła elektrostatyczna jest odwrotnie proporcjonalna do kwadratu odległości między ładunkami:
            
            - Zwiększenie odległości \\(r\\) zmniejsza siłę \\(F\\).
            - Zmniejszenie odległości \\(r\\) powoduje wzrost siły \\(F\\).
            
            Prawo Coulomba ilustruje zależność:
            \\[
            F \\propto \\frac{1}{r^2}
            \\]
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Stała elektrostatyczna
            Stała elektrostatyczna \\(k_e\\) to wartość, która zależy od medium, w którym znajdują się ładunki. W próżni jej wartość wynosi:
            
            \\[
            k_e = 8.99 \\times 10^9 \\frac{Nm^2}{C^2}
            \\]
            
            Jest to współczynnik, który umożliwia przeliczenie jednostek w wzorze Coulomba.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Zastosowanie prawa Coulomba w różnych układach ładunków
            Prawo Coulomba jest wykorzystywane do obliczeń sił między ładunkami w różnych układach, takich jak:
            
            - Dwa ładunki punktowe.
            - Układy wielu ładunków, gdzie stosuje się zasadę superpozycji sił.
            
            W takich przypadkach siły między ładunkami oblicza się dla każdej pary ładunków, a następnie sumuje się te siły.
            """;
}

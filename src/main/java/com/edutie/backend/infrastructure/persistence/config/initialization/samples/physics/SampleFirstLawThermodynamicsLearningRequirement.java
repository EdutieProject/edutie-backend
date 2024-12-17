package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleFirstLawThermodynamicsLearningRequirement {
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
                "Uczeń zna pierwsze prawo termodynamiki i potrafi je zapisać w postaci równania",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie, że zmiana energii wewnętrznej układu zależy od ciepła i pracy",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi wskazać przykłady procesów termodynamicznych (izotermiczny, izochoryczny, izobaryczny, adiabatyczny) oraz ich wpływ na energię wewnętrzną",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczyć ciepło, pracę lub zmianę energii wewnętrznej w układzie dla prostych procesów",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie różnicę między procesami odwracalnymi a nieodwracalnymi z perspektywy pierwszego prawa termodynamiki",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń umie zastosować pierwsze prawo termodynamiki do analizy rzeczywistych układów fizycznych, takich jak silniki cieplne",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi opisać i zinterpretować przemiany energetyczne zachodzące w otwartym i zamkniętym układzie termodynamicznym",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleFirstLawThermodynamicsLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Pierwsze prawo termodynamiki";
    public static final String SUB_REQUIREMENT_1 = """
            ### Pierwsze prawo termodynamiki
            **1. Treść prawa:**  
            Pierwsze prawo termodynamiki jest zasadą zachowania energii w procesach termodynamicznych. Wyraża się ono równaniem:
            \s
            \\[
            \\Delta U = Q - W
            \\]
            gdzie:  
            - \\(\\Delta U\\) – zmiana energii wewnętrznej układu,  
            - \\(Q\\) – ciepło dostarczone do układu (lub oddane),  
            - \\(W\\) – praca wykonana przez układ (lub na układzie).  

            **2. Znaczenie:**  
            Pierwsze prawo termodynamiki wiąże zmiany energii wewnętrznej z przepływem ciepła i wykonaniem pracy. Jest uniwersalne i obowiązuje we wszystkich układach termodynamicznych.  
            \s
            """;

    public static final String SUB_REQUIREMENT_2 = """
            **Zmiana energii wewnętrznej układu:**  
            - Energia wewnętrzna układu (\\(U\\)) zależy od sumy energii kinetycznej i potencjalnej cząsteczek.  
            - Zmiana \\(\\Delta U\\) zachodzi, gdy:  
              1. Układ wymienia ciepło \\(Q\\) z otoczeniem.  
              2. Układ wykonuje pracę \\(W\\) lub praca jest wykonywana na nim.  

            **Interpretacja:**  
            - Jeśli \\(Q > W\\), energia wewnętrzna rośnie.  
            - Jeśli \\(W > Q\\), energia wewnętrzna maleje.  
            \s
            """;

    public static final String SUB_REQUIREMENT_3 = """
            **Procesy termodynamiczne i ich wpływ na energię wewnętrzną:**  
            - **Izotermiczny:** Temperatura układu jest stała. \\(\\Delta U = 0\\), więc \\(Q = W\\).  
            - **Izochoryczny:** Objętość układu jest stała. \\(W = 0\\), więc \\(\\Delta U = Q\\).  
            - **Izobaryczny:** Ciśnienie układu jest stałe. Występują zmiany \\(\\Delta U\\), \\(Q\\) i \\(W\\).  
            - **Adiabatyczny:** Brak wymiany ciepła z otoczeniem (\\(Q = 0\\)). \\(\\Delta U = -W\\).  
            \s
            """;

    public static final String SUB_REQUIREMENT_4 = """
            **Obliczenia dla procesów:**  
            - Znajdowanie \\(\\Delta U\\): \\(\\Delta U = n C_v \\Delta T\\) dla gazów doskonałych.  
            - Znajdowanie \\(W\\): Praca w izobarycznym procesie \\(W = P \\Delta V\\).  
            - Znajdowanie \\(Q\\): \\(Q = \\Delta U + W\\).  

            **Przykład:**  
            Gaz doskonały w procesie izochorycznym pochłania 50 J ciepła. Oblicz \\(\\Delta U\\):  
            \\(\\Delta U = Q - 0 = 50 J\\).  
            \s
            """;

    public static final String SUB_REQUIREMENT_5 = """
            **Procesy odwracalne i nieodwracalne:**  
            - **Odwracalne:** Można odwrócić bez strat energii. Idealizacja procesu termodynamicznego.  
            - **Nieodwracalne:** Realne procesy zachodzące w przyrodzie. Towarzyszą im straty energii (np. na skutek tarcia).  

            **Znaczenie w pierwszym prawie termodynamiki:**  
            Dla procesów odwracalnych i nieodwracalnych równanie \\(\\Delta U = Q - W\\) pozostaje ważne, ale wartości \\(Q\\) i \\(W\\) różnią się w szczegółach.
            \s
            """;

    public static final String SUB_REQUIREMENT_6 = """
            **Zastosowanie do rzeczywistych układów:**  
            - **Silniki cieplne:**  
              Pierwsze prawo wyjaśnia przemiany energii w cyklu Carnota.  
            - **Chłodzenie:**  
              Lodówki wykorzystują \\(Q < 0\\) w pierwszym prawie termodynamiki.  

            **Przykład:**  
            Silnik cieplny otrzymuje 300 J ciepła i wykonuje 100 J pracy. Oblicz \\(\\Delta U\\):  
            \\(\\Delta U = Q - W = 300 J - 100 J = 200 J\\).
            \s
            """;

    public static final String SUB_REQUIREMENT_7 = """
            **Przemiany energetyczne w układach otwartych i zamkniętych:**  
            - **Układ zamknięty:** Nie wymienia masy z otoczeniem. Energia zmienia się zgodnie z \\(\\Delta U = Q - W\\).  
            - **Układ otwarty:** Wymienia masę i energię. Uogólnienie pierwszego prawa uwzględnia przepływ entalpii, pracy technicznej i ciepła.
            """;
}

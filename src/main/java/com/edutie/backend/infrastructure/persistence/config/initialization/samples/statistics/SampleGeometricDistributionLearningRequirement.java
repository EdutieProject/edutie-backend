package com.edutie.backend.infrastructure.persistence.config.initialization.samples.statistics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleGeometricDistributionLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Definicja rozkładu geometrycznego",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Interpretacja parametru \\(p\\)",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Obliczanie podstawowych prawdopodobieństw",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Średnia i wariancja rozkładu geometrycznego",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Graficzna reprezentacja rozkładu",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Rozwiązywanie prostych zadań",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Zadania złożone z wykorzystaniem średniej i wariancji",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Porównanie rozkładu geometrycznego z innymi rozkładami",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirement.appendSubRequirement(
                "Zaawansowane zadania analityczne",
                PromptFragment.of(SUB_REQUIREMENT_9)
        );
        learningRequirement.appendSubRequirement(
                "Estymacja parametru \\(p\\)",
                PromptFragment.of(SUB_REQUIREMENT_10)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleGeometricDistributionLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Rozkład Geometryczny";
    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja rozkładu geometrycznego
            Rozkład geometryczny opisuje liczbę prób wymaganych do osiągnięcia pierwszego sukcesu w serii niezależnych prób, 
            z prawdopodobieństwem sukcesu \\(p\\) w każdej próbie. Prawdopodobieństwo uzyskania sukcesu przy \\(k\\)-tej próbie wynosi:
            \\[
            P(X = k) = (1-p)^{k-1}p, \\quad k = 1, 2, 3, \\ldots
            \\]
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Interpretacja parametru \\(p\\)
            Parametr \\(p\\) reprezentuje prawdopodobieństwo sukcesu w każdej pojedynczej próbie. Wartość \\(1-p\\) oznacza 
            prawdopodobieństwo porażki. Na przykład, jeśli \\(p = 0.3\\), to \\(1-p = 0.7\\).
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Obliczanie podstawowych prawdopodobieństw
            Prawdopodobieństwo sukcesu przy \\(k\\)-tej próbie w rozkładzie geometrycznym wynosi:
            \\[
            P(X = k) = (1-p)^{k-1}p.
            \\]
            Na przykład: Dla \\(p = 0.5\\), prawdopodobieństwo sukcesu przy 3. próbie wynosi:
            \\[
            P(X = 3) = (1-0.5)^{2}0.5 = 0.125.
            \\]
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Średnia i wariancja rozkładu geometrycznego
            Rozkład geometryczny charakteryzuje się:
            - **Średnią (wartość oczekiwana):**
              \\[
              \\mu = \\frac{1}{p},
              \\]
            - **Wariancją:**
              \\[
              \\sigma^2 = \\frac{1-p}{p^2}.
              \\]
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Graficzna reprezentacja rozkładu
            Rozkład geometryczny przedstawia się na wykresie słupkowym, gdzie wysokość każdego słupka odpowiada wartości \\(P(X = k)\\).
            Słupki maleją wykładniczo w miarę wzrostu \\(k\\).
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Rozwiązywanie prostych zadań
            Przykład: Oblicz prawdopodobieństwo sukcesu przy 4. próbie dla \\(p = 0.25\\):
            \\[
            P(X = 4) = (1-0.25)^{3}0.25 = 0.10546875.
            \\]
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Zadania złożone z wykorzystaniem średniej i wariancji
            Zadanie: Oblicz wartość oczekiwaną i wariancję dla \\(p = 0.4\\):
            \\[
            \\mu = \\frac{1}{0.4} = 2.5, \\quad \\sigma^2 = \\frac{1-0.4}{0.4^2} = 3.75.
            \\]
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Porównanie rozkładu geometrycznego z innymi rozkładami
            Porównanie rozkładu geometrycznego z rozkładem Bernoulliego lub dwumianowym, w kontekście liczby prób i parametrów.
            """;

    public static final String SUB_REQUIREMENT_9 = """
            ### Zaawansowane zadania analityczne
            Analiza wpływu parametru \\(p\\) na średnią i wariancję rozkładu. Na przykład, przy \\(p\\) bliskim 1, średnia 
            i wariancja są najmniejsze.
            """;

    public static final String SUB_REQUIREMENT_10 = """
            ### Estymacja parametru \\(p\\)
            Oszacowanie \\(p\\) na podstawie danych. Przykład: W eksperymencie pierwszego sukcesu osiągnięto średnią liczbę prób równą 4:
            \\[
            \\hat{p} = \\frac{1}{\\text{średnia liczba prób}} = \\frac{1}{4} = 0.25.
            \\]
            """;
}

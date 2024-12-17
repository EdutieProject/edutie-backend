package com.edutie.backend.infrastructure.persistence.config.initialization.samples.statistics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleHypergeometricDistributionLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        if (learningRequirementPersistence.getRepository().findAll().stream().anyMatch(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME))) {
            isSeeded = true;
            return;
        }
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Definicja rozkładu hipergeometrycznego",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Parametry rozkładu hipergeometrycznego",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Prawdopodobieństwo w rozkładzie hipergeometrycznym",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Średnia i wariancja rozkładu hipergeometrycznego",
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
                "Porównanie z rozkładami: dwumianowym i hipergeometrycznym",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirement.appendSubRequirement(
                "Zaawansowane zadania analityczne",
                PromptFragment.of(SUB_REQUIREMENT_9)
        );
        learningRequirement.appendSubRequirement(
                "Estymacja parametrów rozkładu hipergeometrycznego",
                PromptFragment.of(SUB_REQUIREMENT_10)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleHypergeometricDistributionLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Rozkład Hipergeometryczny";
    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja rozkładu hipergeometrycznego
            Rozkład hipergeometryczny opisuje prawdopodobieństwo wyboru \\(k\\) sukcesów w próbce o wielkości \\(n\\) 
            z populacji zawierającej \\(K\\) sukcesów, przy założeniu, że próbki są losowane bez zwracania. 
            Prawdopodobieństwo uzyskania dokładnie \\(k\\) sukcesów w próbie obliczamy ze wzoru:
            \\[
            P(X = k) = \\frac{\\binom{K}{k} \\binom{N-K}{n-k}}{\\binom{N}{n}},
            \\]
            gdzie \\(N\\) to rozmiar populacji, \\(K\\) to liczba sukcesów w populacji, a \\(n\\) to rozmiar próbki.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Parametry rozkładu hipergeometrycznego
            Parametry rozkładu hipergeometrycznego to:
            - \\(N\\): całkowity rozmiar populacji,
            - \\(K\\): liczba sukcesów w populacji,
            - \\(n\\): liczba prób,
            - \\(k\\): liczba sukcesów w próbce.
            Na przykład, w zadaniu o populacji \\(N = 100\\), z \\(K = 30\\) sukcesami, i próbce \\(n = 10\\), 
            chcemy obliczyć prawdopodobieństwo uzyskania \\(k = 3\\) sukcesów.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Prawdopodobieństwo w rozkładzie hipergeometrycznym
            Prawdopodobieństwo uzyskania dokładnie \\(k\\) sukcesów w próbie z populacji \\(N\\) oblicza się ze wzoru:
            \\[
            P(X = k) = \\frac{\\binom{K}{k} \\binom{N-K}{n-k}}{\\binom{N}{n}}.
            \\]
            Przykład: Dla populacji \\(N = 100\\), \\(K = 30\\), \\(n = 10\\), i \\(k = 3\\):
            \\[
            P(X = 3) = \\frac{\\binom{30}{3} \\binom{70}{7}}{\\binom{100}{10}}.
            \\]
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Średnia i wariancja rozkładu hipergeometrycznego
            Rozkład hipergeometryczny charakteryzuje się:
            - **Średnią (wartość oczekiwana):**
              \\[
              \\mu = \\frac{nK}{N},
              \\]
              gdzie \\(n\\) to liczba prób, \\(K\\) to liczba sukcesów w populacji, a \\(N\\) to rozmiar populacji.
            - **Wariancją:**
              \\[
              \\sigma^2 = \\frac{nK(N-K)(N-n)}{N^2(N-1)}.
              \\]
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Graficzna reprezentacja rozkładu
            Rozkład hipergeometryczny może być przedstawiony na wykresie słupkowym. Wartość prawdopodobieństwa dla każdej 
            liczby sukcesów \\(k\\) w próbce \\(n\\) będzie reprezentowana przez wysokość odpowiedniego słupka.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Rozwiązywanie prostych zadań
            Przykład: Dla populacji \\(N = 50\\), \\(K = 20\\), \\(n = 5\\), oblicz prawdopodobieństwo uzyskania 
            \\(k = 2\\) sukcesów:
            \\[
            P(X = 2) = \\frac{\\binom{20}{2} \\binom{30}{3}}{\\binom{50}{5}} = 0.318.
            \\]
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Zadania złożone z wykorzystaniem średniej i wariancji
            Zadanie: Oblicz średnią i wariancję dla populacji \\(N = 200\\), \\(K = 50\\), \\(n = 15\\):
            \\[
            \\mu = \\frac{15 \\times 50}{200} = 3.75, \\quad \\sigma^2 = \\frac{15 \\times 50 \\times (200-50) \\times (200-15)}{200^2(200-1)}.
            \\]
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Porównanie z rozkładami: dwumianowym i hipergeometrycznym
            Porównanie rozkładu hipergeometrycznego z rozkładem dwumianowym, szczególnie w kontekście prób z populacji 
            bez zwracania (hipergeometryczny) versus z próbami ze zwracaniem (dwumianowy). Omówienie różnic w modelach 
            oraz zastosowaniach.
            """;

    public static final String SUB_REQUIREMENT_9 = """
            ### Zaawansowane zadania analityczne
            Analiza wpływu parametrów rozkładu hipergeometrycznego na jego kształt. Jak zmiana wartości \\(K\\), 
            \\(N\\), lub \\(n\\) wpływa na średnią, wariancję i kształt rozkładu?
            """;

    public static final String SUB_REQUIREMENT_10 = """
            ### Estymacja parametrów rozkładu hipergeometrycznego
            Oszacowanie parametrów rozkładu hipergeometrycznego na podstawie danych z eksperymentu. Na przykład: 
            w eksperymencie, w którym z 50 próbek uzyskano 12 sukcesów, oszacowanie \\(K\\) i \\(N\\) na podstawie 
            średniej i wariancji wyników.
            """;
}

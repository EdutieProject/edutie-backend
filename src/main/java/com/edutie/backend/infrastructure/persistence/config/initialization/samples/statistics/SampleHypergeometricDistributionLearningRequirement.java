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
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie definicję rozkładu hipergeometrycznego i jego zastosowania.",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczać prawdopodobieństwa dla zmiennych losowych o rozkładzie hipergeometrycznym.",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna wzory na wartość oczekiwaną i wariancję w rozkładzie hipergeometrycznym.",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi odróżniać rozkład hipergeometryczny od rozkładu dwumianowego i innych rozkładów.",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi interpretować i zastosować rozkład hipergeometryczny w przykładach praktycznych.",
                PromptFragment.of(SUB_REQUIREMENT_5)
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

    public static final String LEARNING_REQUIREMENT_NAME = "Rozkład hipergeometryczny";
    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja rozkładu hipergeometrycznego
            Rozkład hipergeometryczny opisuje liczbę sukcesów w próbie bez zwracania z populacji o określonej liczbie sukcesów i porażek.
            
            Przykład: Losowanie kart bez zwracania z talii.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Prawdopodobieństwo w rozkładzie hipergeometrycznym
            Dla zmiennej losowej \\(X\\), reprezentującej liczbę sukcesów w próbie:
            \\[
            P(X = k) = \\frac{\\binom{K}{k} \\binom{N-K}{n-k}}{\\binom{N}{n}}
            \\]
            gdzie:
            - \\(N\\): liczba elementów w populacji,
            - \\(K\\): liczba sukcesów w populacji,
            - \\(n\\): liczba losowań (próba),
            - \\(k\\): liczba sukcesów w próbie.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Wartość oczekiwana i wariancja w rozkładzie hipergeometrycznym
            - **Wartość oczekiwana:**
              \\[
              E(X) = n \\cdot \\frac{K}{N}
              \\]
              Określa średnią liczbę sukcesów w próbie.
            - **Wariancja:**
              \\[
              Var(X) = n \\cdot \\frac{K}{N} \\cdot \\frac{N-K}{N} \\cdot \\frac{N-n}{N-1}
              \\]
              Uwzględnia ograniczenie wynikające z braku zwracania elementów do populacji.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Rozkład hipergeometryczny a inne rozkłady
            - **Rozkład hipergeometryczny**: Modeluje sukcesy w próbie bez zwracania.
            - **Rozkład dwumianowy**: Modeluje sukcesy w próbie ze zwracaniem.
            
            Różnica: W rozkładzie hipergeometrycznym prawdopodobieństwa zmieniają się z każdym kolejnym losowaniem.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Zastosowania rozkładu hipergeometrycznego
            - Kontrola jakości: Losowe testowanie produktów.
            - Analiza kart w grach: Liczba sukcesów w ręku gracza.
            - Biologia: Pobieranie próbek genetycznych lub biologicznych.
            """;
}

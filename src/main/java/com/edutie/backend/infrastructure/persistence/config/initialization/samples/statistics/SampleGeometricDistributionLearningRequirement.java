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
                "Uczeń rozumie pojęcie rozkładu geometrycznego i jego zastosowanie w statystyce.",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczać prawdopodobieństwa dla zmiennych losowych w rozkładzie geometrycznym.",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna wzory na wartość oczekiwaną i wariancję w rozkładzie geometrycznym.",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi interpretować i stosować rozkład geometryczny w praktycznych przykładach.",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie różnicę między rozkładem geometrycznym a innymi rozkładami prawdopodobieństwa.",
                PromptFragment.of(SUB_REQUIREMENT_5)
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

    public static final String LEARNING_REQUIREMENT_NAME = "Rozkład geometryczny";
    public static final String SUB_REQUIREMENT_1 = """
            ### Pojęcie rozkładu geometrycznego
            Rozkład geometryczny opisuje liczbę prób potrzebnych do osiągnięcia pierwszego sukcesu w serii niezależnych prób Bernoulliego.
            
            Przykład: Liczba rzutów monetą potrzebnych, aby wyrzucić pierwszego orła.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Prawdopodobieństwo w rozkładzie geometrycznym
            Dla zmiennej losowej \\(X\\), reprezentującej liczbę prób do pierwszego sukcesu z prawdopodobieństwem sukcesu \\(p\\):
            \\[
            P(X = k) = (1-p)^{k-1}p
            \\]
            gdzie:
            - \\(k\\): liczba prób,
            - \\(p\\): prawdopodobieństwo sukcesu w jednej próbie.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Wartość oczekiwana i wariancja w rozkładzie geometrycznym
            - **Wartość oczekiwana (średnia):**
              \\[
              E(X) = \\frac{1}{p}
              \\]
              Określa średnią liczbę prób potrzebnych do osiągnięcia sukcesu.
            - **Wariancja:**
              \\[
              Var(X) = \\frac{1-p}{p^2}
              \\]
              Mierzy rozproszenie liczby prób wokół średniej.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Zastosowanie rozkładu geometrycznego
            Rozkład geometryczny znajduje zastosowanie w sytuacjach takich jak:
            - Liczba telefonów potrzebnych do pierwszej sprzedaży.
            - Liczba dni do wystąpienia deszczu.
            - Liczba prób w eksperymentach, aby osiągnąć sukces.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Rozkład geometryczny a inne rozkłady
            - **Rozkład geometryczny**: Modeluje liczbę prób do pierwszego sukcesu.
            - **Rozkład Bernoulliego**: Dotyczy pojedynczej próby (sukces/porażka).
            - **Rozkład dwumianowy**: Liczba sukcesów w określonej liczbie prób.
            
            Przykład: Rozkład geometryczny może modelować czas oczekiwania do pierwszego sukcesu, podczas gdy rozkład dwumianowy modeluje liczbę sukcesów w wielu próbach.
            """;
}

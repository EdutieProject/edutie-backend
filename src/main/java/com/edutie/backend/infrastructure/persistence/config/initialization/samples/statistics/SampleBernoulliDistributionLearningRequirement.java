package com.edutie.backend.infrastructure.persistence.config.initialization.samples.statistics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleBernoulliDistributionLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie pojęcie rozkładu Bernoulliego i jego zastosowanie w statystyce.",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi wyznaczać prawdopodobieństwa dla zdarzeń w rozkładzie Bernoulliego.",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna właściwości średniej i wariancji rozkładu Bernoulliego.",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi interpretować i stosować rozkład Bernoulliego w praktycznych zadaniach.",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie różnicę między rozkładem Bernoulliego a innymi rozkładami prawdopodobieństwa.",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleBernoulliDistributionLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Rozkład Bernoulliego";
    public static final String SUB_REQUIREMENT_1 = """
            ### Pojęcie rozkładu Bernoulliego
            Rozkład Bernoulliego opisuje zdarzenia binarne z dwoma możliwymi wynikami:
            - Sukces (z prawdopodobieństwem \\(p\\)),
            - Porażka (z prawdopodobieństwem \\(1-p\\)).
            
            Jest używany w modelowaniu zdarzeń losowych takich jak rzut monetą (orzeł/panna).
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Wyznaczanie prawdopodobieństw w rozkładzie Bernoulliego
            Dla zmiennej losowej \\(X\\), która przyjmuje wartość \\(1\\) (sukces) z prawdopodobieństwem \\(p\\) i \\(0\\) (porażka) z \\(1-p\\), prawdopodobieństwo określa wzór:
            \\[
            P(X = x) = 
            \\begin{cases} 
            p & \\text{dla } x = 1, \\\\
            1-p & \\text{dla } x = 0.
            \\end{cases}
            \\]
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Średnia i wariancja rozkładu Bernoulliego
            - **Średnia (wartość oczekiwana):**
              \\[
              E(X) = p
              \\]
              Wskazuje na przewidywaną wartość sukcesu.
            - **Wariancja:**
              \\[
              Var(X) = p(1-p)
              \\]
              Opisuje rozproszenie wyników wokół średniej.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Zastosowanie rozkładu Bernoulliego
            Rozkład Bernoulliego znajduje zastosowanie w modelowaniu:
            - Wyników eksperymentów losowych (np. rzut kostką, test jakości).
            - Zdarzeń z dwiema opcjami, takich jak:
              - Klient kupił produkt (tak/nie),
              - Odpowiedź na pytanie (prawda/fałsz).
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Rozkład Bernoulliego a inne rozkłady
            - **Rozkład Bernoulliego** opisuje pojedyncze zdarzenie binarne.
            - **Rozkład dwumianowy**: Modeluje sumę wielu niezależnych zdarzeń Bernoulliego.
            - **Rozkład geometryczny**: Modeluje liczbę prób do pierwszego sukcesu w Bernoullim.
            
            Przykład: Jeśli rzucamy monetą, pojedynczy rzut to Bernoulli, a liczba orłów w serii rzutów to rozkład dwumianowy.
            """;
}

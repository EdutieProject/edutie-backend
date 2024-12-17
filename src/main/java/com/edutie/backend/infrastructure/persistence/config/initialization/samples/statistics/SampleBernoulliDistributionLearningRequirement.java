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
        if (learningRequirementPersistence.getRepository().findAll().stream().anyMatch(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME))) {
            isSeeded = true;
            return;
        }
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Definicja rozkładu Bernoulliego",
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
                "Średnia i wariancja rozkładu Bernoulliego",
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
                "Zadania złożone z zastosowaniem średniej i wariancji",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie w rozkładzie dwumianowym",
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
            throw new RuntimeException(SampleBernoulliDistributionLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Rozkład Bernoulliego";
    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja rozkładu Bernoulliego
            Rozkład Bernoulliego opisuje zdarzenia losowe, które mają dokładnie dwa możliwe wyniki: sukces \\(X = 1\\) 
            i porażkę \\(X = 0\\), z prawdopodobieństwem sukcesu równym \\(p\\). Przykładem jest rzut monetą, gdzie sukcesem \\(X = 1\\) 
            może być wyrzucenie orła, a porażką \\(X = 0\\) wyrzucenie reszki. Prawdopodobieństwo wynosi:
            \\[
            P(X = 1) = p, \\quad P(X = 0) = 1 - p.
            \\]
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Interpretacja parametru \\(p\\)
            Parametr \\(p\\) to prawdopodobieństwo sukcesu \\(X = 1\\). Wartość \\(1 - p\\) oznacza prawdopodobieństwo porażki \\(X = 0\\).
            Na przykład w rzucie kostką sukcesem może być wyrzucenie szóstki \\(X = 1\\), a jeśli \\(p = \\frac{1}{6}\\), to:
            \\[
            P(X = 1) = \\frac{1}{6}, \\quad P(X = 0) = \\frac{5}{6}.
            \\]
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Obliczanie podstawowych prawdopodobieństw
            Prawdopodobieństwa w rozkładzie Bernoulliego obliczamy ze wzoru:
            \\[
            P(X = x) = p^x(1-p)^{1-x}, \\quad x \\in \\{0, 1\\}.
            \\]
            Przykład: Dla monety, która z prawdopodobieństwem \\(p = 0.7\\) wypada orłem:
            \\[
            P(X = 1) = 0.7, \\quad P(X = 0) = 0.3.
            \\]
            Suma \\(P(X = 1) + P(X = 0) = 1\\) potwierdza poprawność rozkładu.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Średnia i wariancja rozkładu Bernoulliego
            Rozkład Bernoulliego charakteryzuje się:
            - **Średnią (wartość oczekiwana):**
              \\[
              \\mu = p,
              \\]
              co oznacza oczekiwaną wartość sukcesu.
            - **Wariancją:**
              \\[
              \\sigma^2 = p(1-p),
              \\]
              która mierzy rozproszenie wyników wokół średniej.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Graficzna reprezentacja rozkładu
            Rozkład Bernoulliego przedstawia się na wykresie słupkowym:
            - Wysokość słupka dla \\(X = 1\\) to \\(P(X = 1) = p\\),
            - Wysokość słupka dla \\(X = 0\\) to \\(P(X = 0) = 1-p\\).
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Rozwiązywanie prostych zadań
            Zadania polegają na obliczeniu prawdopodobieństw w rozkładzie Bernoulliego dla określonego \\(p\\). Przykład:
            Jeśli prawdopodobieństwo sukcesu wynosi \\(p = 0.2\\), to:
            \\[
            P(X = 1) = 0.2, \\quad P(X = 0) = 0.8.
            \\]
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Zadania złożone z zastosowaniem średniej i wariancji
            W zadaniach wykorzystujemy wzory:
            \\[
            \\mu = p, \\quad \\sigma^2 = p(1-p).
            \\]
            Na przykład: W eksperymencie z prawdopodobieństwem sukcesu \\(p = 0.8\\):
            \\[
            \\mu = 0.8, \\quad \\sigma^2 = 0.16.
            \\]
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Zastosowanie w rozkładzie dwumianowym
            Rozkład Bernoulliego jest szczególnym przypadkiem rozkładu dwumianowego dla \\(n = 1\\). Na przykład:
            - Pojedynczy rzut monetą to rozkład Bernoulliego,
            - Liczba orłów w serii 10 rzutów to rozkład dwumianowy.
            """;

    public static final String SUB_REQUIREMENT_9 = """
            ### Zaawansowane zadania analityczne
            W zaawansowanych zadaniach analizujemy wpływ parametru \\(p\\) na rozkład. Na przykład, aby zminimalizować wariancję, 
            \\(p\\) powinno być bliskie \\(0\\) lub \\(1\\), ponieważ \\(\\sigma^2 = p(1-p)\\) osiąga maksimum dla \\(p = 0.5\\).
            """;

    public static final String SUB_REQUIREMENT_10 = """
            ### Estymacja parametru \\(p\\)
            Parametr \\(p\\) można oszacować na podstawie wyników eksperymentu. Przykład:
            W 100 próbach sukces \\(X = 1\\) wystąpił 70 razy. Szacowane \\(p\\) wynosi:
            \\[
            \\hat{p} = \\frac{70}{100} = 0.7.
            \\]
            """;
}

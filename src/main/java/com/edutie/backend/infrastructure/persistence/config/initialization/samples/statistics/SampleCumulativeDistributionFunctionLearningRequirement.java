package com.edutie.backend.infrastructure.persistence.config.initialization.samples.statistics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleCumulativeDistributionFunctionLearningRequirement {
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
                "Definicja dystrybuanty",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Wzór na dystrybuantę",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Dystrybuanta a prawdopodobieństwo",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Dystrybuanta rozkładu normalnego",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Dystrybuanta rozkładu dwumianowego",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie dystrybuanty w zadaniach",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Zaawansowane zadania z dystrybuantą",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Porównanie dystrybuanty w różnych rozkładach",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirement.appendSubRequirement(
                "Estymacja parametrów rozkładów z wykorzystaniem dystrybuanty",
                PromptFragment.of(SUB_REQUIREMENT_9)
        );
        learningRequirement.appendSubRequirement(
                "Zaawansowane analizy z dystrybuantą",
                PromptFragment.of(SUB_REQUIREMENT_10)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleCumulativeDistributionFunctionLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Dystrybuanta i jej zastosowanie w rozkładach";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja dystrybuanty
            Dystrybuanta (funkcja rozkładu) to funkcja, która dla danej zmiennej losowej \\(X\\) 
            określa prawdopodobieństwo, że \\(X\\) przyjmie wartość mniejszą lub równą od zadanego argumentu \\(x\\):
            \\[
            F_X(x) = P(X \\leq x).
            \\]
            Dystrybuanta jest funkcją rosnącą i przyjmuje wartości z przedziału \\([0, 1]\\).
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Wzór na dystrybuantę
            Wzór na dystrybuantę zależy od typu rozkładu. Dla rozkładu ciągłego, dystrybuanta jest całką z funkcji gęstości prawdopodobieństwa:
            \\[
            F_X(x) = \\int_{-\\infty}^{x} f_X(t) dt,
            \\]
            gdzie \\(f_X(t)\\) to funkcja gęstości prawdopodobieństwa. Dla rozkładu dyskretnego, dystrybuanta jest sumą prawdopodobieństw:
            \\[
            F_X(x) = \\sum_{t \\leq x} P(X = t).
            \\]
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Dystrybuanta a prawdopodobieństwo
            Dystrybuanta pozwala obliczyć prawdopodobieństwo, że zmienna losowa przyjmie wartość mniejszą lub równą zadanemu \\(x\\).
            Na przykład, dla rozkładu ciągłego:
            \\[
            P(a \\leq X \\leq b) = F_X(b) - F_X(a).
            \\]
            Dla rozkładu dyskretnego, to prawdopodobieństwo obliczamy jako sumę:
            \\[
            P(a \\leq X \\leq b) = \\sum_{t=a}^{b} P(X = t).
            \\]
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Dystrybuanta rozkładu normalnego
            Dystrybuanta rozkładu normalnego \\(F_X(x)\\) jest funkcją rosnącą, która opisuje prawdopodobieństwo, 
            że zmienna losowa \\(X\\) z rozkładu normalnego przyjmie wartość mniejszą lub równą \\(x\\). 
            Dla rozkładu normalnego \\(N(\\mu, \\sigma^2)\\), dystrybuanta jest obliczana za pomocą funkcji błędu (erf):
            \\[
            F_X(x) = \\frac{1}{2}\\left(1 + \\text{erf}\\left(\\frac{x - \\mu}{\\sigma \\sqrt{2}}\\right)\\right).
            \\]
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Dystrybuanta rozkładu dwumianowego
            Dystrybuanta rozkładu dwumianowego \\(B(n, p)\\) jest sumą prawdopodobieństw dla wszystkich możliwych wyników:
            \\[
            F_X(k) = P(X \\leq k) = \\sum_{i=0}^{k} \\binom{n}{i} p^i (1 - p)^{n - i}.
            \\]
            Jest to prawdopodobieństwo uzyskania co najwyżej \\(k\\) sukcesów w \\(n\\) próbach z prawdopodobieństwem sukcesu \\(p\\).
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Zastosowanie dystrybuanty w zadaniach
            Dystrybuanta jest użyteczna w obliczaniu prawdopodobieństw w różnych zadaniach. Na przykład, 
            dla rozkładu normalnego można obliczyć prawdopodobieństwo, że zmienna \\(X\\) przyjmie wartość 
            w przedziale \\([a, b]\\) za pomocą różnicy dystrybuant:
            \\[
            P(a \\leq X \\leq b) = F_X(b) - F_X(a).
            \\]
            Przykład: Dla \\(N(0, 1)\\) oblicz prawdopodobieństwo, że zmienna \\(X\\) leży w przedziale \\([-1, 1]\\).
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Zaawansowane zadania z dystrybuantą
            W zaawansowanych zadaniach wykorzystujemy dystrybuantę do obliczania prawdopodobieństw skrajnych 
            przypadków (np. ekstremalne wartości w rozkładzie normalnym) oraz do szacowania wartości krytycznych 
            w testach statystycznych (np. testy t-Studenta).
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Porównanie dystrybuanty w różnych rozkładach
            Porównanie dystrybuant w różnych rozkładach, takich jak normalny, dwumianowy, hipergeometryczny, 
            czy Poissona. Zrozumienie, jak dystrybuanta dla każdego z tych rozkładów różni się w kontekście 
            funkcji gęstości prawdopodobieństwa oraz w zastosowaniach praktycznych.
            """;

    public static final String SUB_REQUIREMENT_9 = """
            ### Estymacja parametrów rozkładów z wykorzystaniem dystrybuanty
            Dystrybuanta może być używana do oszacowania parametrów rozkładów na podstawie danych eksperymentalnych. 
            Przykład: Estymacja średniej i wariancji rozkładu normalnego z próby na podstawie obliczeń dystrybuanty.
            """;

    public static final String SUB_REQUIREMENT_10 = """
            ### Zaawansowane analizy z dystrybuantą
            Analiza skomplikowanych problemów statystycznych, w których dystrybuanta odgrywa kluczową rolę, 
            takich jak analiza ryzyka, modelowanie procesów stochastycznych, czy badanie zależności pomiędzy 
            różnymi zmiennymi losowymi.
            """;
}

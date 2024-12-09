package com.edutie.backend.infrastructure.persistence.config.initialization.samples.statistics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleDiscreteRandomVariableDistributionLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Definicja zmiennej losowej",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Rodzaje zmiennych losowych",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Rozkład prawdopodobieństwa zmiennej losowej",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Obliczanie rozkładu prawdopodobieństwa dla zmiennej losowej",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Przykład z rzutami koszykarza",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Podstawowe własności funkcji prawdopodobieństwa",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Interpretacja wyników obliczeń",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowania rozkładu prawdopodobieństwa w analizach statystycznych",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirement.appendSubRequirement(
                "Porównanie różnych rozkładów prawdopodobieństwa",
                PromptFragment.of(SUB_REQUIREMENT_9)
        );
        learningRequirement.appendSubRequirement(
                "Zaawansowane obliczenia z rozkładami dyskretnymi",
                PromptFragment.of(SUB_REQUIREMENT_10)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleDiscreteRandomVariableDistributionLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Rozkład prawdopodobieństwa zmiennej losowej dyskretnej";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja zmiennej losowej
            Zmienną losową nazywamy funkcję, która przypisuje każdemu zdarzeniu elementarnemu z przestrzeni zdarzeń \\(\\Omega\\) 
            liczbę rzeczywistą. W przykładzie dotyczącym rzutów koszykarza, zmienna losowa \\(X\\) opisuje liczbę trafień, 
            które mogą przyjąć wartości 0, 1 lub 2 w zależności od wyniku dwóch rzutów.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Rodzaje zmiennych losowych
            Zmienna losowa może być dyskretna lub ciągła. Zmienna losowa jest dyskretna, gdy przyjmuje skończoną lub 
            przeliczalną liczbę wartości (np. liczba trafień w rzucie koszykarza). Zmienna losowa jest ciągła, gdy przyjmuje 
            wartości z pewnego przedziału (np. czas spędzony w banku).
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Rozkład prawdopodobieństwa zmiennej losowej
            Rozkład prawdopodobieństwa zmiennej losowej określa, z jakimi prawdopodobieństwami zmienna ta przyjmuje 
            poszczególne wartości. Funkcja prawdopodobieństwa dla zmiennej losowej dyskretnej \\(X\\) jest funkcją, 
            która przypisuje każdemu możliwemu wynikowi prawdopodobieństwo.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Obliczanie rozkładu prawdopodobieństwa dla zmiennej losowej
            Dla zmiennej losowej \\(X\\), aby obliczyć rozkład prawdopodobieństwa, musimy znać prawdopodobieństwo 
            każdej z wartości, które \\(X\\) może przyjąć. Przykładem może być obliczenie prawdopodobieństwa dla liczby 
            trafień w dwóch rzutach koszykarza.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Przykład z rzutami koszykarza
            Rozważmy eksperyment, w którym koszykarz wykonuje dwa rzuty osobiste. Jeśli prawdopodobieństwo trafienia 
            w jednym rzucie wynosi 0.8, to możemy obliczyć rozkład prawdopodobieństwa dla liczby trafień \\(X\\), gdzie 
            \\(X = 0\\) oznacza brak trafień, \\(X = 1\\) oznacza jedno trafienie, a \\(X = 2\\) oznacza dwa trafienia.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Podstawowe własności funkcji prawdopodobieństwa
            Funkcja prawdopodobieństwa spełnia pewne podstawowe własności, takie jak:
            - \\(0 \\leq P(X = x_i) \\leq 1\\) dla każdego \\(x_i\\),
            - suma prawdopodobieństw dla wszystkich możliwych wartości zmiennej losowej wynosi 1.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Interpretacja wyników obliczeń
            Wyniki obliczeń rozkładu prawdopodobieństwa mogą być interpretowane w kontekście zadania. Na przykład, 
            obliczone prawdopodobieństwo \\(P(X = 1) = 0.32\\) w kontekście rzutów koszykarza oznacza, że 
            prawdopodobieństwo, że koszykarz trafi dokładnie raz w dwóch rzutach wynosi 32%.
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Zastosowania rozkładu prawdopodobieństwa w analizach statystycznych
            Rozkład prawdopodobieństwa zmiennej losowej jest wykorzystywany w analizach statystycznych, takich jak 
            obliczanie oczekiwanej wartości, wariancji, oraz w testach statystycznych.
            """;

    public static final String SUB_REQUIREMENT_9 = """
            ### Porównanie różnych rozkładów prawdopodobieństwa
            Analiza różnych rodzajów rozkładów, np. rozkładów dwumianowych, normalnych, czy Poissona, i porównanie 
            ich właściwości w kontekście różnych zastosowań w statystyce i analizie danych.
            """;

    public static final String SUB_REQUIREMENT_10 = """
            ### Zaawansowane obliczenia z rozkładami dyskretnymi
            Zaawansowane techniki obliczeniowe przy rozkładach dyskretnych, takie jak obliczanie wartości oczekiwanych, 
            wariancji, oraz zastosowanie ich w modelach ryzyka lub analizie zdarzeń rzadkich.
            """;
}

package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleBellNumbersLearningRequirement {
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
                "Definicja liczby Bella",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Własności liczb Bella",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Obliczanie liczb Bella przy użyciu wzoru rekurencyjnego",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Obliczanie liczb Bella za pomocą tablicy Stirlinga II rodzaju",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Graficzna reprezentacja liczb Bella",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowania liczb Bella w kombinatoryce",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Przykłady zadań praktycznych z liczbami Bella",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Analiza asymptotyczna liczb Bella",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirement.appendSubRequirement(
                "Zaawansowane metody obliczania liczb Bella",
                PromptFragment.of(SUB_REQUIREMENT_9)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowania liczb Bella w naukach ścisłych i informatyce",
                PromptFragment.of(SUB_REQUIREMENT_10)
        );

        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleBellNumbersLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Liczby Bella";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja liczby Bella
            Liczby Bella \\( B_n \\) opisują liczbę podziałów zbioru o \\( n \\) elementach na **niepuste podzbiory**.  
            Na przykład dla zbioru \\{1, 2, 3\\}, liczba Bella \\( B_3 = 5 \\), ponieważ istnieje 5 różnych sposobów podziału zbioru:  
            \\[
            \\{ \\{1\\}, \\{2\\}, \\{3\\} \\}, \\{ \\{1, 2\\}, \\{3\\} \\}, \\{ \\{1, 3\\}, \\{2\\} \\}, \\{ \\{2, 3\\}, \\{1\\} \\}, \\{ \\{1, 2, 3\\} \\}.
            \\]
            
            **Podejście do nauki:**  
            - Zrozum znaczenie liczby Bella jako kombinatorycznego problemu podziału zbiorów.  
            - Rozpocznij od małych zbiorów i ręcznie wypisz wszystkie możliwe podziały.  
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Własności liczb Bella
            Liczby Bella mają kilka istotnych własności:  
            - **Wzór rekurencyjny:**  
            \\[
            B_{n+1} = \\sum_{k=0}^n \\binom{n}{k} B_k
            \\]
            - **Pierwsze liczby Bella:**  
            \\[
            B_0 = 1, B_1 = 1, B_2 = 2, B_3 = 5, B_4 = 15, B_5 = 52, \\dots
            \\]
            
            **Podejście do nauki:**  
            - Naucz się rozumieć i stosować wzór rekurencyjny.  
            - Zapamiętaj kilka pierwszych liczb Bella, aby zrozumieć ich wzrost.  
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Obliczanie liczb Bella przy użyciu wzoru rekurencyjnego
            Liczby Bella można obliczać przy pomocy wzoru rekurencyjnego:  
            \\[
            B_{n+1} = \\sum_{k=0}^n \\binom{n}{k} B_k
            \\]
            Gdzie \\( B_0 = 1 \\).  
            
            **Podejście do nauki:**  
            - Zrozum, jak działa wzór rekurencyjny.  
            - Obliczaj kolejne liczby Bella krok po kroku na podstawie wcześniejszych wartości.  
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Obliczanie liczb Bella za pomocą tablicy Stirlinga II rodzaju
            Liczby Bella można obliczyć przez sumowanie wartości wierszy **tablicy Stirlinga II rodzaju**:  
            \\[
            B_n = \\sum_{k=0}^n S(n, k),
            \\]
            gdzie \\( S(n, k) \\) to liczba podziałów zbioru na \\( k \\) niepustych podzbiorów.  
            
            **Podejście do nauki:**  
            - Naucz się generować tablicę Stirlinga II rodzaju.  
            - Praktykuj obliczanie liczb Bella na podstawie tablicy.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Graficzna reprezentacja liczb Bella
            Liczby Bella mogą być przedstawione na wykresach liniowych lub słupkowych, które pokazują ich szybki wzrost wraz z rosnącym \\( n \\).  
            
            **Podejście do nauki:**  
            - Zbierz wartości liczb Bella i przedstaw je graficznie.  
            - Przeanalizuj kształt wykresu i zrozum tempo wzrostu liczb Bella.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Zastosowania liczb Bella w kombinatoryce
            Liczby Bella znajdują zastosowanie w:  
            - Problemach podziału zbiorów,  
            - Teorii grafów,  
            - Problemach klasyfikacji i enumeracji.  
            
            **Podejście do nauki:**  
            - Pracuj nad przykładami problemów podziału zbioru, aby zrozumieć praktyczne znaczenie liczb Bella.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Przykłady zadań praktycznych z liczbami Bella
            Rozwiąż zadania takie jak:  
            - Oblicz liczbę podziałów zbioru o \\( n \\) elementach,  
            - Znajdź \\( B_n \\) przy użyciu wzoru rekurencyjnego lub tablicy Stirlinga.  
            
            **Podejście do nauki:**  
            - Ćwicz różne metody obliczeń liczb Bella w praktyce.
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Analiza asymptotyczna liczb Bella
            Liczby Bella rosną w sposób asymptotyczny. Przybliżony wzór:  
            \\[
            B_n \\sim \\frac{1}{\\sqrt{n}} \\left( \\frac{n}{\\ln(n)} \\right)^n.
            \\]
            **Podejście do nauki:**  
            - Zrozum, jak analizować wzrost liczb Bella przy dużych \\( n \\).
            """;

    public static final String SUB_REQUIREMENT_9 = """
            ### Zaawansowane metody obliczania liczb Bella
            Użycie algorytmów programowania dynamicznego oraz funkcji generujących do obliczania liczb Bella.  
            """;

    public static final String SUB_REQUIREMENT_10 = """
            ### Zastosowania liczb Bella w naukach ścisłych i informatyce
            Liczby Bella pojawiają się w analizie algorytmów, kryptografii oraz optymalizacji.  
            """;
}

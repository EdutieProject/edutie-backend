package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleNewtonPolynomialsLearningRequirement {
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
                "Definicja wielomianów Newtona",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Wielomiany Newtona w kontekście podziałów zbiorów",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Schemat Newtona do obliczeń liczbowych",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Współczynniki Newtona i ich interpretacja kombinatoryczna",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Przykłady obliczeń dla małych zbiorów",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie wielomianów Newtona do zadań z wyborem opcji",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Uogólnione wielomiany Newtona i ich własności",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Analiza złożoności obliczeń z wykorzystaniem wielomianów Newtona",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowania w podziałach zbiorów na podzbiory",
                PromptFragment.of(SUB_REQUIREMENT_9)
        );
        learningRequirement.appendSubRequirement(
                "Strategie rozwiązywania złożonych zadań praktycznych",
                PromptFragment.of(SUB_REQUIREMENT_10)
        );

        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleNewtonPolynomialsLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Wielomiany Newtona";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja wielomianów Newtona
            Wielomiany Newtona są sekwencją wielomianów, które umożliwiają interpolację danych oraz obliczenia w kontekście podziałów i rozmieszczeń.  
            Ich postać ogólna to:
            \\[
            P(x) = a_0 + a_1(x - x_0) + a_2(x - x_0)(x - x_1) + \\dots
            \\]
            - Współczynniki \\(a_i\\) są obliczane w oparciu o różnice skończone.

            **Podejście do nauki:**  
            - Zrozum strukturę wielomianów Newtona i rolę współczynników.  
            - Przećwicz wyprowadzenie dla prostych danych wejściowych.  
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Wielomiany Newtona w kontekście podziałów zbiorów
            Wielomiany Newtona można stosować do obliczania liczby sposobów podziału zbioru na podzbiory o różnych rozmiarach.

            **Przykład:**  
            Dla zbioru o rozmiarze \\( n = 4 \\), liczba sposobów podziału zbioru jest związana z odpowiednimi współczynnikami wielomianów Newtona.

            **Podejście do nauki:**  
            - Poznaj interpretację kombinatoryczną współczynników wielomianów Newtona.  
            - Ćwicz zastosowanie tych metod w zadaniach z podziałami zbiorów.  
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Schemat Newtona do obliczeń liczbowych
            Schemat Newtona jest algorytmem pozwalającym na efektywne obliczanie wartości wielomianu Newtona.  
            **Kroki:**
            1. Oblicz różnice skończone.  
            2. Zbuduj kolejne człony wielomianu.  
            3. Podstaw wartość do wielomianu, aby uzyskać wynik.

            **Podejście do nauki:**  
            - Naucz się implementacji schematu krok po kroku.  
            - Rozwiąż kilka zadań obliczeniowych dla różnych wielomianów Newtona.  
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Współczynniki Newtona i ich interpretacja kombinatoryczna
            Współczynniki wielomianu Newtona \\( a_i \\) można interpretować jako liczby odpowiadające sposobom wyboru i podziału elementów.  

            **Podejście do nauki:**  
            - Zrozum, jak obliczać współczynniki Newtona.  
            - Naucz się ich interpretacji w kontekście podziałów i wyborów.  
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Przykłady obliczeń dla małych zbiorów
            Przećwicz zastosowanie wielomianów Newtona dla zbiorów o małym rozmiarze (np. \\( n = 3, 4, 5 \\)).  

            **Podejście do nauki:**  
            - Rozwiąż konkretne zadania obliczeniowe krok po kroku.  
            - Porównaj uzyskane wyniki z ręcznymi obliczeniami.  
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Zastosowanie wielomianów Newtona do zadań z wyborem opcji
            Wielomiany Newtona można stosować w zadaniach polegających na rozmieszczaniu elementów, gdzie występują różne opcje do wyboru.  

            **Podejście do nauki:**  
            - Naucz się modelować zadania z wyborami za pomocą wielomianów.  
            - Rozwiąż zadania praktyczne związane z rozmieszczeniem elementów.  
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Uogólnione wielomiany Newtona i ich własności
            Poznaj rozszerzenia wielomianów Newtona na bardziej złożone sytuacje (np. wielomiany wielowymiarowe).  

            **Podejście do nauki:**  
            - Zrozum koncepcję uogólnienia wielomianów.  
            - Rozwiąż przykłady z bardziej skomplikowanymi podziałami zbiorów.  
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Analiza złożoności obliczeń z wykorzystaniem wielomianów Newtona
            Wielomiany Newtona są efektywnym narzędziem obliczeniowym. Poznaj ich złożoność obliczeniową w różnych zadaniach.  

            **Podejście do nauki:**  
            - Przeanalizuj kroki algorytmu pod kątem efektywności.  
            - Zrozum, jak optymalizować obliczenia w praktyce.  
            """;

    public static final String SUB_REQUIREMENT_9 = """
            ### Zastosowania w podziałach zbiorów na podzbiory
            Wielomiany Newtona pozwalają analizować podziały zbiorów na różne grupy, co ma zastosowanie w kombinatoryce i statystyce.  

            **Podejście do nauki:**  
            - Rozwiąż zadania polegające na podziale zbiorów.  
            - Użyj wielomianów do uzasadniania wyników.  
            """;

    public static final String SUB_REQUIREMENT_10 = """
            ### Strategie rozwiązywania złożonych zadań praktycznych
            Naucz się łączyć techniki wielomianów Newtona z innymi metodami matematycznymi do rozwiązywania zaawansowanych problemów.  

            **Podejście do nauki:**  
            - Rozwiązuj złożone zadania krok po kroku.  
            - Opracuj strategie do zadań praktycznych z życia codziennego.  
            """;
}

package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleSetsWithRepetitionsLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        if (learningRequirementPersistence.getRepository().findAll().stream().anyMatch(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME))) {
            log.info("Learning requirement {} already present in the DB, omitting seeding", LEARNING_REQUIREMENT_NAME);
            requirement = learningRequirementPersistence.getRepository().findAll().stream().filter(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME)).findFirst().get();
            isSeeded = true;
            return;
        }
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);

        learningRequirement.appendSubRequirement(
                "Definicja zbiorów z powtórzeniami",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Podstawowe operacje na zbiorach z powtórzeniami",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Wybieranie podzbiorów z powtórzeniami",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Wybieranie podzbiorów bez powtórzeń",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Wzór na liczbę podzbiorów z powtórzeniami",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Strategie rozwiązywania zadań praktycznych",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Złożone problemy kombinatoryczne z powtórzeniami",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie zbiorów z powtórzeniami w analizie danych",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirement.appendSubRequirement(
                "Porównanie zbiorów z powtórzeniami i bez powtórzeń",
                PromptFragment.of(SUB_REQUIREMENT_9)
        );
        learningRequirement.appendSubRequirement(
                "Zadania mieszane – różne techniki wyboru podzbiorów",
                PromptFragment.of(SUB_REQUIREMENT_10)
        );

        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleSetsWithRepetitionsLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Zbiory z Powtórzeniami i Wybieranie Podzbiorów";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja zbiorów z powtórzeniami
            Zbiór z powtórzeniami to zbiór, w którym elementy mogą się powtarzać. Przykład:  
            \\[
            \\{ a, a, b, c \\}
            \\]  
            W tym zbiorze element \\( a \\) pojawia się dwukrotnie.

            **Podejście do nauki:**  
            - Zrozum definicję i różnicę między zbiorami z powtórzeniami a klasycznymi zbiorami.  
            - Przygotuj przykłady z życia codziennego (np. litery w wyrazach).  
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Podstawowe operacje na zbiorach z powtórzeniami
            Operacje na zbiorach z powtórzeniami obejmują:  
            - **Sumę zbiorów** – połączenie elementów z powtórzeniami.  
            - **Iloczyn zbiorów** – elementy wspólne z uwzględnieniem liczby powtórzeń.  
            - **Różnicę zbiorów** – usunięcie wspólnych elementów.

            **Podejście do nauki:**  
            - Naucz się rozpoznawać i zapisywać operacje na zbiorach.  
            - Rozwiąż zadania z iloczynem i różnicą zbiorów.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Wybieranie podzbiorów z powtórzeniami
            W przypadku zbiorów z powtórzeniami można wybierać podzbiory, w których elementy mogą się powtarzać.  
            **Wzór:**  
            Liczba sposobów wyboru podzbioru z powtórzeniami z \\( n \\) elementów:  
            \\[
            C_{powt}(n, k) = \\binom{n + k - 1}{k}
            \\]
            gdzie \\( k \\) to liczba elementów w podzbiorze.

            **Podejście do nauki:**  
            - Utrwal wzór na kombinacje z powtórzeniami.  
            - Rozwiąż przykłady z wybieraniem podzbiorów z powtarzalnymi elementami.  
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Wybieranie podzbiorów bez powtórzeń
            Wybieranie podzbiorów z klasycznego zbioru polega na wyborze elementów, które nie powtarzają się.  
            **Wzór:**  
            \\[
            C(n, k) = \\binom{n}{k}
            \\]  
            gdzie \\( n \\) to liczba elementów w zbiorze, a \\( k \\) liczba elementów w podzbiorze.

            **Podejście do nauki:**  
            - Przećwicz zadania z wybieraniem podzbiorów bez powtórzeń.  
            - Porównaj z wyborem podzbiorów z powtórzeniami.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Wzór na liczbę podzbiorów z powtórzeniami
            Wzór \\( C_{powt}(n, k) = \\binom{n+k-1}{k} \\) umożliwia liczenie podzbiorów z powtórzeniami.  
            **Podejście do nauki:**  
            - Zastosuj wzór w przykładach praktycznych.  
            - Rozwiąż zadania krok po kroku, analizując liczbę wyborów.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Strategie rozwiązywania zadań praktycznych
            Zadania praktyczne mogą obejmować:  
            - Wybór kul z urny,  
            - Układanie przedmiotów z powtarzającymi się elementami,  
            - Tworzenie kombinacji liter w słowach.

            **Podejście do nauki:**  
            - Naucz się analizować zadanie i rozpoznawać typ wyboru (z powtórzeniami lub bez).  
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Złożone problemy kombinatoryczne z powtórzeniami
            Rozwiązuj zadania, które wymagają łączenia:  
            - Wyboru podzbiorów,  
            - Uwzględniania powtórzeń elementów,  
            - Ograniczeń takich jak „co najmniej” lub „co najwyżej”.

            **Podejście do nauki:**  
            - Ćwicz zadania o różnym stopniu trudności.
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Zastosowanie zbiorów z powtórzeniami w analizie danych
            Zbiory z powtórzeniami są szeroko stosowane w analizie danych, statystyce oraz kryptografii.  
            **Podejście do nauki:**  
            - Poznaj przykłady praktyczne zastosowania w analizie danych.
            """;

    public static final String SUB_REQUIREMENT_9 = """
            ### Porównanie zbiorów z powtórzeniami i bez powtórzeń
            Zrozum różnice między zbiorami:  
            - Z powtórzeniami: Elementy mogą się powtarzać.  
            - Bez powtórzeń: Każdy element jest unikalny.

            **Podejście do nauki:**  
            - Rozwiąż zadania porównawcze i naucz się rozpoznawać typ zbioru.  
            """;

    public static final String SUB_REQUIREMENT_10 = """
            ### Zadania mieszane – różne techniki wyboru podzbiorów
            Zadania wymagające łączenia technik:  
            - Wybierania z powtórzeniami,  
            - Klasycznych kombinacji,  
            - Analizy warunków ograniczających.

            **Podejście do nauki:**  
            - Rozwiązuj zadania krok po kroku, stosując właściwe techniki.
            """;
}

package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleEquivalenceRelationsLearningRequirement {
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
                "Definicja relacji równoważności",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Własności relacji równoważności",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Podział zbioru przez relację równoważności",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Przykłady relacji równoważności",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Podziały zbiorów w zadaniach kombinatorycznych",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Relacje równoważności w zadaniach praktycznych",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie relacji równoważności do rozwiązywania równań",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Relacje równoważności w teorii klas",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirement.appendSubRequirement(
                "Tworzenie klas abstrakcji przez relacje równoważności",
                PromptFragment.of(SUB_REQUIREMENT_9)
        );
        learningRequirement.appendSubRequirement(
                "Przykłady z życia codziennego związane z relacjami równoważności",
                PromptFragment.of(SUB_REQUIREMENT_10)
        );

        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleEquivalenceRelationsLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Relacje Równoważności";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja relacji równoważności
            Relacja \\(R\\) na zbiorze \\(A\\) jest relacją równoważności, jeśli spełnia trzy właściwości:
            - **Refleksyjność**: dla każdego \\(a \\in A\\), \\(a R a\\),
            - **Symetryczność**: dla każdego \\(a, b \\in A\\), jeśli \\(a R b\\), to \\(b R a\\),
            - **Przechodniość**: dla każdego \\(a, b, c \\in A\\), jeśli \\(a R b\\) i \\(b R c\\), to \\(a R c\\).

            **Podejście do nauki:**  
            - Zapoznaj się z definicjami właściwości relacji.  
            - Sprawdź, jak relacje równoważności można rozpoznać i udowodnić na przykładach.  
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Własności relacji równoważności
            Relacja równoważności posiada kilka istotnych własności:
            - **Podział zbioru na klasy abstrakcji**: Relacja ta dzieli zbiór na disjoint podzbiory, które nazywamy **klasami abstrakcji**.
            - **Reprezentacja klas abstrakcji**: Każdy element zbioru należy do jednej klasy abstrakcji, której członkowie są ze sobą równoważni w sensie danej relacji.

            **Podejście do nauki:**  
            - Zrozum, czym są klasy abstrakcji i jak je tworzymy.  
            - Ćwicz rozpoznawanie i tworzenie klas abstrakcji na podstawie relacji równoważności.  
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Podział zbioru przez relację równoważności
            Podział zbioru \\(A\\) przez relację równoważności \\(R\\) prowadzi do utworzenia zbioru klas abstrakcji \\([a]_R\\), gdzie każda klasa abstrakcji zawiera elementy, które są równoważne w sensie tej relacji.

            **Podejście do nauki:**  
            - Zrozum, jak relacja równoważności wpływa na podział zbioru.  
            - Ćwicz tworzenie podziałów na podstawie przykładów.  
            - Zastosuj teorię do rozwiązania zadań praktycznych.  
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Przykłady relacji równoważności
            Przykłady popularnych relacji równoważności to:
            - Relacja "bycia równością" na liczbach,
            - Relacja "posiadania tej samej liczby liter" w wyrazach.

            **Podejście do nauki:**  
            - Ćwicz na przykładach, rozpoznając czy dana relacja jest równoważnością.  
            - Ustal, które zbiorów można podzielić na klasy abstrakcji przy użyciu tych relacji.  
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Podziały zbiorów w zadaniach kombinatorycznych
            W zadaniach kombinatorycznych relacje równoważności pozwalają na obliczanie liczby sposobów podziałów zbiorów na podzbiory o różnych rozmiarach.

            **Podejście do nauki:**  
            - Rozwiązuj zadania związane z podziałami zbiorów.  
            - Użyj definicji relacji równoważności, aby określić, jakie podziały są możliwe.  
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Relacje równoważności w zadaniach praktycznych
            W zadaniach praktycznych, takich jak analiza grup czy klasyfikacja elementów, relacje równoważności pomagają w tworzeniu grup elementów, które mają wspólne właściwości.

            **Podejście do nauki:**  
            - Ćwicz rozwiązywanie zadań z życia codziennego, wykorzystując relacje równoważności.  
            - Naucz się rozpoznawać, jak klasy abstrakcji mogą zostać zastosowane do różnych problemów praktycznych.  
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Zastosowanie relacji równoważności do rozwiązywania równań
            Relacje równoważności są pomocne przy rozwiązywaniu równań, które można traktować jako zadania polegające na znalezieniu klas abstrakcji równych wartościom.

            **Podejście do nauki:**  
            - Zrozum, jak relacja równoważności może pomóc w uproszczeniu rozwiązywania równań.  
            - Przećwicz rozwiązywanie prostych równań z wykorzystaniem tej koncepcji.  
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Relacje równoważności w teorii klas
            Relacje równoważności w teorii klas prowadzą do podziałów zbiorów na różne klasy, które mają podobne właściwości. 

            **Podejście do nauki:**  
            - Zrozum, jak relacje równoważności wpływają na teorię klas i klasyfikację obiektów.  
            - Zastosuj teorię klas do rozwiązywania problemów z tej dziedziny.  
            """;

    public static final String SUB_REQUIREMENT_9 = """
            ### Tworzenie klas abstrakcji przez relacje równoważności
            Klasy abstrakcji tworzone przez relacje równoważności grupują elementy, które są sobie równoważne. Każda klasa abstrakcji jest zbiorem elementów, które mają wspólne właściwości.

            **Podejście do nauki:**  
            - Ćwicz rozwiązywanie zadań związanych z tworzeniem klas abstrakcji w różnych zbiorach.  
            - Zrozumienie i ćwiczenie tworzenia klas abstrakcji pozwala na lepsze zrozumienie teorii zbiorów i matematycznych relacji.  
            """;

    public static final String SUB_REQUIREMENT_10 = """
            ### Przykłady z życia codziennego związane z relacjami równoważności
            Przykładem z życia codziennego może być klasyfikacja książek do różnych gatunków literackich, gdzie każda książka należy do jednej klasy abstrakcji w zależności od swojego gatunku.

            **Podejście do nauki:**  
            - Pracuj z przykładami z codziennego życia, aby lepiej zrozumieć zastosowanie relacji równoważności w różnych dziedzinach.  
            - Ustal, jakie klasyfikacje można stworzyć w zależności od różnych relacji równoważności.  
            """;
}

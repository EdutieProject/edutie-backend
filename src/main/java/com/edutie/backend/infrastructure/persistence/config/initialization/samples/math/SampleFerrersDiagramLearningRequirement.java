package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleFerrersDiagramLearningRequirement {
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
                "Definicja Diagramów Ferrersa",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Tworzenie Diagramów Ferrersa",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Interpretacja Diagramów Ferrersa",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Porównywanie podziałów liczbowych z użyciem Diagramów Ferrersa",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Diagramy Ferrersa a macierze Younga",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie Diagramów Ferrersa w kombinatoryce",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Zadania z liczeniem podziałów liczbowych",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Równoważność diagramów w transpozycji Ferrersa",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirement.appendSubRequirement(
                "Zaawansowane zastosowania w teorii liczb",
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
            throw new RuntimeException(SampleFerrersDiagramLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Diagramy Ferrersa";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja Diagramów Ferrersa
            Diagram Ferrersa to graficzna reprezentacja podziału liczby naturalnej na sumę części składowych. 
            Przykładowo, dla podziału liczby \\( n = 5 \\) jako \\( 3 + 2 \\), diagram Ferrersa wygląda następująco:
            ```
            ● ● ●  
            ● ●  
            ```
            - Każda linia reprezentuje jedną część składową podziału.  
            - Długość linii odpowiada wartości tej części.

            **Podejście do nauki:**  
            - Zrozum definicję i strukturę diagramu Ferrersa.  
            - Narysuj kilka przykładów diagramów dla różnych podziałów liczbowych.  
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Tworzenie Diagramów Ferrersa
            Diagramy Ferrersa można tworzyć krok po kroku na podstawie podziału liczby.  
            **Przykład:**  
            Dla liczby \\( n = 7 \\) i podziału \\( 4 + 2 + 1 \\):  
            ```
            ● ● ● ●  
            ● ●  
            ●  
            ```
            - Rozpocznij od największej części i rysuj rzędy kropek w kolejności malejącej.

            **Podejście do nauki:**  
            - Ćwicz tworzenie diagramów Ferrersa dla różnych podziałów liczbowych.  
            - Staraj się zauważyć, jak zmienia się liczba linii i kropek przy różnych podziałach.  
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Interpretacja Diagramów Ferrersa
            Diagramy Ferrersa mogą być interpretowane na wiele sposobów:  
            - **Liczba rzędów** odpowiada liczbie części podziału.  
            - **Liczba kropek w każdym rzędzie** odpowiada wartości części składowej.  
            - **Całkowita liczba kropek** to suma podziału.

            **Podejście do nauki:**  
            - Naucz się „czytać” diagramy Ferrersa.  
            - Ćwicz interpretację podziałów na podstawie narysowanych diagramów.  
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Porównywanie podziałów liczbowych z użyciem Diagramów Ferrersa
            Diagramy Ferrersa pozwalają łatwo porównać dwa podziały liczby.  
            **Przykład:**  
            Dla liczby \\( n = 6 \\):  
            - Podział \\( 4 + 2 \\):  
              ```
              ● ● ● ●  
              ● ●  
              ```
            - Podział \\( 3 + 3 \\):  
              ```
              ● ● ●  
              ● ● ●  
              ```

            **Podejście do nauki:**  
            - Porównuj różne podziały liczby, analizując ich strukturę wizualną.  
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Diagramy Ferrersa a macierze Younga
            Diagram Ferrersa jest ściśle powiązany z macierzami Younga, które znajdują zastosowanie w teorii reprezentacji.  
            Macierz Younga to diagram Ferrersa z dodatkowymi oznaczeniami (np. numeracja komórek).

            **Podejście do nauki:**  
            - Poznaj różnice między diagramem Ferrersa a macierzami Younga.  
            - Ćwicz konwersję między tymi reprezentacjami.  
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Zastosowanie Diagramów Ferrersa w kombinatoryce
            Diagramy Ferrersa mają szerokie zastosowanie w kombinatoryce, np.:  
            - Analiza podziałów liczbowych.  
            - Dowody istnienia podziałów symetrycznych.  

            **Podejście do nauki:**  
            - Rozwiąż zadania związane z liczeniem podziałów liczbowych za pomocą diagramów Ferrersa.  
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Zadania z liczeniem podziałów liczbowych
            Rozwiązuj zadania polegające na tworzeniu diagramów dla podziałów liczbowych.  
            **Przykład:**  
            Znajdź wszystkie podziały liczby \\( 5 \\) i narysuj ich diagramy.

            **Podejście do nauki:**  
            - Systematycznie generuj wszystkie podziały liczby i ich odpowiadające diagramy.  
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Równoważność diagramów w transpozycji Ferrersa
            Diagramy Ferrersa można transponować (zamieniać rzędy na kolumny).  
            **Przykład:**  
            Oryginalny diagram:  
            ```
            ● ● ●  
            ● ●  
            ●  
            ```
            Transpozycja:  
            ```
            ● ● ●  
            ● ●  
            ●  
            ```

            **Podejście do nauki:**  
            - Ćwicz transpozycję diagramów.  
            - Zrozum, jak transpozycja wpływa na strukturę podziału.  
            """;

    public static final String SUB_REQUIREMENT_9 = """
            ### Zaawansowane zastosowania w teorii liczb
            Diagramy Ferrersa można wykorzystywać w zaawansowanych problemach z teorii liczb, takich jak:  
            - Podziały liczby na różne sposoby.  
            - Dowody istnienia równości między podziałami symetrycznymi.  

            **Podejście do nauki:**  
            - Rozwiązuj bardziej złożone problemy wymagające analizy podziałów liczbowych.
            """;

    public static final String SUB_REQUIREMENT_10 = """
            ### Strategie rozwiązywania złożonych zadań praktycznych
            Rozwiązuj zadania łączące tworzenie diagramów, transpozycję oraz porównywanie podziałów.  
            **Podejście do nauki:**  
            - Ćwicz krok po kroku.  
            - Wykorzystuj diagramy do wizualizacji problemów.  
            """;
}

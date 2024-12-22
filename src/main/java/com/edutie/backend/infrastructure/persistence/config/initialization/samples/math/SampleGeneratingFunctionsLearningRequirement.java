package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleGeneratingFunctionsLearningRequirement {
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
                "Definicja funkcji tworzącej",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Typy funkcji tworzących",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie funkcji tworzącej do obliczania liczb Fibonacciego",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Operacje na funkcjach tworzących",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Funkcja tworząca dla ciągów złożonych",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Przykłady funkcji tworzących w zadaniach kombinatorycznych",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Funkcje tworzące w teorii liczb",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Funkcje tworzące a zadania ze zbiorami i permutacjami",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie funkcji tworzącej w rachunku różniczkowym i całkowym",
                PromptFragment.of(SUB_REQUIREMENT_9)
        );
        learningRequirement.appendSubRequirement(
                "Funkcje tworzące w analizie asymptotycznej",
                PromptFragment.of(SUB_REQUIREMENT_10)
        );

        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleGeneratingFunctionsLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Funkcje Tworzące";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja funkcji tworzącej
            Funkcja tworząca to funkcja, która koduje informacje o ciągu liczbowym w postaci jednej funkcji. Dzięki funkcji tworzącej można łatwo operować na ciągach, wykonując na nich operacje algebraiczne. Najczęściej stosowane funkcje tworzące to funkcje generujące, takie jak funkcja tworząca dla ciągu (generating function) czy funkcje charakterystyczne.

            **Podejście do nauki:**  
            - Zapoznaj się z definicją funkcji tworzącej oraz jej reprezentacją matematyczną.  
            - Zrozum, jak funkcje tworzące mogą reprezentować różne ciągi liczbowe.  
            - Zrozum podstawowe operacje na funkcjach tworzących i ich zastosowanie w rozwiązywaniu problemów kombinatorycznych.  
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Typy funkcji tworzących
            Istnieje kilka rodzajów funkcji tworzących:
            - **Funkcja tworząca zwykła** – najprostsza funkcja generująca dla ciągu liczbowego.
            - **Funkcja tworząca wykładnicza** – bardziej zaawansowana funkcja do reprezentacji ciągów związaną z wykładniczymi transformacjami.
            - **Funkcja tworząca z tzw. sprzężeniem** – używana do reprezentacji bardziej złożonych ciągów.

            **Podejście do nauki:**  
            - Poznaj różne rodzaje funkcji tworzących.  
            - Ćwicz rozpoznawanie odpowiednich typów funkcji dla różnych typów ciągów.  
            - Naucz się używać ich w obliczeniach.  
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Zastosowanie funkcji tworzącej do obliczania liczb Fibonacciego
            Funkcja tworząca jest bardzo przydatna przy obliczaniu liczb Fibonacciego. Za pomocą odpowiedniej funkcji tworzącej możemy uzyskać wyrazy ciągu Fibonacciego w sposób algebraiczny, unikając ręcznego liczenia każdego wyrazu.

            **Podejście do nauki:**  
            - Naucz się formułować funkcję tworzącą dla ciągu Fibonacciego.  
            - Zrozum, jak transformacje funkcji tworzącej mogą być użyte do uzyskiwania kolejnych wyrazów ciągu.  
            - Ćwicz rozwiązywanie zadań z użyciem funkcji tworzącej dla liczb Fibonacciego.  
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Operacje na funkcjach tworzących
            Na funkcjach tworzących można wykonywać różnorodne operacje algebraiczne, takie jak dodawanie, mnożenie czy dzielenie. Te operacje są szczególnie przydatne w zadaniach kombinatorycznych.

            **Podejście do nauki:**  
            - Poznaj podstawowe operacje na funkcjach tworzących.  
            - Zrozum, jak operacje te mogą pomóc w obliczaniu liczb w zadaniach z permutacjami, kombinacjami czy podziałami zbiorów.  
            - Ćwicz operowanie na funkcjach tworzących w różnych zadaniach.  
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Funkcja tworząca dla ciągów złożonych
            Funkcje tworzące mogą być używane nie tylko dla prostych ciągów, ale także dla bardziej złożonych struktur, takich jak ciągi, w których występują różne zmienne lub zależności między składnikami.

            **Podejście do nauki:**  
            - Naucz się, jak formułować funkcje tworzące dla złożonych ciągów.  
            - Zrozum, jak łączyć różne funkcje tworzące w jedno wyrażenie, które będzie reprezentować złożony ciąg.  
            - Ćwicz na przykładach złożonych ciągów.  
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Przykłady funkcji tworzących w zadaniach kombinatorycznych
            Funkcje tworzące są bardzo przydatne w zadaniach kombinatorycznych, np. przy obliczaniu liczby permutacji, kombinacji, czy podziałów zbiorów. Dzięki funkcjom tworzącym można szybko obliczyć liczby bez konieczności wykonywania żmudnych obliczeń.

            **Podejście do nauki:**  
            - Rozwiązuj zadania z zakresu kombinatoryki, używając funkcji tworzących.  
            - Zrozum, jak funkcje tworzące umożliwiają szybkie obliczenia dla liczb permutacji, kombinacji i innych struktur kombinatorycznych.  
            - Ćwicz obliczanie różnych problemów kombinatorycznych.  
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Funkcje tworzące w teorii liczb
            Funkcje tworzące znajdują zastosowanie także w teorii liczb, szczególnie przy obliczaniu liczb pierwszych, liczb podzielnych przez określoną liczbę, czy analizie struktur liczbowych.

            **Podejście do nauki:**  
            - Zrozum, jak funkcje tworzące są wykorzystywane w teorii liczb.  
            - Ćwicz rozwiązywanie zadań z teorii liczb z wykorzystaniem funkcji tworzących.  
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Funkcje tworzące a zadania ze zbiorami i permutacjami
            Funkcje tworzące są również przydatne w zadaniach związanych z permutacjami, np. obliczanie liczby permutacji zbiorów z powtórzeniami.

            **Podejście do nauki:**  
            - Naucz się formułować funkcję tworzącą dla zadań z permutacjami.  
            - Zastosuj funkcje tworzące do obliczeń związanych z różnymi rodzajami permutacji.  
            - Ćwicz na zadaniach z tej tematyki.  
            """;

    public static final String SUB_REQUIREMENT_9 = """
            ### Zastosowanie funkcji tworzącej w rachunku różniczkowym i całkowym
            Funkcje tworzące mają także zastosowanie w analizie matematycznej, w tym w rachunku różniczkowym i całkowym. Mogą one pomóc przy rozwiązywaniu równań różniczkowych czy obliczaniu całek.

            **Podejście do nauki:**  
            - Zrozum, jak funkcje tworzące są używane w analizie matematycznej.  
            - Naucz się wykorzystywać funkcje tworzące w zadaniach z rachunku różniczkowego i całkowego.  
            """;

    public static final String SUB_REQUIREMENT_10 = """
            ### Funkcje tworzące w analizie asymptotycznej
            Funkcje tworzące są także użyteczne w analizie asymptotycznej, szczególnie w badaniu wzrostu ciągów i określaniu ich zachowania w nieskończoności.

            **Podejście do nauki:**  
            - Poznaj zastosowanie funkcji tworzących w analizie asymptotycznej.  
            - Zrozum, jak funkcje tworzące mogą pomóc w analizie dużych liczb i ich wzrostu.  
            - Ćwicz rozwiązywanie problemów asymptotycznych z wykorzystaniem funkcji tworzących.  
            """;
}

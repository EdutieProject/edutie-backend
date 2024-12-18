package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleDiophantineEquationLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        if (learningRequirementPersistence.getRepository().findAll().stream()
                .anyMatch(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME))) {
            log.info("Learning requirement {} already present in the DB, omitting seeding", LEARNING_REQUIREMENT_NAME);
            requirement = learningRequirementPersistence.getRepository().findAll()
                    .stream()
                    .filter(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME))
                    .findFirst()
                    .get();
            isSeeded = true;
            return;
        }
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);

        learningRequirement.appendSubRequirement(
                "Definicja równań diofantycznych",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Klasyfikacja równań diofantycznych",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Metody znajdowania rozwiązań równań diofantycznych",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Przykłady rozwiązań równań diofantycznych",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Analiza istnienia rozwiązań",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Algorytmiczne podejście do równań diofantycznych",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Zadania praktyczne z równań diofantycznych",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Historia i znaczenie równań diofantycznych",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirement.appendSubRequirement(
                "Zaawansowane przykłady analityczne",
                PromptFragment.of(SUB_REQUIREMENT_9)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowania równań diofantycznych w różnych dziedzinach nauki",
                PromptFragment.of(SUB_REQUIREMENT_10)
        );

        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleDiophantineEquationLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Równania Diofantyczne";
    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja równań diofantycznych
            Równania diofantyczne to równania, których rozwiązania szuka się **w liczbach całkowitych**.  
            Przykładem równania diofantycznego jest równanie liniowe:  
            \\[
            ax + by = c,
            \\]
            gdzie \\( a, b, c \\) to liczby całkowite, a \\( x, y \\) to niewiadome, których rozwiązania także muszą być całkowite.  
            
            **Podejście do nauki:**  
            - Zrozum definicję równań diofantycznych i rozróżniaj je od równań z rozwiązaniami w liczbach rzeczywistych.  
            - Przeanalizuj przykłady prostych równań diofantycznych. Zastanów się, dlaczego nie każde równanie ma rozwiązania całkowite.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Klasyfikacja równań diofantycznych
            Równania diofantyczne można podzielić na różne typy:  
            - **Liniowe równania diofantyczne:** mają postać \\( ax + by = c \\).  
            - **Wielomianowe równania wyższych stopni:** np. \\( x^2 + y^2 = z^2 \\), które opisują trójki pitagorejskie.  
            - **Systemy równań diofantycznych:** kilka równań rozwiązywanych jednocześnie.  

            **Podejście do nauki:**  
            - Naucz się klasyfikować równania diofantyczne według ich stopnia oraz liczby zmiennych.  
            - Ćwicz rozpoznawanie typu równania i określanie, jakiego podejścia wymaga jego rozwiązanie (np. algorytmicznego czy algebraicznego).
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Metody znajdowania rozwiązań równań diofantycznych
            Rozwiązania równań diofantycznych wymagają konkretnych metod:  
            - **Algorytm Euklidesa:** szczególnie przydatny dla równań liniowych.  
            - **Metoda podstawiania i eliminacji:** stosowana dla systemów równań.  
            - **Redukcja wielomianów:** dla równań wyższych stopni.  

            **Podejście do nauki:**  
            - Naucz się krok po kroku stosować algorytm Euklidesa do równań postaci \\( ax + by = c \\).  
            - Ćwicz metody algebraiczne i podstawianie dla bardziej złożonych przypadków.  
            - Rozwiązuj przykłady manualnie, aby opanować procedury obliczeniowe.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Przykłady rozwiązań równań diofantycznych
            Praktyczne przykłady pomagają zrozumieć teorię. Przykład dla równania:  
            \\[
            3x + 5y = 7.
            \\]  
            Rozwiązaniem tego równania mogą być pary \\( (x, y) \\), gdzie \\( x \\) i \\( y \\) są liczbami całkowitymi.  

            **Podejście do nauki:**  
            - Przeanalizuj przykłady krok po kroku.  
            - Znajdź przynajmniej jedno rozwiązanie, a następnie wyprowadź wzór na wszystkie rozwiązania.  
            - Spróbuj samodzielnie rozwiązywać przykłady o różnym stopniu trudności.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Analiza istnienia rozwiązań
            Nie każde równanie diofantyczne ma rozwiązania całkowite.  
            Dla równania liniowego \\( ax + by = c \\):  
            - Rozwiązania istnieją tylko wtedy, gdy największy wspólny dzielnik (NWD) \\( a \\) i \\( b \\) dzieli \\( c \\).  

            **Podejście do nauki:**  
            - Naucz się wyznaczać NWD przy użyciu algorytmu Euklidesa.  
            - Sprawdź, czy dane równanie ma rozwiązania, analizując podzielność.  
            - Praktykuj analizę istnienia rozwiązań dla różnych równań.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Algorytmiczne podejście do równań diofantycznych
            Podejścia algorytmiczne pozwalają efektywnie znaleźć rozwiązania. Kluczowe algorytmy to:  
            - **Rozszerzony algorytm Euklidesa:** znajduje rozwiązania równań liniowych.  
            - **Iteracyjne metody numeryczne:** dla bardziej złożonych przypadków.  

            **Podejście do nauki:**  
            - Zrozum teorię algorytmu Euklidesa i jego rozszerzenia.  
            - Przećwicz algorytmy krok po kroku na przykładach.  
            - Implementuj algorytmy w prostym kodzie (np. w Pythonie), aby zobaczyć ich działanie.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Zadania praktyczne z równań diofantycznych
            Rozwiązywanie zadań praktycznych, takich jak:  
            - Rozdzielenie zasobów przy użyciu równań liniowych.  
            - Optymalizacja przy ograniczeniach całkowitoliczbowych.  

            **Podejście do nauki:**  
            - Rozwiązuj rzeczywiste problemy matematyczne przy pomocy równań diofantycznych.  
            - Analizuj dane zadanie, zapisuj je w formie matematycznego równania i rozwiązuj.
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Historia i znaczenie równań diofantycznych
            Równania diofantyczne mają bogatą historię:  
            - **Diofantos z Aleksandrii** jako twórca teorii.  
            - Zastosowanie równań diofantycznych w teorii liczb i kryptografii.  

            **Podejście do nauki:**  
            - Zapoznaj się z historycznymi przykładami i ich znaczeniem.  
            - Rozważ, jak równania diofantyczne wpłynęły na rozwój współczesnej matematyki.
            """;

    public static final String SUB_REQUIREMENT_9 = """
            ### Zaawansowane przykłady analityczne
            Analiza trudniejszych przypadków, takich jak:  
            - Rozwiązywanie równań wielomianowych wyższych stopni.  
            - Rozwiązywanie systemów wielu równań diofantycznych.  

            **Podejście do nauki:**  
            - Pracuj z trudniejszymi przykładami, które wymagają kombinacji metod algebraicznych i numerycznych.  
            - Staraj się rozwiązywać zadania krok po kroku, dzieląc je na mniejsze części.
            """;

    public static final String SUB_REQUIREMENT_10 = """
            ### Zastosowania równań diofantycznych w różnych dziedzinach nauki
            Równania diofantyczne mają szerokie zastosowania:  
            - **Kryptografia:** np. w szyfrowaniu RSA.  
            - **Informatyka:** optymalizacja i algorytmy numeryczne.  
            - **Ekonomia:** modelowanie problemów podziału zasobów.  

            **Podejście do nauki:**  
            - Rozpoznawaj, gdzie w praktyce pojawiają się równania diofantyczne.  
            - Rozwiązuj praktyczne problemy, które mają rzeczywiste zastosowanie.
            """;

}

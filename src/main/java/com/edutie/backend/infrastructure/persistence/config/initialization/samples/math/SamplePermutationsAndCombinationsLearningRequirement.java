package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SamplePermutationsAndCombinationsLearningRequirement {
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
                "Definicja permutacji i kombinacji",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Permutacje – szczegółowe omówienie i wzory",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Kombinacje – szczegółowe omówienie i wzory",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Permutacje z powtórzeniami i bez powtórzeń",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Kombinacje z powtórzeniami i bez powtórzeń",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie permutacji i kombinacji w zadaniach praktycznych",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Porównanie permutacji i kombinacji – jak rozróżnić?",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Zaawansowane problemy z permutacjami i kombinacjami",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirement.appendSubRequirement(
                "Permutacje cykliczne",
                PromptFragment.of(SUB_REQUIREMENT_9)
        );
        learningRequirement.appendSubRequirement(
                "Rozwiązywanie zadań mieszanych – permutacje, kombinacje i ich złożenia",
                PromptFragment.of(SUB_REQUIREMENT_10)
        );

        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SamplePermutationsAndCombinationsLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Permutacje i Kombinacje";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja permutacji i kombinacji
            Permutacje i kombinacje są kluczowymi pojęciami w kombinatoryce:  
            - **Permutacja** to uporządkowany zbiór elementów.  
            - **Kombinacja** to zbiór elementów bez uwzględnienia kolejności.  
            
            **Podejście do nauki:**  
            - Zrozum różnicę między permutacjami a kombinacjami.  
            - Przećwicz podstawowe przykłady, aby utrwalić rozróżnienie między nimi.  
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Permutacje – szczegółowe omówienie i wzory
            Permutacje dotyczą sposobów uporządkowania elementów zbioru:  
            - Liczba permutacji zbioru o \\( n \\) elementach wynosi:  
            \\[
            P(n) = n!
            \\]  
            - Permutacje częściowe (bez powtórzeń):  
            \\[
            P(n, k) = \\frac{n!}{(n-k)!}
            \\]
            
            **Podejście do nauki:**  
            - Naucz się wzoru na permutacje pełne i częściowe.  
            - Rozwiąż proste zadania z układaniem elementów w kolejności.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Kombinacje – szczegółowe omówienie i wzory
            Kombinacje dotyczą wyboru elementów **bez uwzględnienia kolejności**:  
            - Liczba kombinacji k-elementowych ze zbioru n-elementowego:  
            \\[
            C(n, k) = \\binom{n}{k} = \\frac{n!}{k!(n-k)!}
            \\]  

            **Podejście do nauki:**  
            - Zrozum znaczenie kombinacji jako wyboru podzbiorów.  
            - Przećwicz liczenie kombinacji dla małych zbiorów.  
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Permutacje z powtórzeniami i bez powtórzeń
            - **Bez powtórzeń:** Elementy nie mogą się powtarzać.  
            - **Z powtórzeniami:** Można powtarzać elementy. Wzór dla \\( n \\) elementów z powtórzeniami:  
            \\[
            P_{powt}(n, k) = n^k
            \\]  
            **Podejście do nauki:**  
            - Rozróżnij przypadki z powtórzeniami i bez.  
            - Rozwiąż zadania z różnymi wariantami permutacji.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Kombinacje z powtórzeniami i bez powtórzeń
            - **Bez powtórzeń:** Liczba kombinacji:  
            \\[
            C(n, k) = \\binom{n}{k}
            \\]
            - **Z powtórzeniami:**  
            \\[
            C_{powt}(n, k) = \\binom{n+k-1}{k}
            \\]  

            **Podejście do nauki:**  
            - Naucz się rozpoznawać sytuacje z powtórzeniami.  
            - Przećwicz obliczenia dla kombinacji z powtórzeniami.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Zastosowanie permutacji i kombinacji w zadaniach praktycznych
            Użycie permutacji i kombinacji w:  
            - Układaniu szyków,  
            - Liczeniu podzbiorów,  
            - Problemach z urnami.  

            **Podejście do nauki:**  
            - Rozwiązuj praktyczne zadania z życia codziennego.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Porównanie permutacji i kombinacji – jak rozróżnić?
            - Permutacja: **kolejność ma znaczenie**.  
            - Kombinacja: **kolejność nie ma znaczenia**.  

            **Podejście do nauki:**  
            - Użyj diagramów i przykładów, aby zapamiętać różnice.  
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Zaawansowane problemy z permutacjami i kombinacjami
            Przykłady obejmujące liczenie wariantów z ograniczeniami:  
            - Warunki typu „co najmniej”, „co najwyżej”.  
            - Problemy z elementami o różnym priorytecie.  
            """;

    public static final String SUB_REQUIREMENT_9 = """
            ### Permutacje cykliczne
            Permutacje cykliczne uwzględniają układy, w których elementy są uporządkowane w cyklach. Liczba cykli dla \\( n \\):  
            \\[
            C_{cykl}(n) = (n-1)!
            \\]
            """;

    public static final String SUB_REQUIREMENT_10 = """
            ### Rozwiązywanie zadań mieszanych – permutacje, kombinacje i ich złożenia
            Zadania, które wymagają łączenia permutacji, kombinacji oraz dodatkowych warunków.  
            **Podejście do nauki:**  
            - Pracuj nad złożonymi problemami, które łączą różne techniki kombinatoryczne.  
            """;
}

package com.edutie.backend.infrastructure.persistence.config.initialization.samples.finance;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SamplePoliticalCyclesLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Wprowadzenie do cykli politycznych i ich wpływ na rynek kapitałowy",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Definicja cykli politycznych",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Rodzaje cykli politycznych",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Cykle prezydenckie a gospodarka",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Polityka fiskalna a rynki finansowe",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Reakcja rynków kapitałowych na zmiany polityczne",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Wpływ cykli wyborczych na rynek akcji",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Prognozowanie cykli politycznych",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SamplePoliticalCyclesLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Cykle polityczne a rynek kapitałowy";

    public static final String SUB_REQUIREMENT_1 = """
            ### Wprowadzenie do cykli politycznych i ich wpływ na rynek kapitałowy
            Cykl polityczny odnosi się do okresów, w których następują zmiany w strukturze politycznej kraju, w tym wybory, zmiany rządów i polityki publicznej.
            Zmiany te mają istotny wpływ na rynki kapitałowe, ponieważ polityka może wpłynąć na decyzje inwestycyjne, zmieniając preferencje inwestorów i kierunki gospodarcze.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Definicja cykli politycznych
            Cykle polityczne to okresowe zmiany w strukturze władzy politycznej, które mogą obejmować wybory, zmiany administracyjne oraz zmiany polityki gospodarczej.
            Zmiany te mogą mieć istotny wpływ na sytuację gospodarczą i na zachowania rynków kapitałowych.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Rodzaje cykli politycznych
            Cykle polityczne obejmują różne rodzaje zmian politycznych, takie jak:
            - **Cykl prezydencki** – Okres między wyborami prezydenckimi, który może wpływać na kształt polityki gospodarczej.
            - **Cykl parlamentarny** – Okres między wyborami parlamentarnymi, w którym mogą być wprowadzane zmiany legislacyjne mające wpływ na rynek.
            - **Zmiany administracyjne** – Zmiany w administracji rządowej, które mogą wpłynąć na politykę fiskalną i monetarną.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Cykle prezydenckie a gospodarka
            Cykle prezydenckie obejmują okresy między wyborami prezydenckimi, które mogą wpływać na politykę gospodarczą danego kraju. 
            Zmiana administracji prezydenckiej może wiązać się z nowymi kierunkami polityki fiskalnej, monetarnej oraz innymi decyzjami mającymi wpływ na rynek kapitałowy.
            Zmiana prezydenta może prowadzić do wzrostu lub spadku wartości akcji, w zależności od tego, jakie zmiany gospodarcze są zapowiadane.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Polityka fiskalna a rynki finansowe
            Polityka fiskalna, w tym decyzje o wydatkach publicznych i opodatkowaniu, ma kluczowy wpływ na gospodarkę i rynek kapitałowy. 
            Zmiany w polityce fiskalnej mogą wpłynąć na wzrost lub spadek aktywności gospodarczej, a tym samym na ceny akcji i inne instrumenty finansowe.
            Na przykład, obniżenie podatków może stymulować wzrost gospodarczy i poprawić wyniki finansowe firm, co prowadzi do wzrostu cen akcji.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Reakcja rynków kapitałowych na zmiany polityczne
            Rynki kapitałowe są bardzo wrażliwe na zmiany polityczne, zwłaszcza w okresach niepewności politycznej, jak zmiany rządów czy kryzysy polityczne. 
            Decyzje polityczne, takie jak zmiany w polityce monetarnej, wprowadzenie nowych regulacji czy zmiany w prawie pracy, mogą wywołać reakcje na rynkach finansowych.
            Przykładem może być gwałtowny wzrost cen akcji w odpowiedzi na zapowiedzi polityki wspierającej wzrost gospodarczy.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Wpływ cykli wyborczych na rynek akcji
            Cykl wyborczy, szczególnie w kontekście wyborów prezydenckich, może mieć wpływ na ceny akcji. 
            Inwestorzy mogą przewidywać, jakie decyzje gospodarcze podejmie nowo wybrany prezydent, co może wpływać na inwestycje i wycenę akcji.
            Rynki mogą reagować optymistycznie na zmiany w administracji, zwłaszcza jeśli nowe władze zapowiadają reformy wspierające rozwój gospodarczy.
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Prognozowanie cykli politycznych
            Prognozowanie cykli politycznych jest trudne, ale możliwe. Analitycy starają się przewidywać, jak zmiany polityczne wpłyną na sytuację gospodarczą i rynki finansowe. 
            Zrozumienie historii politycznej, programów wyborczych i decyzji politycznych może pomóc inwestorom w ocenie ryzyka związanego z inwestowaniem w określonych okresach politycznych.
            Prognozowanie cykli politycznych może również pomóc w określaniu, kiedy rynek może być szczególnie wrażliwy na zmiany polityczne.
            """;
}

package com.edutie.backend.infrastructure.persistence.config.initialization.samples.finance;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleFinancialInstrumentsLearningRequirement {
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
                "Wprowadzenie do instrumentów finansowych",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Rodzaje instrumentów finansowych",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Instrumenty kapitałowe: akcje",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Instrumenty dłużne: obligacje",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Instrumenty pochodne: opcje i kontrakty futures",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Rynki instrumentów finansowych",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Ocena ryzyka instrumentów finansowych",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie instrumentów finansowych w inwestycjach",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleFinancialInstrumentsLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Instrumenty finansowe na rynku kapitałowym";

    public static final String SUB_REQUIREMENT_1 = """
            ### Wprowadzenie do instrumentów finansowych
            Instrumenty finansowe to kontrakty, które mogą być przedmiotem obrotu na rynku kapitałowym. Są one wykorzystywane w celu pozyskiwania kapitału, zarządzania ryzykiem oraz inwestowania. Mogą być stosowane zarówno przez przedsiębiorstwa, jak i inwestorów indywidualnych.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Rodzaje instrumentów finansowych
            Instrumenty finansowe dzielą się na różne kategorie, w zależności od funkcji, jaką pełnią. Możemy wyróżnić:
            - **Instrumenty kapitałowe** – np. akcje, które dają prawo do uczestniczenia w zyskach firmy.
            - **Instrumenty dłużne** – np. obligacje, które dają prawo do uzyskania odsetek i zwrotu kapitału.
            - **Instrumenty pochodne** – np. opcje, kontrakty futures, które służą do zabezpieczenia lub spekulacji.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Instrumenty kapitałowe: akcje
            Akcje są podstawowym instrumentem kapitałowym, który daje posiadaczowi prawo do udziału w zyskach i majątku firmy. Mogą być wykorzystywane do pozyskania kapitału przez przedsiębiorstwa, a inwestorzy mogą na nich zarabiać na wzrostach cen lub dywidendach.
            Akcje dzielą się na:
            - **Akcje zwykłe** – dają prawo głosu na walnym zgromadzeniu akcjonariuszy i udział w zyskach.
            - **Akcje uprzywilejowane** – dają prawo do wyższej dywidendy lub preferencyjnego podziału majątku firmy w przypadku jej likwidacji.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Instrumenty dłużne: obligacje
            Obligacje to instrumenty finansowe, które stanowią zobowiązanie emitenta do zwrotu pożyczonego kapitału w określonym terminie oraz wypłaty odsetek. Obligacje mogą być emitowane przez rządy, przedsiębiorstwa oraz inne podmioty.
            W zależności od rodzaju obligacji mogą one mieć różne ryzyko, zyski i terminy wykupu.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Instrumenty pochodne: opcje i kontrakty futures
            Instrumenty pochodne, takie jak opcje i kontrakty futures, to kontrakty, których wartość zależy od ceny innych aktywów, takich jak akcje, obligacje czy surowce. 
            - **Opcje** dają prawo, ale nie obowiązek, kupna lub sprzedaży instrumentu bazowego po określonej cenie.
            - **Kontrakty futures** to umowy zobowiązujące do kupna lub sprzedaży instrumentu bazowego po ustalonej cenie w przyszłości.
            Są one wykorzystywane w celu zabezpieczania się przed ryzykiem lub spekulacji na zmiany cen aktywów.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Rynki instrumentów finansowych
            Instrumenty finansowe są przedmiotem obrotu na różnych rynkach finansowych. Wyróżniamy:
            - **Rynek akcji** – miejsce obrotu akcjami, na którym inwestorzy kupują i sprzedają udziały w firmach.
            - **Rynek obligacji** – rynek, na którym emitowane są obligacje, a inwestorzy mogą je kupować i sprzedawać.
            - **Rynek instrumentów pochodnych** – rynek, na którym zawierane są kontrakty na instrumenty pochodne, takie jak opcje i futures.
            Rynek może być fizyczny (giełda) lub elektroniczny (platformy obrotu).
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Ocena ryzyka instrumentów finansowych
            Każdy instrument finansowy wiąże się z określonym poziomem ryzyka. Inwestorzy muszą rozumieć, jak ocenić ryzyko związane z danym instrumentem. Do podstawowych rodzajów ryzyka należą:
            - **Ryzyko kredytowe** – ryzyko niewypłacalności emitenta obligacji.
            - **Ryzyko rynkowe** – ryzyko wynikające ze zmiany cen instrumentu finansowego.
            - **Ryzyko płynności** – ryzyko związane z trudnościami w sprzedaży instrumentu.
            Ocena ryzyka pozwala inwestorom na podejmowanie świadomych decyzji inwestycyjnych.
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Zastosowanie instrumentów finansowych w inwestycjach
            Instrumenty finansowe odgrywają kluczową rolę w strategiach inwestycyjnych. Mogą być wykorzystywane do:
            - **Inwestycji długoterminowych** – np. akcje i obligacje, które generują zyski w długim okresie.
            - **Zabezpieczenia przed ryzykiem** – np. opcje i futures, które mogą chronić przed niekorzystnymi zmianami cen.
            - **Spekulacji** – np. kontrakty futures, które pozwalają na zarabianie na zmianach cen bez posiadania instrumentu bazowego.
            Zrozumienie funkcji instrumentów finansowych pozwala na skuteczne zarządzanie portfelem inwestycyjnym.
            """;
}

package com.edutie.backend.infrastructure.persistence.config.initialization.samples.finance;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleCreditCycleImpactOnStockMarketLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Wprowadzenie do cyklu kredytowego",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Fazy cyklu kredytowego: ekspansja i kontrakcja",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Wpływ cyklu kredytowego na rynek akcji",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Związek pomiędzy dostępnością kredytów a wycenami akcji",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Zmiany stóp procentowych a rynek akcji",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Korelacja cyklu kredytowego z cyklem gospodarczym",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleCreditCycleImpactOnStockMarketLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Cykl kredytowy i jego wpływ na rynek akcji";

    public static final String SUB_REQUIREMENT_1 = """
            ### Wprowadzenie do cyklu kredytowego
            Cykl kredytowy odnosi się do okresowych zmian w dostępności i kosztach kredytu w gospodarce. Jest ściśle powiązany z polityką monetarną banków centralnych, która reguluje podaż pieniądza i stopy procentowe. Cykl kredytowy ma kluczowe znaczenie dla gospodarki i rynków finansowych, ponieważ wpływa na decyzje inwestycyjne, konsumpcję i poziom zadłużenia.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Fazy cyklu kredytowego: ekspansja i kontrakcja
            Cykl kredytowy składa się z dwóch głównych faz: ekspansji i kontrakcji.
            - **Ekspansja kredytowa**: W tej fazie banki zwiększają dostępność kredytów, a koszty finansowania spadają, co sprzyja wzrostowi inwestycji, konsumpcji i rozwoju gospodarczego.
            - **Kontrakcja kredytowa**: Z kolei w tej fazie dostępność kredytów maleje, stopy procentowe rosną, a banki stają się bardziej ostrożne w udzielaniu pożyczek, co prowadzi do spowolnienia gospodarki.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Wpływ cyklu kredytowego na rynek akcji
            Cykl kredytowy ma bezpośredni wpływ na rynki akcji. W fazie ekspansji kredytowej, kiedy kredyty są tanie i łatwo dostępne, przedsiębiorstwa mogą finansować swoje inwestycje, a konsumenci chętniej wydają pieniądze. W efekcie wzrasta popyt na produkty i usługi, co prowadzi do wyższych zysków firm i wzrostu wycen akcji. Z kolei w fazie kontrakcji kredytowej, gdy kredyty stają się droższe i trudniej dostępne, firmy ograniczają inwestycje, a konsumpcja spada, co może prowadzić do spadku zysków i obniżenia wycen akcji.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Związek pomiędzy dostępnością kredytów a wycenami akcji
            Wysoka dostępność kredytów wpływa na wyższe wyceny akcji, ponieważ firmy mogą łatwiej pozyskiwać kapitał na rozwój i inwestycje, a inwestorzy są skłonni płacić więcej za akcje tych firm. W okresach łatwego dostępu do kredytów, inwestorzy mogą skłaniać się do bardziej ryzykownych inwestycji, co podnosi wyceny akcji na rynku. Przeciwne zjawisko ma miejsce w okresach, gdy dostępność kredytów jest ograniczona.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Zmiany stóp procentowych a rynek akcji
            Zmiany stóp procentowych mają kluczowy wpływ na rynek akcji. Wzrost stóp procentowych podnosi koszty kredytów, co może prowadzić do spadku inwestycji firm oraz obniżenia zysków. W wyniku tego spadają wyceny akcji, szczególnie w sektorach wrażliwych na koszt kapitału, takich jak nieruchomości czy technologie. Z kolei obniżenie stóp procentowych ma odwrotny efekt, sprzyjając wzrostowi gospodarki i wycen akcji.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Korelacja cyklu kredytowego z cyklem gospodarczym
            Cykl kredytowy jest silnie powiązany z cyklem gospodarczym. W fazie ekspansji gospodarczej wzrasta popyt na kredyty, co z kolei przyczynia się do szybszego wzrostu gospodarczego. W fazie recesji, kiedy następuje spadek dostępności kredytów, gospodarka zwalnia, a spadające inwestycje i konsumpcja wpływają na wyceny akcji. Cykl kredytowy jest więc jednym z kluczowych czynników, które mogą w znaczący sposób kształtować wyniki rynków akcji w różnych fazach cyklu gospodarczego.
            """;
}

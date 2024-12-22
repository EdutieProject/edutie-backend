package com.edutie.backend.infrastructure.persistence.config.initialization.samples.finance;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleSentimentalCyclesLearningRequirement {
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
                "Wprowadzenie do cykli sentymentalnych na rynku",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Czym są cykle sentymentalne?",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Rola sentymentu na rynku kapitałowym",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Cykle sentymentalne a zmiany cen na rynku",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Fazy cyklu sentymentalnego",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Wykorzystanie cykli sentymentalnych w analizie technicznej",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Zjawisko overconfidence i jego wpływ na rynek",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Przewidywanie cykli sentymentalnych na rynku",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleSentimentalCyclesLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Cykle sentymentalne na rynku kapitałowym";

    public static final String SUB_REQUIREMENT_1 = """
            ### Wprowadzenie do cykli sentymentalnych na rynku
            Cykle sentymentalne na rynku kapitałowym odnoszą się do zmian nastrojów inwestorów, które wpływają na decyzje inwestycyjne i mogą powodować zmiany cen aktywów. 
            Nastroje te mogą być pozytywne lub negatywne, a cykliczne zmiany w sentymencie mogą prowadzić do tworzenia bańki spekulacyjnej lub bessy rynkowej.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Czym są cykle sentymentalne?
            Cykle sentymentalne to okresy zmieniających się emocji i nastrojów wśród inwestorów. 
            Często manifestują się one jako naprzemienne fazy optymizmu i pesymizmu, które mogą prowadzić do nadmiernych wzrostów (bulle) lub spadków (bessa) cen aktywów.
            Cykl ten może być wynikiem zarówno czynników zewnętrznych, jak i wewnętrznych wpływających na decyzje inwestorów.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Rola sentymentu na rynku kapitałowym
            Sentiment jest jednym z kluczowych czynników, które mogą prowadzić do wahań na rynku kapitałowym. 
            Nastroje inwestorów, takie jak optymizm, strach czy euforia, mogą wpłynąć na wycenę aktywów, a także na decyzje dotyczące zakupu lub sprzedaży akcji.
            Cykliczne zmiany w sentymencie mogą powodować, że ceny akcji odbiegają od ich rzeczywistej wartości, co prowadzi do bańki lub krachu.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Cykle sentymentalne a zmiany cen na rynku
            Cykle sentymentalne mają bezpośredni wpływ na zmiany cen na rynku kapitałowym. 
            Podczas okresów euforii rynki mogą rosnąć, nawet jeśli fundamenty spółek nie uzasadniają takich wzrostów. 
            Z kolei w okresach pesymizmu inwestorzy mogą nadmiernie wyprzedawać aktywa, co prowadzi do spadków cen.
            Zrozumienie tych cykli jest kluczowe dla prognozowania trendów na rynku.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Fazy cyklu sentymentalnego
            Cykl sentymentalny składa się z kilku faz:
            - **Faza optymizmu** – Inwestorzy czują się pewnie, co prowadzi do wzrostów cen.
            - **Faza euforii** – Ceny rosną w szybkim tempie, ale nie mają uzasadnienia fundamentalnego.
            - **Faza pesymizmu** – Inwestorzy zaczynają obawiać się o przyszłość, a ceny spadają.
            - **Faza paniki** – Strach prowadzi do masowej sprzedaży, co powoduje gwałtowne spadki cen.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Wykorzystanie cykli sentymentalnych w analizie technicznej
            Analiza techniczna wykorzystuje cykle sentymentalne do identyfikacji trendów rynkowych. 
            Wykresy cenowe, wskaźniki techniczne oraz formacje wykresów mogą pomóc w określeniu, w jakiej fazie cyklu sentymentalnego znajduje się rynek.
            Inwestorzy techniczni próbują wykorzystywać te sygnały, aby przewidywać przyszłe ruchy cenowe.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Zjawisko overconfidence i jego wpływ na rynek
            Zjawisko overconfidence (nadmierna pewność siebie) polega na tym, że inwestorzy mają tendencję do przeceniania swoich umiejętności analitycznych 
            i wiedzy, co może prowadzić do błędnych decyzji inwestycyjnych. Nadmierna pewność siebie w okresach optymizmu może prowadzić do napompowania 
            bańki spekulacyjnej, a w okresach pesymizmu – do przesadnego wyprzedawania aktywów.
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Przewidywanie cykli sentymentalnych na rynku
            Przewidywanie cykli sentymentalnych jest trudne, ale możliwe. Analiza danych rynkowych, wskaźników sentymentu oraz opinii ekspertów 
            może pomóc w zidentyfikowaniu nadchodzących zmian w sentymencie inwestorów. 
            Jednak ze względu na zmienność emocji na rynku, prognozy te zawsze niosą ze sobą pewien stopień niepewności.
            """;
}

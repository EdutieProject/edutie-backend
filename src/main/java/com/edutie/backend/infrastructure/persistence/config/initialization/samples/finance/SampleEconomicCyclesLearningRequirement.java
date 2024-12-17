package com.edutie.backend.infrastructure.persistence.config.initialization.samples.finance;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleEconomicCyclesLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        if (learningRequirementPersistence.getRepository().findAll().stream().anyMatch(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME))) {
            isSeeded = true;
            return;
        }
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Wprowadzenie do cykli ekonomicznych na rynku kapitałowym",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Definicja cykli ekonomicznych",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Fazy cyklu gospodarczego",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Cykle ekonomiczne a rynek kapitałowy",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Wpływ cykli koniunkturalnych na wycenę akcji",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Cykl wzrostu gospodarczego i jego wpływ na inwestycje",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Recesja i jej wpływ na rynek kapitałowy",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Prognozowanie cykli ekonomicznych",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleEconomicCyclesLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Cykl ekonomiczny na rynku kapitałowym";

    public static final String SUB_REQUIREMENT_1 = """
            ### Wprowadzenie do cykli ekonomicznych na rynku kapitałowym
            Cykle ekonomiczne odnoszą się do okresowych wahań aktywności gospodarczej, które wpływają na popyt, produkcję i zatrudnienie w gospodarce. 
            Zmiany te mają także ogromny wpływ na rynki kapitałowe, ponieważ zmieniają wycenę aktywów oraz preferencje inwestorów.
            Cykle ekonomiczne na rynku kapitałowym mogą przybierać różną formę, od okresów wzrostu po recesję.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Definicja cykli ekonomicznych
            Cykl ekonomiczny to okresowe wahania w poziomie aktywności gospodarczej, które wpływają na różne sektory rynku. 
            Cykl składa się z kilku faz, takich jak ekspansja, szczyt, recesja i dno. 
            Zrozumienie cykli ekonomicznych jest kluczowe dla inwestorów, ponieważ wpływają one na strategię inwestycyjną oraz wycenę aktywów.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Fazy cyklu gospodarczego
            Cykl gospodarczy składa się z czterech głównych faz:
            - **Ekspansja** – Gospodarka rośnie, produkcja wzrasta, bezrobocie spada, a inwestycje rosną.
            - **Szczyt** – Gospodarka osiąga maksymalny poziom aktywności, a wzrost zaczyna zwalniać.
            - **Recesja** – Produkcja spada, bezrobocie rośnie, a inwestycje maleją.
            - **Dno** – Gospodarka osiąga najniższy punkt cyklu, co może prowadzić do ożywienia.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Cykle ekonomiczne a rynek kapitałowy
            Cykl ekonomiczny ma bezpośredni wpływ na rynki kapitałowe, ponieważ zmiany w gospodarce wpływają na dochody przedsiębiorstw, 
            a tym samym na wycenę ich akcji. Wzrost gospodarczy prowadzi do wzrostu zysków przedsiębiorstw, co może podnieść ceny akcji, 
            natomiast recesja często prowadzi do spadku zysków i spadku cen akcji.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Wpływ cykli koniunkturalnych na wycenę akcji
            Cykle koniunkturalne mają bezpośredni wpływ na wycenę akcji na rynku kapitałowym. 
            Podczas okresów wzrostu gospodarczego, zyski przedsiębiorstw rosną, co prowadzi do wyższych cen akcji. 
            Z kolei w czasie recesji, gdy dochody firm spadają, ceny akcji mogą ulegać obniżeniu.
            Inwestorzy wykorzystują cykle koniunkturalne do podejmowania decyzji inwestycyjnych.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Cykl wzrostu gospodarczego i jego wpływ na inwestycje
            Cykl wzrostu gospodarczego charakteryzuje się zwiększoną produkcją, wzrostem zatrudnienia i wzrostem inwestycji. 
            W tym okresie rynki kapitałowe rosną, a inwestorzy preferują ryzykowne aktywa, takie jak akcje. 
            Wzrost gospodarczy sprzyja innowacjom i ekspansji firm, co podnosi ich wycenę.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Recesja i jej wpływ na rynek kapitałowy
            Recesja to okres spadku aktywności gospodarczej, który prowadzi do wyższych poziomów bezrobocia, spadku produkcji i spadku zysków firm.
            Podczas recesji ceny akcji zwykle spadają, a inwestorzy zmieniają swoje podejście do ryzyka, szukając bezpieczniejszych aktywów, takich jak obligacje.
            Recesja często prowadzi do spadku popytu na konsumpcję i inwestycje, co wpływa na wyniki firm i ich wycenę na rynku kapitałowym.
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Prognozowanie cykli ekonomicznych
            Prognozowanie cykli ekonomicznych jest trudne, ale możliwe. Analitycy stosują różne wskaźniki makroekonomiczne, takie jak PKB, stopa bezrobocia czy indeksy cen, 
            aby przewidywać zmiany w cyklach gospodarczych. 
            Wykorzystanie danych z historii gospodarczej i bieżącej sytuacji makroekonomicznej może pomóc inwestorom w podejmowaniu decyzji inwestycyjnych w zależności od fazy cyklu gospodarczego.
            """;
}

package com.edutie.backend.infrastructure.persistence.config.initialization.samples.finance;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleSectorCorrelationsLearningRequirement {
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
                "Wprowadzenie do korelacji sektorowych",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Zrozumienie zależności między sektorami gospodarki",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Korelacja sektorów: cykliczne vs defensywne",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Przykłady pozytywnej korelacji sektorów",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Przykłady negatywnej korelacji sektorów",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Korelacje sektorowe w cyklu gospodarczym",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Strategie inwestycyjne bazujące na korelacjach sektorowych",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Analiza zmienności sektorów i jej wpływ na portfel",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleSectorCorrelationsLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Korelacje sektorowe na rynku akcji";

    public static final String SUB_REQUIREMENT_1 = """
            ### Wprowadzenie do korelacji sektorowych
            Korelacje sektorowe odnoszą się do zależności pomiędzy różnymi sektorami gospodarki i ich wpływem na ceny akcji w obrębie tych sektorów. Rozumienie korelacji sektorów jest kluczowe dla inwestorów, którzy chcą dywersyfikować swój portfel, zrozumieć zmienność rynku oraz dostosować swoje strategie inwestycyjne do warunków rynkowych.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Zrozumienie zależności między sektorami gospodarki
            Sektory gospodarki są ze sobą powiązane w różny sposób. W zależności od kondycji gospodarczej, sektory mogą wykazywać pozytywną lub negatywną korelację. Zrozumienie tych zależności jest ważne przy budowaniu portfela inwestycyjnego, ponieważ pozwala na lepsze zarządzanie ryzykiem.
            - **Sektory cykliczne** – reagują na zmiany w cyklu gospodarczym (np. przemysł, technologie).
            - **Sektory defensywne** – mniej wrażliwe na cykliczne zmiany gospodarcze (np. zdrowie, użyteczność publiczna).
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Korelacja sektorów: cykliczne vs defensywne
            Korelacje między sektorami cyklicznymi a defensywnymi są kluczowe, zwłaszcza w okresach zmienności rynkowej. Sektory cykliczne, takie jak przemysł, technologie czy finansowe, mają tendencję do rosnąć w okresach ekspansji gospodarczej, ale również mogą mocno tracić podczas spowolnienia. Sektory defensywne, takie jak zdrowie, sektor użyteczności publicznej, żywność i napoje, zwykle są mniej wrażliwe na zmiany w gospodarce.
            - **Pozytywna korelacja** – sektor cykliczny wzrasta, a sektor defensywny spada w odpowiedzi na zmiany w cyklu gospodarczym.
            - **Negatywna korelacja** – sektory defensywne mogą wzrastać, gdy sektory cykliczne spadają, np. w czasie recesji.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Przykłady pozytywnej korelacji sektorów
            Istnieje wiele przypadków, w których sektory mogą wykazywać pozytywną korelację, czyli zmieniają się w tym samym kierunku. Na przykład, sektor technologiczny może być pozytywnie skorelowany z sektorem finansowym w okresach wzrostu gospodarczego, ponieważ rozwój technologii może wspierać innowacje finansowe, co z kolei pozytywnie wpływa na ceny akcji w obu sektorach.
            - **Sektor finansowy i nieruchomości** – w okresach wzrostu gospodarczego, banki i deweloperzy mogą osiągać zyski, ponieważ większa dostępność kredytów stymuluje inwestycje w nieruchomości.
            - **Sektor energetyczny i przemysł** – w okresach wzrostu produkcji przemysłowej popyt na energię wzrasta, co może pozytywnie wpłynąć na oba sektory.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Przykłady negatywnej korelacji sektorów
            Negatywna korelacja występuje wtedy, gdy jeden sektor rośnie, a drugi spada. Na przykład, w czasie spowolnienia gospodarczego, sektory cykliczne mogą tracić na wartości, podczas gdy sektory defensywne, takie jak zdrowie czy użyteczność publiczna, mogą zyskiwać na wartości, ponieważ ich produkty i usługi są bardziej stabilne.
            - **Sektor technologiczny i sektor użyteczności publicznej** – technologie mogą stracić na wartości podczas spowolnienia gospodarczego, podczas gdy firmy z sektora użyteczności publicznej mogą zyskiwać, ponieważ popyt na energię i wodę jest mniej wrażliwy na cykle koniunkturalne.
            - **Sektor przemysłowy i zdrowie** – w czasach recesji sektor przemysłowy może zanotować spadki, podczas gdy sektor zdrowia może pozostać stabilny lub wzrosnąć.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Korelacje sektorowe w cyklu gospodarczym
            Cykl gospodarczy ma bezpośredni wpływ na korelacje sektorów. Wzrost gospodarczy prowadzi do rozwoju sektorów cyklicznych, natomiast spowolnienie lub recesja zazwyczaj powodują wzrost popularności sektorów defensywnych. Rozumienie tych korelacji pozwala inwestorom lepiej przygotować się na zmiany w gospodarce i dostosować portfel inwestycyjny.
            - **Faza ekspansji** – sektory cykliczne rosną, a defensywne mogą rosnąć w mniejszym stopniu.
            - **Faza recesji** – sektory defensywne mogą rosnąć, a cykliczne mogą tracić na wartości.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Strategie inwestycyjne bazujące na korelacjach sektorowych
            Inwestorzy mogą wykorzystać korelacje sektorowe do budowy bardziej zdywersyfikowanych portfeli inwestycyjnych. Strategie inwestycyjne bazujące na korelacjach obejmują m.in.:
            - **Strategia rotacji sektorowej** – inwestowanie w sektory, które są pozytywnie skorelowane w danym etapie cyklu gospodarczego.
            - **Hedging** – wykorzystanie negatywnych korelacji do zabezpieczenia portfela przed ryzykiem związanym z recesją lub innymi zagrożeniami rynkowymi.
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Analiza zmienności sektorów i jej wpływ na portfel
            Zmienność sektorów ma kluczowe znaczenie dla inwestorów. Sektory o wysokiej zmienności mogą zwiększać ryzyko portfela, podczas gdy sektory bardziej stabilne mogą przyczynić się do zmniejszenia ryzyka. Analiza zmienności pozwala na optymalizację alokacji aktywów w portfelu, w celu zminimalizowania ryzyka i zwiększenia potencjalnych zysków.
            - **Sektory o wysokiej zmienności** – np. technologie, które mogą reagować na zmiany rynku w sposób bardziej gwałtowny.
            - **Sektory o niskiej zmienności** – np. zdrowie, użyteczność publiczna, które zwykle cechują się stabilnością.
            """;
}

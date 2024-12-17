package com.edutie.backend.infrastructure.persistence.config.initialization.samples.finance;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleMarketEfficiencyLearningRequirement {
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
                "Wprowadzenie do efektywności rynku",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Hipoteza efektywności rynku",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Rodzaje efektywności rynku",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Teoria rynków efektywnych",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Efektywność rynku a wycena akcji",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Rola informacji na rynku",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Wycena akcji w warunkach efektywności rynku",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Horyzont wyceny i jego wpływ na decyzje inwestycyjne",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleMarketEfficiencyLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Efektywność rynku i horyzont wyceny";

    public static final String SUB_REQUIREMENT_1 = """
            ### Wprowadzenie do efektywności rynku
            Efektywność rynku odnosi się do stopnia, w jakim ceny akcji odzwierciedlają dostępne informacje. 
            Na rynku efektywnym ceny akcji powinny natychmiast reagować na wszelkie dostępne dane, co oznacza, 
            że inwestorzy nie mogą uzyskać nadprzeciętnych zysków, wykorzystując publicznie dostępne informacje.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Hipoteza efektywności rynku
            Hipoteza efektywności rynku (EMH) zakłada, że ceny akcji w pełni odzwierciedlają wszystkie dostępne informacje. 
            Istnieją trzy formy EMH: słaba, półmocna i silna, zależnie od tego, jakie informacje są uwzględniane w cenach akcji.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Rodzaje efektywności rynku
            - **Słaba efektywność** – ceny akcji odzwierciedlają jedynie przeszłe ceny.
            - **Półmocna efektywność** – ceny akcji uwzględniają zarówno przeszłe ceny, jak i publicznie dostępne informacje.
            - **Silna efektywność** – ceny akcji zawierają wszystkie informacje, w tym dane poufne.
            Zrozumienie tych rodzajów efektywności pomaga w określeniu, jakie strategie inwestycyjne są możliwe w danym środowisku.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Teoria rynków efektywnych
            Teoria rynków efektywnych sugeruje, że rynek akcji jest w stanie szybko reagować na zmiany informacji, 
            co sprawia, że inwestorzy nie mogą osiągnąć lepszych wyników niż rynek, stosując dostępne informacje publiczne.
            Inwestorzy w takim rynku nie mogą wykorzystywać przeszłych danych ani nowych informacji do uzyskiwania nadzwyczajnych zysków.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Efektywność rynku a wycena akcji
            Na rynku efektywnym wycena akcji powinna odzwierciedlać wszystkie dostępne informacje, co sprawia, 
            że nie ma potrzeby długoterminowego przewidywania cen. Z tego powodu wycena akcji jest bardziej zbliżona 
            do ceny rynkowej, która zmienia się zgodnie z nowymi informacjami dostępnymi dla inwestorów.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Rola informacji na rynku
            Informacje są kluczowe w procesie ustalania ceny akcji. Na rynku efektywnym wszystkie dostępne informacje 
            – w tym dane finansowe, komunikaty prasowe i inne publiczne informacje – są natychmiast przyswajane przez inwestorów, 
            co wpływa na cenę akcji. Im szybciej rynek reaguje na te informacje, tym bardziej efektywny jest.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Wycena akcji w warunkach efektywności rynku
            W warunkach efektywności rynku wycena akcji odbywa się na podstawie wszystkich dostępnych informacji, 
            co oznacza, że ceny akcji są zawsze adekwatne do ich rzeczywistej wartości. 
            Z tego powodu inwestorzy nie powinni spodziewać się uzyskania ponadprzeciętnych wyników na rynku efektywnym.
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Horyzont wyceny i jego wpływ na decyzje inwestycyjne
            Horyzont wyceny odnosi się do okresu, w którym inwestorzy analizują i prognozują przyszłe przepływy pieniężne 
            lub zyski związane z inwestowaniem w akcje. Długi horyzont wyceny uwzględnia więcej czynników ryzyka i zmienności, 
            podczas gdy krótki horyzont wyceny może być bardziej podatny na zmiany cen związane z chwilowymi informacjami 
            rynkowymi. W zależności od horyzontu, inwestorzy mogą przyjąć różne strategie wyceny akcji.
            """;
}

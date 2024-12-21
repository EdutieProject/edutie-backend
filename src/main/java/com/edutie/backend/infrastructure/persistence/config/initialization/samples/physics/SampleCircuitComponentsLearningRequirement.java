package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleCircuitComponentsLearningRequirement {
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
                "Uczeń zna podstawowe elementy obwodów elektrycznych: rezystor, kondensator, cewka i źródło napięcia",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczać parametry obwodów złożonych z rezystorów, kondensatorów i cewek",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi rozróżnić obwody szeregowe i równoległe oraz zna zasady ich obliczania",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczać całkowity opór, pojemność i indukcyjność w obwodach złożonych",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie działanie poszczególnych komponentów obwodów elektrycznych w kontekście ich zastosowań",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleCircuitComponentsLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Komponenty obwodów elektrycznych";

    public static final String SUB_REQUIREMENT_1 = """
            ### Podstawowe elementy obwodów elektrycznych
            Obwody elektryczne składają się z różnych komponentów, które pełnią różne funkcje. Do najczęściej spotykanych elementów należą:
            
            - **Rezystor**: element ograniczający przepływ prądu elektrycznego, jego opór mierzymy w omach (Ω).
            - **Kondensator**: element magazynujący energię w postaci pola elektrycznego, jego pojemność mierzymy w faradach (F).
            - **Cewka**: element indukcyjny, który przeciwdziała zmianom natężenia prądu, mierzymy ją w henrach (H).
            - **Źródło napięcia**: źródło energii, np. bateria, akumulator, zasilacz.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Obliczania parametrów obwodów złożonych
            W obwodach elektrycznych mogą występować różne kombinacje komponentów, które wymagają stosowania odpowiednich wzorów:
            
            **Rezystory w obwodach szeregowych**:
            \\[
            R_{całkowity} = R_1 + R_2 + R_3 + ... 
            \\]
            
            **Rezystory w obwodach równoległych**:
            \\[
            \\frac{1}{R_{całkowity}} = \\frac{1}{R_1} + \\frac{1}{R_2} + \\frac{1}{R_3} + ...
            \\]
            
            **Kondensatory w obwodach szeregowych**:
            \\[
            \\frac{1}{C_{całkowita}} = \\frac{1}{C_1} + \\frac{1}{C_2} + \\frac{1}{C_3} + ...
            \\]
            
            **Kondensatory w obwodach równoległych**:
            \\[
            C_{całkowita} = C_1 + C_2 + C_3 + ...
            \\]
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Obwody szeregowe i równoległe
            W obwodach elektrycznych wyróżniamy dwie podstawowe konfiguracje:
            
            - **Obwód szeregowy**: komponenty są połączone jeden po drugim. Całkowity opór jest sumą oporów poszczególnych elementów.
            - **Obwód równoległy**: komponenty są połączone równolegle, czyli początki i końce elementów są wspólne. Całkowity opór obwodu jest mniejszy niż najmniejszy opór poszczególnych elementów.
            
            W obwodach szeregowych i równoległych różnią się także obliczenia dla innych komponentów, jak kondensatory czy cewki.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Obliczanie całkowitego oporu, pojemności i indukcyjności
            W obwodach złożonych, w zależności od konfiguracji komponentów, obliczamy parametry całkowite:
            
            **Całkowity opór w obwodach szeregowych i równoległych**: obliczanie całkowitego oporu zależy od tego, czy komponenty są połączone szeregowo czy równolegle.
            
            **Całkowita pojemność w obwodach**: podobnie jak opór, pojemność oblicza się zależnie od układu elementów.
            
            **Całkowita indukcyjność**: indukcyjność również jest zależna od połączenia cewek w obwodzie.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Działanie komponentów obwodów elektrycznych
            Każdy z komponentów obwodów elektrycznych pełni określoną funkcję:
            
            - **Rezystor**: kontroluje przepływ prądu w obwodzie.
            - **Kondensator**: magazynuje energię elektryczną, wykorzystywany w obwodach filtrujących.
            - **Cewka**: przeciwdziała nagłym zmianom prądu, stosowana w układach oscylacyjnych.
            - **Źródło napięcia**: dostarcza energię niezbędną do przepływu prądu przez obwód.
            
            Zrozumienie ich działania jest kluczowe do poprawnego projektowania obwodów elektrycznych.
            """;
}

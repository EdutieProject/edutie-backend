package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleThermalEngineEfficiencyRequirement {
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
                "Uczeń zna definicję wydajności silnika cieplnego i potrafi stosować wzór na wydajność",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie, że maksymalna wydajność silnika cieplnego jest ograniczona przez cykl Carnota",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi analizować wpływ temperatur źródeł ciepła na wydajność silnika",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi interpretować zależność między wydajnością silnika cieplnego a stratami energii",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie praktyczne ograniczenia technologiczne w osiąganiu maksymalnej wydajności",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleThermalEngineEfficiencyRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Wydajność silnika cieplnego";
    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja wydajności silnika cieplnego
            Wydajność silnika cieplnego określa, jaka część pobranego ciepła zostaje przekształcona w pracę:
            
            **Wzór:**
            \\[
            \\eta = \\frac{W}{Q_{we}} = 1 - \\frac{Q_{wy}}{Q_{we}}
            \\]
            gdzie:
            - \\(\\eta\\): wydajność,
            - \\(W\\): praca wykonana przez silnik,
            - \\(Q_{we}\\): ciepło pobrane,
            - \\(Q_{wy}\\): ciepło oddane.
            
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Maksymalna wydajność i cykl Carnota
            Maksymalna wydajność silnika cieplnego, działającego między dwoma źródłami o temperaturach \\(T_H\\) i \\(T_C\\), jest określona przez cykl Carnota:
            
            **Wzór Carnota:**
            \\[
            \\eta_{Carnot} = 1 - \\frac{T_C}{T_H}
            \\]
            gdzie:
            - \\(T_H\\): temperatura źródła ciepła (w kelwinach),
            - \\(T_C\\): temperatura chłodnicy (w kelwinach).
            
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Wpływ temperatur źródeł ciepła na wydajność
            Zmiana temperatury \\(T_H\\) i \\(T_C\\) wpływa na maksymalną możliwą wydajność:
            - Wysoka \\(T_H\\) zwiększa wydajność.
            - Niska \\(T_C\\) zmniejsza straty energii.
            
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Zależność wydajności i strat energii
            Wydajność silnika cieplnego jest ograniczona przez straty energii związane z oddawaniem ciepła do chłodnicy:
            - Straty są nieuniknione z powodu drugiego prawa termodynamiki.
            - Przykłady strat: emisja ciepła, tarcie, opory mechaniczne.
            
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Praktyczne ograniczenia technologiczne
            Osiągnięcie maksymalnej wydajności Carnota jest teoretyczne:
            - W rzeczywistych silnikach występują straty, takie jak:
              - Straty ciepła.
              - Tarcie mechaniczne.
              - Straty związane z konstrukcją urządzenia.
            - Technologiczne ograniczenia materiałowe i konstrukcyjne wpływają na osiągalną wydajność.
            
            """;
}

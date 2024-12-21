package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleElectricPotentialLearningRequirement {
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
                "Uczeń zna definicję potencjału elektrycznego i jego jednostki",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczyć potencjał elektryczny dla ładunku punktowego",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie związek między potencjałem elektrycznym a polem elektrycznym",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna właściwości potencjału elektrycznego w różnych układach ładunków",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczyć różnicę potencjałów między dwoma punktami w polu elektrycznym",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleElectricPotentialLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Potencjał elektryczny";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja potencjału elektrycznego
            Potencjał elektryczny w danym punkcie jest to energia potencjalna na jednostkowy ładunek w tym punkcie. Potencjał jest wielkością skalarową, której jednostką jest wolt (V).
            
            **Wzór:**
            \\[
            V = k_e \\frac{q}{r}
            \\]
            gdzie:
            - \\(V\\): potencjał elektryczny,
            - \\(k_e\\): stała elektrostatyczna,
            - \\(q\\): ładunek punktowy,
            - \\(r\\): odległość od ładunku.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Obliczanie potencjału elektrycznego
            Potencjał elektryczny w odległości \\(r\\) od ładunku punktowego \\(q\\) obliczamy ze wzoru:
            
            **Wzór:**
            \\[
            V = k_e \\frac{q}{r}
            \\]
            gdzie:
            - \\(V\\): potencjał elektryczny,
            - \\(k_e\\): stała elektrostatyczna,
            - \\(q\\): ładunek punktowy,
            - \\(r\\): odległość od ładunku.
            
            Przykład: Jeśli \\(q = 3 \\times 10^{-6} C\\) i \\(r = 0.2 m\\), oblicz potencjał w tym punkcie.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Związek między potencjałem elektrycznym a polem elektrycznym
            Pole elektryczne jest gradientem potencjału elektrycznego. Zatem:
            
            \\[
            E = - \\nabla V
            \\]
            
            Oznacza to, że natężenie pola elektrycznego \\(E\\) jest związane z przestrzenną zmianą potencjału \\(V\\). Potencjał maleje w kierunku wzrastającego natężenia pola elektrycznego.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Właściwości potencjału elektrycznego w różnych układach ładunków
            - Dla ładunku dodatniego potencjał rośnie w miarę zbliżania się do ładunku.
            - Dla ładunku ujemnego potencjał maleje w miarę zbliżania się do ładunku.
            - Potencjał elektryczny jest wyższy w miejscach, gdzie ładunki są zgromadzone w większej liczbie.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Różnica potencjałów
            Różnica potencjałów między dwoma punktami w polu elektrycznym jest miarą pracy potrzebnej do przeniesienia jednostkowego ładunku między tymi punktami:
            
            **Wzór:**
            \\[
            \\Delta V = V_B - V_A
            \\]
            gdzie:
            - \\(\\Delta V\\): różnica potencjałów między punktami A i B,
            - \\(V_A\\): potencjał w punkcie A,
            - \\(V_B\\): potencjał w punkcie B.
            
            Różnica potencjałów jest ważnym pojęciem w obliczaniu pracy w polu elektrycznym.
            """;
}

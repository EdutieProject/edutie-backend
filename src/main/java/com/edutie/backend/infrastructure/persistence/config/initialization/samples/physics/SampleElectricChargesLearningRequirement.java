package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleElectricChargesLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Uczeń zna definicję ładunku elektrycznego i jednostki miary ładunku",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie, jak ładunki elektryczne oddziałują między sobą",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi objaśnić pojęcie przewodników i izolatorów w kontekście ładunków elektrycznych",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna zasadę zachowania ładunku elektrycznego",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi rozróżnić między ładunkami dodatnimi i ujemnymi oraz ich oddziaływaniem",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleElectricChargesLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Ładunki elektryczne";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja ładunku elektrycznego
            Ładunek elektryczny to właściwość ciał, która powoduje, że oddziałują one ze sobą na zasadzie przyciągania lub odpychania. 
            Jednostką ładunku elektrycznego jest kulomb (C).
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Oddziaływania ładunków elektrycznych
            Ładunki elektryczne oddziałują na siebie w sposób przyciągający lub odpychający:
            - Ładunki o tym samym znaku odpychają się.
            - Ładunki o przeciwnych znakach przyciągają się.
            
            Zasada ta jest opisana przez prawo Coulomba:
            
            \\[
            F = k_e \\frac{|q_1 q_2|}{r^2}
            \\]
            gdzie:
            - \\(F\\): siła między ładunkami,
            - \\(q_1, q_2\\): ładunki elektryczne,
            - \\(r\\): odległość między ładunkami,
            - \\(k_e\\): stała elektrostatyczna.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Przewodniki i izolatory
            Materiały dzielimy na przewodniki i izolatory w zależności od ich zdolności do przewodzenia ładunków elektrycznych:
            - **Przewodniki**: materiały, w których elektrony mogą swobodnie się poruszać, np. metale.
            - **Izolatory**: materiały, w których elektrony są mocno związane z atomami, np. guma, szkło.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Zasada zachowania ładunku elektrycznego
            Zasada ta mówi, że w układzie izolowanym całkowity ładunek elektryczny pozostaje niezmienny. 
            Oznacza to, że ładunki nie mogą być tworzone ani niszczone, ale mogą być przenoszone z jednego ciała na drugie.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Ładunki dodatnie i ujemne
            - Ładunki dodatnie: przyciągają ładunki ujemne i odpychają inne ładunki dodatnie.
            - Ładunki ujemne: przyciągają ładunki dodatnie i odpychają inne ładunki ujemne.
            
            Wzajemne oddziaływania ładunków elektrycznych są podstawą dla zrozumienia działania urządzeń elektrycznych.
            """;
}

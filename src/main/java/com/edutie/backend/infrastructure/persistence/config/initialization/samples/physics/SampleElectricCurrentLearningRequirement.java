package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleElectricCurrentLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Uczeń zna definicję napięcia elektrycznego i jednostkę napięcia",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczyć napięcie w obwodzie elektrycznym",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie zależność między napięciem a natężeniem prądu elektrycznego w obwodzie",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi opisać zastosowanie napięcia w obwodach elektrycznych",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna zasady dotyczące pomiaru napięcia w obwodach elektrycznych",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleElectricCurrentLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Napięcie elektryczne";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja napięcia elektrycznego
            Napięcie elektryczne to różnica potencjałów między dwoma punktami w polu elektrycznym. Jest to miara pracy, jaką należy wykonać, aby przenieść jednostkowy ładunek między tymi punktami. Jednostką napięcia jest wolt (V).
            
            **Wzór:**
            \\[
            U = \frac{W}{Q}
            \\]
            gdzie:
            - \\(U\\): napięcie elektryczne,
            - \\(W\\): praca wykonana przy przenoszeniu ładunku,
            - \\(Q\\): ładunek.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Obliczanie napięcia elektrycznego
            Napięcie między dwoma punktami można obliczyć, znając wykonaną pracę i przeniesiony ładunek.
            
            **Wzór:**
            \\[
            U = \frac{W}{Q}
            \\]
            
            Przykład: Jeśli praca wynosi \\( 2 J \\), a ładunek \\(Q = 4 C\\), oblicz napięcie.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Zależność między napięciem a natężeniem prądu
            Napięcie w obwodzie elektrycznym jest związane z natężeniem prądu i oporem elektrycznym, zgodnie z prawem Ohma:
            
            **Prawo Ohma:**
            \\[
            U = I \\cdot R
            \\]
            gdzie:
            - \\(U\\): napięcie,
            - \\(I\\): natężenie prądu,
            - \\(R\\): opór elektryczny.
            
            Z tego wzoru wynika, że napięcie jest wprost proporcjonalne do natężenia prądu przy stałym oporze.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Zastosowanie napięcia w obwodach elektrycznych
            Napięcie elektryczne jest podstawową wielkością w obwodach elektrycznych. Służy do przepływu prądu przez różne elementy obwodu, takie jak oporniki, kondensatory, czy źródła energii (np. akumulatory). Napięcie jest także istotnym parametrem przy obliczaniu mocy w obwodach elektrycznych:
            
            **Wzór na moc elektryczną:**
            \\[
            P = U \\cdot I
            \\]
            gdzie:
            - \\(P\\): moc,
            - \\(U\\): napięcie,
            - \\(I\\): natężenie prądu.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Pomiar napięcia
            Napięcie mierzy się za pomocą woltomierza. Woltomierz należy podłączyć równolegle do elementu obwodu, w którym chcemy zmierzyć napięcie. Pomiar napięcia pozwala na ocenę różnicy potencjałów między dwoma punktami w obwodzie.
            """;
}

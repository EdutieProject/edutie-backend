package com.edutie.backend.infrastructure.persistence.config.initialization.samples.finance;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleStockBasicsLearningRequirement {
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
                "Definicja akcji",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Rodzaje akcji",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Zyski z akcji: Dywidendy i wzrost wartości",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Dlaczego ludzie inwestują w akcje",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleStockBasicsLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Inwestowanie w akcje";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja akcji
            Akcja to papier wartościowy, który reprezentuje część własnościową w firmie. Posiadanie akcji daje 
            inwestorowi prawo do części zysków firmy (dywidendy) oraz możliwość uczestniczenia w głosowaniach 
            na walnym zgromadzeniu akcjonariuszy.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Rodzaje akcji
            Istnieją dwa główne rodzaje akcji: akcje zwykłe i akcje uprzywilejowane. Akcje zwykłe dają prawo 
            do głosowania i uczestnictwa w zyskach firmy poprzez dywidendy, natomiast akcje uprzywilejowane 
            dają pierwszeństwo w wypłacie dywidend, ale zazwyczaj nie dają prawa do głosowania.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Zyski z akcji: Dywidendy i wzrost wartości
            Zyski z akcji mogą pochodzić z dwóch głównych źródeł: dywidend oraz wzrostu wartości akcji. 
            Dywidenda to część zysku firmy wypłacana akcjonariuszom. Z kolei wzrost wartości akcji następuje, 
            gdy cena akcji rośnie na rynku, co pozwala inwestorowi sprzedać akcje z zyskiem.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Dlaczego ludzie inwestują w akcje
            Ludzie inwestują w akcje, aby zarobić na wzroście wartości tych akcji lub zyskiwać regularne 
            dochody z dywidend. Inwestowanie w akcje pozwala na długoterminowy wzrost kapitału, a także może 
            stanowić sposób na dywersyfikację portfela inwestycyjnego.
            """;
}

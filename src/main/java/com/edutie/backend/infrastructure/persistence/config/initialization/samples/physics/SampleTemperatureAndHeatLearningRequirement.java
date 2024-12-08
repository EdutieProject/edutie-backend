package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleTemperatureAndHeatLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie, czym jest temperatura i jak ją mierzymy",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna różne skale temperatury i potrafi przeliczać wartości między nimi",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie pojęcie ciepła jako energii przekazywanej między ciałami",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna zasadę zachowania energii w procesach cieplnych",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczać ciepło właściwe i zmiany energii cieplnej w układach",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleTemperatureAndHeatLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Temperatura i ciepło";

    public static final String SUB_REQUIREMENT_1 = """
            ### Czym jest temperatura
            
            **1. Definicja:**
            Temperatura jest miarą średniej energii kinetycznej cząsteczek w ciele.
            
            **2. Pomiar temperatury:**
            - Termometry: cieczowe, elektroniczne, oporowe.
            - Jednostki: stopnie Celsjusza (°C), Kelwiny (K), stopnie Fahrenheita (°F).
            
            ### Znaczenie:
            Temperatura jest kluczowa w opisie zjawisk termodynamicznych, takich jak zmiany fazowe czy przepływ ciepła.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Skale temperatur
            
            **1. Skale temperatur:**
            - **Celsjusz (°C):** Skala oparta na zamarzaniu (0°C) i wrzeniu wody (100°C).
            - **Kelwin (K):** Skala absolutna, 0 K to temperatura absolutnego zera.
            - **Fahrenheit (°F):** Skala używana głównie w USA.
            
            **2. Przeliczanie:**
            - \\(T(K) = T(°C) + 273.15\\)
            - \\(T(°F) = T(°C) \\times \\frac{9}{5} + 32\\)
            - \\(T(°C) = (T(°F) - 32) \\times \\frac{5}{9}\\)
            
            ### Przykład:
            Przelicz 25°C na Kelwiny i Fahrenheity.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Ciepło jako forma energii
            
            **1. Definicja:**
            Ciepło to energia przekazywana między ciałami na skutek różnicy temperatur.
            
            **2. Kierunek przepływu:**
            Energia przepływa od ciała cieplejszego do chłodniejszego.
            
            **3. Procesy cieplne:**
            - Przewodzenie.
            - Konwekcja.
            - Promieniowanie.
            
            ### Przykład:
            Ogrzewanie wody w czajniku – energia cieplna przepływa z grzałki do wody.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Zasada zachowania energii
            
            **1. Pierwsza zasada termodynamiki:**
            \\[
            \\Delta U = Q - W
            \\]
            gdzie:
            - \\(\\Delta U\\): zmiana energii wewnętrznej,
            - \\(Q\\): ciepło przekazane układowi,
            - \\(W\\): praca wykonana przez układ.
            
            **2. Równowaga cieplna:**
            W zamkniętym układzie suma wymienionej energii cieplnej wynosi zero.
            
            ### Przykład:
            Gdy gorąca woda miesza się z zimną, końcowa temperatura układu jest średnią z uwzględnieniem mas i ciepła właściwego.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Obliczenia związane z ciepłem
            
            **1. Ciepło właściwe:**
            \\[
            Q = m \\cdot c \\cdot \\Delta T
            \\]
            gdzie:
            - \\(Q\\): ilość ciepła (J),
            - \\(m\\): masa (kg),
            - \\(c\\): ciepło właściwe (J/kg·K),
            - \\(\\Delta T\\): różnica temperatur (K lub °C).
            
            **2. Procesy zmian fazowych:**
            \\[
            Q = m \\cdot L
            \\]
            gdzie \\(L\\) to ciepło topnienia lub parowania.
            
            ### Przykład:
            Oblicz energię potrzebną do ogrzania 2 kg wody o 20°C.
            """;
}

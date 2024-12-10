package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleIdealGasTheoryLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie założenia teorii gazu idealnego i ich znaczenie",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna równanie stanu gazu idealnego i potrafi je stosować",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi wyjaśnić, jak zmiany temperatury, ciśnienia i objętości wpływają na gaz idealny",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie związek między teorią kinetyczną gazów a równaniem stanu gazu idealnego",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń umie przeprowadzić obliczenia związane z gazami idealnymi, takie jak ciśnienie cząstkowe czy liczba moli",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleIdealGasTheoryLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Teoria gazu idealnego";

    public static final String SUB_REQUIREMENT_1 = """
            ### Założenia teorii gazu idealnego
            
            **1. Cząsteczki gazu idealnego:**
            - Są punktowe, o pomijalnie małych rozmiarach.
            - Nie oddziałują na siebie poza elastycznymi zderzeniami.
            
            **2. Ruch cząsteczek:**
            - Cząsteczki poruszają się chaotycznie z różnymi prędkościami.
            
            **3. Energia kinetyczna:**
            - Średnia energia kinetyczna zależy tylko od temperatury gazu.
            
            ### Znaczenie:
            Teoria gazu idealnego dostarcza uproszczonego modelu do zrozumienia gazów i przewidywania ich zachowania w różnych warunkach.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Równanie stanu gazu idealnego
            
            **1. Postać równania:**
            \\[
            PV = nRT
            \\]
            gdzie:
            - \\(P\\): ciśnienie (Pa),
            - \\(V\\): objętość (m³),
            - \\(n\\): liczba moli,
            - \\(R\\): stała gazowa (8.314 J/mol·K),
            - \\(T\\): temperatura (K).
            
            **2. Stosowanie równania:**
            Pozwala obliczyć jedną z wielkości gazu, jeśli pozostałe są znane.
            
            ### Przykład:
            Obliczanie objętości 2 moli gazu w temperaturze 300 K przy ciśnieniu 100 kPa.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Zmiany parametrów gazu idealnego
            
            **1. Temperatura a ciśnienie i objętość:**
            - Wyższa temperatura zwiększa ciśnienie, jeśli objętość jest stała.
            - Przy stałym ciśnieniu zwiększa objętość.
            
            **2. Prawo Boyle’a:**
            Przy stałej temperaturze, \\(P \\times V = const\\).
            
            **3. Prawo Charlesa:**
            Przy stałym ciśnieniu, \\(\\frac{V}{T} = const\\).
            
            ### Przykład:
            Rozszerzanie balonu podczas ogrzewania, przy stałym ciśnieniu atmosferycznym.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Teoria kinetyczna gazów a równanie stanu
            
            **1. Energia kinetyczna i ciśnienie:**
            Ciśnienie wynika ze zderzeń cząsteczek gazu z ściankami naczynia, co jest zgodne z równaniem stanu.
            
            **2. Temperatura i energia kinetyczna:**
            Średnia energia kinetyczna cząsteczek \\(E_k\\) jest proporcjonalna do temperatury \\(T\\):
            \\[
            E_k = \\frac{3}{2} k_B T
            \\]
            gdzie \\(k_B\\) to stała Boltzmanna.
            
            **3. Pochodzenie równania stanu:**
            Analiza kinetyczna gazów prowadzi do uzasadnienia równania \\(PV = nRT\\).
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Obliczenia związane z gazami idealnymi
            
            **1. Liczba moli:**
            \\[
            n = \\frac{m}{M}
            \\]
            gdzie \\(m\\) to masa, a \\(M\\) to masa molowa.
            
            **2. Ciśnienie cząstkowe:**
            \\[
            P_i = \\frac{n_i}{n_{total}} \\cdot P_{total}
            \\]
            
            **3. Przykłady obliczeń:**
            - Obliczanie objętości gazu przy danej liczbie moli i warunkach.
            - Wyznaczanie temperatury z równania stanu.
            
            ### Przykład:
            Oblicz ciśnienie gazu zawartego w zbiorniku o objętości 10 L, zawierającym 1 mol gazu w temperaturze 273 K.
            """;
}

package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleElectricFieldLearningRequirement {
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
                "Uczeń zna definicję pola elektrycznego i jego właściwości",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczyć natężenie pola elektrycznego wokół ładunku punktowego",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie zależność między natężeniem pola elektrycznego a odległością od ładunku",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi narysować linie pola elektrycznego dla różnych układów ładunków",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna pojęcie potencjału elektrycznego i jego związek z polem elektrycznym",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleElectricFieldLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Pole elektryczne";

    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja pola elektrycznego
            Pole elektryczne to przestrzeń wokół ładunku elektrycznego, w której na inne ładunki działa siła elektrostatyczna. Siła ta jest proporcjonalna do ładunku i odwrotnie proporcjonalna do kwadratu odległości między ładunkiem a punktem, w którym badamy pole.
            
            Pole elektryczne opisujemy wektorem, którego kierunek wskazuje na siłę działającą na ładunek dodatni, a wartość – natężenie pola w danym punkcie.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Obliczanie natężenia pola elektrycznego
            Natężenie pola elektrycznego \\(E\\) w odległości \\(r\\) od ładunku punktowego \\(q\\) obliczamy ze wzoru:
            
            **Wzór:**
            \\[
            E = k_e \\frac{|q|}{r^2}
            \\]
            gdzie:
            - \\(E\\): natężenie pola elektrycznego,
            - \\(k_e\\): stała elektrostatyczna,
            - \\(q\\): ładunek punktowy,
            - \\(r\\): odległość od ładunku.
            
            Przykład: Jeśli \\(q = 2 \\times 10^{-6} C\\) i \\(r = 0.5 m\\), oblicz natężenie pola w tym punkcie.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Zależność natężenia pola elektrycznego od odległości
            Natężenie pola elektrycznego jest odwrotnie proporcjonalne do kwadratu odległości od ładunku:
            
            \\[
            E \\propto \\frac{1}{r^2}
            \\]
            
            Oznacza to, że im bliżej ładunku, tym większe jest natężenie pola elektrycznego. Zwiększenie odległości zmniejsza natężenie pola.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Linie pola elektrycznego
            Linie pola elektrycznego pokazują kierunek i kształt pola wokół ładunku. W przypadku ładunku dodatniego linie wychodzą z ładunku, a w przypadku ładunku ujemnego wchodzą do ładunku.
            
            - Linie pola elektrycznego nigdy się nie krzyżują.
            - Linie pola są gęstsze w miejscach o większym natężeniu pola.
            - W układzie dwóch ładunków o przeciwnych znakach linie pola łączą je, tworząc charakterystyczny kształt.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Potencjał elektryczny i jego związek z polem elektrycznym
            Potencjał elektryczny to miara energii potencjalnej na jednostkowy ładunek w danym punkcie pola. Pole elektryczne jest gradientem potencjału, co oznacza, że:
            
            \\[
            E = - \\nabla V
            \\]
            gdzie \\(V\\) to potencjał elektryczny.
            
            Potencjał wyrażany jest w jednostkach woltów (V), a pole elektryczne w jednostkach newtonów na kulomb (N/C).
            """;
}

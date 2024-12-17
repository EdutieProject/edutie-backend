package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleBasicTrigonometryLearningRequirement {
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
                "Zrozumienie kątów i boków w trójkącie prostokątnym",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Definicja funkcji sinus na podstawie trójkąta prostokątnego",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Definicja funkcji cosinus na podstawie trójkąta prostokątnego",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Definicja funkcji tangens w odniesieniu do trójkąta prostokątnego",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Związek między sinus, cosinus i tangens w trójkącie prostokątnym",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie twierdzenia Pitagorasa w trójkącie prostokątnym",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie funkcji trygonometrycznych do obliczania brakujących długości boków",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie funkcji trygonometrycznych do obliczania brakujących kątów",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );

        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleBasicTrigonometryLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Podstawy trygonometrii na trójkącie prostokątnym";

    public static final String SUB_REQUIREMENT_1 = """
            ### Zrozumienie kątów i boków w trójkącie prostokątnym
            - Trójkąt prostokątny ma jeden kąt prosty (\\(90°\\)).
            - Pozostałe dwa kąty są ostre (mniejsze niż \\(90°\\)).
            - Bok naprzeciw kąta prostego to **przeciwprostokątna**.
            - Boki przylegające do kąta ostrego to **przyprostokątne**.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Definicja funkcji sinus
            Funkcja sinus jest zdefiniowana jako stosunek długości boku przeciwległego do długości przeciwprostokątnej:
            \\[
            \\sin(\\theta) = \\frac{\\text{przeciwległa}}{\\text{przeciwprostokątna}}
            \\]
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Definicja funkcji cosinus
            Funkcja cosinus jest zdefiniowana jako stosunek długości boku przyległego do kąta do długości przeciwprostokątnej:
            \\[
            \\cos(\\theta) = \\frac{\\text{przyległa}}{\\text{przeciwprostokątna}}
            \\]
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Definicja funkcji tangens
            Funkcja tangens jest zdefiniowana jako stosunek długości boku przeciwległego do boku przyległego:
            \\[
            \\tan(\\theta) = \\frac{\\text{przeciwległa}}{\\text{przyległa}}
            \\]
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Związek między sinus, cosinus i tangens
            Funkcje sinus, cosinus i tangens są ze sobą powiązane:
            - \\(\\tan(\\theta) = \\frac{\\sin(\\theta)}{\\cos(\\theta)}\\).
            - Każda z tych funkcji opisuje różne aspekty relacji między bokami trójkąta prostokątnego.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Zastosowanie twierdzenia Pitagorasa
            Twierdzenie Pitagorasa mówi, że w trójkącie prostokątnym suma kwadratów długości przyprostokątnych jest równa kwadratowi długości przeciwprostokątnej:
            \\[
            a^2 + b^2 = c^2
            \\]
            gdzie \\(a\\) i \\(b\\) to długości przyprostokątnych, a \\(c\\) to długość przeciwprostokątnej.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Obliczanie brakujących długości boków
            Funkcje trygonometryczne umożliwiają obliczenie brakującego boku trójkąta prostokątnego, gdy znany jest jeden kąt ostry i długość jednego boku.
            Przykład:
            - Znając \\(\\sin(\\theta)\\) i przeciwprostokątną, można znaleźć bok przeciwległy:
              \\[
              \\text{przeciwległa} = \\sin(\\theta) \\times \\text{przeciwprostokątna}
              \\]
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Obliczanie brakujących kątów
            Funkcje trygonometryczne umożliwiają wyznaczenie brakujących kątów, gdy znane są długości boków.
            Przykład:
            - Jeśli znasz \\(\\sin(\\theta)\\), możesz znaleźć kąt \\(\\theta\\) za pomocą funkcji odwrotnej:
              \\[
              \\theta = \\arcsin\\left(\\frac{\\text{przeciwległa}}{\\text{przeciwprostokątna}}\\right)
              \\]
            """;
}

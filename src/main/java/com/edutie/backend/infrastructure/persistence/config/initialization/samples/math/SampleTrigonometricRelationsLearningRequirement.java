package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleTrigonometricRelationsLearningRequirement {
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
                "Definicja funkcji trygonometrycznych",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Podstawowe tożsamości trygonometryczne",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Relacje między sinusami, cosinusami i tangensami",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Jedynka trygonometryczna",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Wzory redukcyjne",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Sumy i różnice kątów",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleTrigonometricRelationsLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Relacje między funkcjami trygonometrycznymi";
    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja funkcji trygonometrycznych
            Funkcje trygonometryczne, takie jak sinus \\(\\sin\\), cosinus \\(\\cos\\), tangens \\(\\tan\\), i cotangens \\(\\cot\\), 
            opisują zależności między długościami boków trójkąta prostokątnego i jego kątami. Na przykład:
            - \\(\\sin(\\theta) = \\frac{przeciwległa}{przeciwprostokątna}\\),
            - \\(\\cos(\\theta) = \\frac{przyległa}{przeciwprostokątna}\\),
            - \\(\\tan(\\theta) = \\frac{\\sin(\\theta)}{\\cos(\\theta)}\\),
            - \\(\\cot(\\theta) = \\frac{\\cos(\\theta)}{\\sin(\\theta)}\\).
            Funkcje te są kluczowe w analizie geometrii i fal.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Podstawowe tożsamości trygonometryczne
            Tożsamości trygonometryczne pozwalają uprościć wyrażenia i obliczenia. Przykłady:
            - \\(\\sin^2(\\theta) + \\cos^2(\\theta) = 1\\),
            - \\(1 + \\tan^2(\\theta) = \\sec^2(\\theta)\\),
            - \\(1 + \\cot^2(\\theta) = \\csc^2(\\theta)\\).
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Relacje między sinusami, cosinusami i tangensami
            Funkcje trygonometryczne są powiązane następującymi relacjami:
            - \\(\\tan(\\theta) = \\frac{\\sin(\\theta)}{\\cos(\\theta)}\\),
            - \\(\\cot(\\theta) = \\frac{\\cos(\\theta)}{\\sin(\\theta)}\\),
            - \\(\\sin(\\theta) = \\cos(90° - \\theta)\\), 
            - \\(\\cos(\\theta) = \\sin(90° - \\theta)\\).
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Jedynka trygonometryczna
            Podstawową zależnością między \\(\\sin\\) i \\(\\cos\\) jest jedynka trygonometryczna:
            \\[
            \\sin^2(\\theta) + \\cos^2(\\theta) = 1.
            \\]
            Wynika to z geometrii trójkąta prostokątnego na okręgu jednostkowym.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Wzory redukcyjne
            Wzory redukcyjne upraszczają wyrażenia trygonometryczne dla kątów większych niż \\(90°\\). Przykłady:
            - \\(\\sin(180° - \\theta) = \\sin(\\theta)\\),
            - \\(\\cos(180° - \\theta) = -\\cos(\\theta)\\),
            - \\(\\tan(180° - \\theta) = -\\tan(\\theta)\\).
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Sumy i różnice kątów
            Tożsamości dla sumy i różnicy kątów pozwalają obliczać wartości funkcji trygonometrycznych:
            - \\(\\sin(\\alpha \\pm \\beta) = \\sin(\\alpha)\\cos(\\beta) \\pm \\cos(\\alpha)\\sin(\\beta)\\),
            - \\(\\cos(\\alpha \\pm \\beta) = \\cos(\\alpha)\\cos(\\beta) \\mp \\sin(\\alpha)\\sin(\\beta)\\).
            """;

}

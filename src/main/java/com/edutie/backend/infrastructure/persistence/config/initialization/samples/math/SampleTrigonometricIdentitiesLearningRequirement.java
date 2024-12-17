package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleTrigonometricIdentitiesLearningRequirement {
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
                "Uczeń rozumie podstawową tożsamość trygonometryczną \\(\\sin^2(\\theta) + \\cos^2(\\theta) = 1\\).",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna zależność między tangensem i sinusem oraz cosinusem: \\(\\tan(\\theta) = \\frac{\\sin(\\theta)}{\\cos(\\theta)}\\).",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi wyprowadzić i zastosować tożsamości trygonometryczne dla funkcji secans i cosecans.",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie i stosuje tożsamości dla sumy i różnicy kątów, takie jak \\(\\sin(a \\pm b)\\) i \\(\\cos(a \\pm b)\\).",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie tożsamości podwójnego kąta, takie jak \\(\\sin(2\\theta)\\) i \\(\\cos(2\\theta)\\), oraz potrafi je wyprowadzić.",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna i stosuje tożsamości trygonometryczne dla połowy kąta, takie jak \\(\\sin^2(\\frac{\\theta}{2})\\) i \\(\\cos^2(\\frac{\\theta}{2})\\).",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi przekształcać wyrażenia trygonometryczne przy użyciu tożsamości iloczynu na sumę i różnicę, takie jak \\(\\sin(a)\\cos(b)\\).",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi uprościć złożone wyrażenia trygonometryczne z wykorzystaniem zaawansowanych tożsamości.",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );

        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleTrigonometricIdentitiesLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Tożsamości trygonometryczne";

    public static final String SUB_REQUIREMENT_1 = """
            ### Podstawowa tożsamość trygonometryczna
            Każdy kąt \\(\\theta\\) spełnia równanie:
            \\[
            \\sin^2(\\theta) + \\cos^2(\\theta) = 1
            \\]
            Ta tożsamość opisuje zależność między wartościami funkcji sinus i cosinus.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Związek między tangensem, sinusem i cosinusem
            Funkcja tangens jest zdefiniowana jako stosunek funkcji sinus do funkcji cosinus:
            \\[
            \\tan(\\theta) = \\frac{\\sin(\\theta)}{\\cos(\\theta)}
            \\]
            Tożsamość ta jest użyteczna przy analizie trójkątów prostokątnych i wykresów funkcji trygonometrycznych.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Tożsamości dla secans i cosecans
            Funkcje secans i cosecans mają następujące tożsamości:
            \\[
            \\sec^2(\\theta) = 1 + \\tan^2(\\theta)
            \\]
            \\[
            \\csc^2(\\theta) = 1 + \\cot^2(\\theta)
            \\]
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Tożsamości sumy i różnicy kątów
            Dla dowolnych kątów \\(a\\) i \\(b\\):
            \\[
            \\sin(a \\pm b) = \\sin(a)\\cos(b) \\pm \\cos(a)\\sin(b)
            \\]
            \\[
            \\cos(a \\pm b) = \\cos(a)\\cos(b) \\mp \\sin(a)\\sin(b)
            \\]
            Te tożsamości są kluczowe w rozwiązywaniu równań trygonometrycznych i analizie fal.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Tożsamości podwójnego kąta
            Tożsamości podwójnego kąta pozwalają przekształcać wyrażenia dla \\(2\\theta\\):
            \\[
            \\sin(2\\theta) = 2\\sin(\\theta)\\cos(\\theta)
            \\]
            \\[
            \\cos(2\\theta) = \\cos^2(\\theta) - \\sin^2(\\theta)
            \\]
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Tożsamości dla połowy kąta
            Dla kąta \\(\\theta/2\\):
            \\[
            \\sin^2(\\frac{\\theta}{2}) = \\frac{1 - \\cos(\\theta)}{2}
            \\]
            \\[
            \\cos^2(\\frac{\\theta}{2}) = \\frac{1 + \\cos(\\theta)}{2}
            \\]
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Tożsamości iloczynu na sumę i różnicę
            Wyrażenia trygonometryczne mogą być przekształcane przy użyciu następujących równań:
            \\[
            \\sin(a)\\cos(b) = \\frac{1}{2}[\\sin(a+b) + \\sin(a-b)]
            \\]
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Uproszczenia złożonych wyrażeń trygonometrycznych
            Uczniowie uczą się stosować zaawansowane tożsamości w celu uproszczenia wyrażeń trygonometrycznych w problemach matematycznych i fizycznych.
            """;
}

package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleUnitCircleLearningRequirement {
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
                "Definicja okręgu jednostkowego",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Sinus i cosinus na okręgu jednostkowym",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Tangens i cotangens na okręgu jednostkowym",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Interpretacja wartości dodatnich i ujemnych",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Wartości funkcji trygonometrycznych dla popularnych kątów",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Tożsamości trygonometryczne na okręgu jednostkowym",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Zastosowanie okręgu jednostkowego w zadaniach praktycznych",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleUnitCircleLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Funkcje trygonometryczne na okręgu jednostkowym";
    public static final String SUB_REQUIREMENT_1 = """
            ### Definicja okręgu jednostkowego
            Okrąg jednostkowy to okrąg w układzie współrzędnych o promieniu \\(1\\) i środku w punkcie \\((0, 0)\\). 
            Każdy punkt na okręgu spełnia równanie \\(x^2 + y^2 = 1\\).
            Okrąg jednostkowy jest podstawowym narzędziem do analizy funkcji trygonometrycznych, gdzie:
            - Współrzędna \\(x\\) punktu reprezentuje \\(\\cos(\\theta)\\),
            - Współrzędna \\(y\\) punktu reprezentuje \\(\\sin(\\theta)\\),
            dla kąta \\(\\theta\\), mierzonego od dodatniej półosi \\(x\\) w kierunku przeciwnym do ruchu wskazówek zegara.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Sinus i cosinus na okręgu jednostkowym
            Na okręgu jednostkowym:
            - \\(\\cos(\\theta)\\) to współrzędna \\(x\\) punktu na okręgu odpowiadającego kątowi \\(\\theta\\),
            - \\(\\sin(\\theta)\\) to współrzędna \\(y\\) tego samego punktu.
            Wartości \\(\\sin(\\theta)\\) i \\(\\cos(\\theta)\\) zmieniają się w przedziale \\([-1, 1]\\) w zależności od kąta \\(\\theta\\).
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Tangens i cotangens na okręgu jednostkowym
            - \\(\\tan(\\theta)\\) jest definiowany jako stosunek \\(\\frac{\\sin(\\theta)}{\\cos(\\theta)}\\) i odpowiada nachyleniu prostej przechodzącej przez punkt \\((\\cos(\\theta), \\sin(\\theta))\\) oraz środek \\((0, 0)\\).
            - \\(\\cot(\\theta)\\) to odwrotność tangensa: \\(\\cot(\\theta) = \\frac{\\cos(\\theta)}{\\sin(\\theta)}\\).
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Interpretacja wartości dodatnich i ujemnych
            Wartości funkcji trygonometrycznych zmieniają znak w różnych ćwiartkach okręgu jednostkowego:
            - W pierwszej ćwiartce \\(\\sin(\\theta) > 0\\) i \\(\\cos(\\theta) > 0\\),
            - W drugiej ćwiartce \\(\\sin(\\theta) > 0\\) i \\(\\cos(\\theta) < 0\\),
            - W trzeciej ćwiartce \\(\\sin(\\theta) < 0\\) i \\(\\cos(\\theta) < 0\\),
            - W czwartej ćwiartce \\(\\sin(\\theta) < 0\\) i \\(\\cos(\\theta) > 0\\).
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Wartości funkcji trygonometrycznych dla popularnych kątów
            Na okręgu jednostkowym łatwo odczytać wartości funkcji trygonometrycznych dla kątów \\(0°\\), \\(30°\\), \\(45°\\), \\(60°\\), i \\(90°\\):
            - \\(\\sin(0°) = 0\\), \\(\\cos(0°) = 1\\),
            - \\(\\sin(90°) = 1\\), \\(\\cos(90°) = 0\\),
            - oraz inne wartości, które można wyprowadzić z geometrii okręgu.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Tożsamości trygonometryczne na okręgu jednostkowym
            Tożsamości trygonometryczne, takie jak \\(\\sin^2(\\theta) + \\cos^2(\\theta) = 1\\), wynikają bezpośrednio z równania okręgu jednostkowego \\(x^2 + y^2 = 1\\).
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Zastosowanie okręgu jednostkowego w zadaniach praktycznych
            Okrąg jednostkowy pozwala wizualizować i rozwiązywać zadania związane z trygonometrią, takie jak:
            - Obliczanie wartości funkcji trygonometrycznych dla dowolnego kąta,
            - Analiza przesunięć fazowych w falach,
            - Zastosowania w fizyce i inżynierii.
            """;
}

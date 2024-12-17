package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleHeatEngineLearningRequirement {
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
                "Uczeń zna podstawowe zasady działania silnika cieplnego i definiuje pojęcie silnika cieplnego",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie, że silnik cieplny przekształca energię cieplną w pracę mechaniczną",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna cykl Carnota jako model idealnego silnika cieplnego",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi wyjaśnić rolę temperatury źródła ciepła i chłodnicy w pracy silnika cieplnego",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń umie obliczać sprawność silnika cieplnego przy pomocy wzoru \\( \\eta = 1 - \\frac{T_{ch}}{T_{zh}} \\)",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi analizować straty energetyczne w praktycznych zastosowaniach silników cieplnych",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie, że silnik cieplny działa zgodnie z zasadami termodynamiki, w tym drugą zasadą termodynamiki",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń umie opisać przykłady zastosowania silników cieplnych, np. w samochodach i elektrowniach",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleHeatEngineLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Silniki cieplne i ich zastosowania";

    public static final String SUB_REQUIREMENT_1 = """
            ### Podstawowe pojęcie: Silnik cieplny
            Silnik cieplny to urządzenie, które przekształca energię cieplną w pracę mechaniczną. Działa na zasadzie wykorzystania różnicy temperatur między źródłem ciepła a chłodnicą.
            - **Definicja**: Silnik cieplny to maszyna cieplna, która zamienia ciepło w pracę mechaniczną, zgodnie z zasadami termodynamiki.
            - **Przykłady**: Silniki samochodowe, turbiny parowe, silniki lotnicze.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Zasada działania silnika cieplnego
            Silnik cieplny działa poprzez pobieranie energii cieplnej ze źródła ciepła, przekształcanie części tej energii w pracę mechaniczną, a resztę oddawanie do chłodnicy.
            - **Energia cieplna → Praca mechaniczna**: Dzięki procesowi termodynamicznemu ciepło zostaje zamienione na ruch tłoka lub obrót turbiny.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Cykl Carnota
            Cykl Carnota to teoretyczny model idealnego silnika cieplnego, który działa z maksymalną możliwą sprawnością.
            - **Fazy cyklu Carnota**:
              1. Izotermiczne rozprężanie gazu.
              2. Adiabatyczne rozprężanie.
              3. Izotermiczne sprężanie.
              4. Adiabatyczne sprężanie.
            - **Dlaczego jest ważny**: Służy jako wzorzec dla rzeczywistych silników cieplnych.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Rola temperatur w silniku cieplnym
            - Temperatura źródła ciepła \\(T_{zh}\\): Im wyższa, tym więcej energii można wykorzystać do wykonania pracy.
            - Temperatura chłodnicy \\(T_{ch}\\): Im niższa, tym większa różnica temperatur i wyższa sprawność silnika.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Sprawność silnika cieplnego
            Sprawność \\( \\eta \\) to stosunek pracy mechanicznej wykonanej przez silnik do energii cieplnej pobranej ze źródła ciepła.
            - Wzór: \\( \\eta = 1 - \\frac{T_{ch}}{T_{zh}} \\), gdzie \\(T_{zh}\\) i \\(T_{ch}\\) są wyrażone w kelwinach.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Straty energetyczne
            Rzeczywiste silniki cieplne zawsze mają straty energetyczne:
            - Tarcie mechaniczne.
            - Ciepło tracone do otoczenia.
            - Ograniczenia materiałowe i technologiczne.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Zasady termodynamiki
            Silnik cieplny działa zgodnie z zasadami termodynamiki:
            - **Pierwsza zasada**: Energia nie znika, lecz zmienia formę (np. ciepło w pracę).
            - **Druga zasada**: Część energii zawsze jest tracona jako ciepło do chłodnicy.
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Zastosowania silników cieplnych
            Silniki cieplne znajdują zastosowanie w wielu dziedzinach:
            - **Transport**: Samochody, samoloty, statki.
            - **Energetyka**: Elektrownie węglowe, gazowe i jądrowe.
            - **Przemysł**: Turbiny przemysłowe i generatory prądu.
            """;
}

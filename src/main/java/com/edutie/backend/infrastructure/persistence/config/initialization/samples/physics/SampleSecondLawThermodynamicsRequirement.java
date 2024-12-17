package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleSecondLawThermodynamicsRequirement {
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
                "Uczeń zna treść drugiego prawa termodynamiki w sformułowaniach Clausiusa i Kelvina-Plancka",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi interpretować pojęcie entropii jako miary nieodwracalności procesów",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie, że entropia w izolowanym układzie nie maleje",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie, że procesy cieplne są kierunkowe, a ich nieodwracalność wynika z drugiego prawa termodynamiki",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi opisać wzrost entropii w przykładach praktycznych, takich jak mieszanie substancji lub przepływ ciepła",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi rozróżniać między procesami odwracalnymi i nieodwracalnymi oraz wyjaśnić ich wpływ na entropię",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie znaczenie drugiego prawa termodynamiki w kontekście efektywności silników cieplnych i innych urządzeń termodynamicznych",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleSecondLawThermodynamicsRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Drugie prawo termodynamiki";
    public static final String SUB_REQUIREMENT_1 = """
            ### Drugie prawo termodynamiki
            Drugie prawo termodynamiki można sformułować na kilka sposobów:
            
            **1. Sformułowanie Clausiusa:**
            Ciepło nigdy samoistnie nie przepływa z ciała zimniejszego do cieplejszego.
            
            **2. Sformułowanie Kelvina-Plancka:**
            Nie jest możliwe zbudowanie silnika cieplnego, który przekształca całe pobrane ciepło w pracę bez jakichkolwiek strat.
            
            ### Znaczenie drugiego prawa:
            Drugie prawo termodynamiki określa kierunkowość procesów termodynamicznych, podkreślając, że istnieją ograniczenia w przekształcaniu energii.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Pojęcie entropii
            Entropia jest miarą nieuporządkowania układu. W procesach termodynamicznych wzrost entropii jest związany z nieodwracalnością.
            
            **Wzór na entropię w procesach odwracalnych:**
            \\[
            \\Delta S = \\int \\frac{dQ}{T}
            \\]
            
            ### Znaczenie entropii:
            Entropia pozwala zrozumieć, dlaczego procesy przebiegają w określonym kierunku i dlaczego układy dążą do równowagi.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Zasada wzrostu entropii
            W izolowanym układzie entropia nigdy nie maleje. Oznacza to, że procesy naturalne są zawsze nieodwracalne.
            
            **Przykłady:**
            - Ciepło przepływa od ciała cieplejszego do zimniejszego.
            - Mieszanie substancji zwiększa entropię.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Kierunkowość procesów cieplnych
            Drugie prawo termodynamiki wyjaśnia, dlaczego procesy cieplne są kierunkowe, np.:
            - Gaz wypełnia całą dostępną objętość.
            - Ciepło przepływa od ciała cieplejszego do zimniejszego.
            
            Kierunkowość wynika z faktu, że procesy te zwiększają entropię.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Przykłady wzrostu entropii
            Wzrost entropii można zaobserwować w wielu sytuacjach, np.:
            - Mieszanie substancji: wzrost nieuporządkowania.
            - Przepływ ciepła: wyrównanie temperatur w układzie.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Procesy odwracalne i nieodwracalne
            - Procesy odwracalne: idealne, w których entropia układu pozostaje stała.
            - Procesy nieodwracalne: rzeczywiste, w których entropia układu rośnie.
            
            **Przykłady procesów nieodwracalnych:**
            - Tarcie mechaniczne.
            - Dyfuzja gazów.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Znaczenie drugiego prawa dla urządzeń termodynamicznych
            Drugie prawo określa maksymalną efektywność urządzeń takich jak:
            - Silniki cieplne: sprawność ograniczona przez entropię.
            - Lodówki: wymagają pracy do transportu ciepła z ciała zimniejszego do cieplejszego.
            """;
}

package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleKineticMolecularTheoryLearningRequirement {
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
                "Uczeń rozumie, że materia składa się z cząsteczek, które są w ciągłym ruchu",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi wyjaśnić związek między temperaturą a energią kinetyczną cząsteczek",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń umie opisać, jak zmiana stanu skupienia wynika z oddziaływań między cząsteczkami",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi interpretować ciśnienie gazu jako efekt zderzeń cząsteczek z powierzchnią",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi wykorzystać kinetyczno-molekularną teorię materii do wyjaśnienia podstawowych właściwości gazów, cieczy i ciał stałych",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleKineticMolecularTheoryLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Kinetyczno-molekularna teoria materii";

    public static final String SUB_REQUIREMENT_1 = """
            ### Koncept teoretyczny: Ruch cząsteczek w materii
            
            **1. Wszystkie substancje składają się z cząsteczek:**
            Każda substancja jest złożona z małych cząsteczek, które są w ciągłym ruchu, niezależnie od stanu skupienia (gaz, ciecz, ciało stałe).
            
            **2. Ruch cząsteczek w zależności od stanu skupienia:**
            - W gazach: Cząsteczki poruszają się swobodnie w różnych kierunkach i z dużą prędkością.
            - W cieczach: Cząsteczki są blisko siebie, ale mogą się przesuwać względem siebie.
            - W ciałach stałych: Cząsteczki drgają wokół stałych pozycji.
            
            ### Dlaczego jest to ważne?
            Zrozumienie ruchu cząsteczek to podstawa w opisywaniu procesów fizycznych i chemicznych, takich jak dyfuzja, zmiana stanu skupienia czy przewodnictwo cieplne.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Związek między temperaturą a energią kinetyczną cząsteczek
            
            **1. Temperatura to miara energii kinetycznej cząsteczek:**
            Wyższa temperatura oznacza większą średnią energię kinetyczną cząsteczek.
            
            **2. Energia kinetyczna cząsteczek:**
            Energia kinetyczna pojedynczej cząsteczki zależy od jej masy \\(m\\) i prędkości \\(v\\):
            \\[
            E_k = \\frac{1}{2} m v^2
            \\]
            
            **3. Ruch cząsteczek a temperatura:**
            W gazach, wzrost temperatury powoduje szybszy ruch cząsteczek, co wpływa na ciśnienie i objętość gazu.
            
            ### Przykład:
            Ogrzewanie gazu w zamkniętym pojemniku zwiększa energię kinetyczną cząsteczek, co prowadzi do wzrostu ciśnienia.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Zmiana stanu skupienia i oddziaływania między cząsteczkami
            
            **1. Stany skupienia:**
            - Ciało stałe: Silne oddziaływania między cząsteczkami, zachowanie kształtu i objętości.
            - Ciecz: Oddziaływania średniej siły, zachowanie objętości, ale nie kształtu.
            - Gaz: Oddziaływania bardzo słabe, brak stałego kształtu i objętości.
            
            **2. Zmiana stanu skupienia:**
            Zmiana energii (ciepła) powoduje zmiany w ruchu i oddziaływaniach cząsteczek:
            - Topnienie: Cząsteczki zyskują energię i mogą się przesuwać.
            - Parowanie: Cząsteczki opuszczają ciecz, przechodząc w stan gazowy.
            - Skraplanie: Cząsteczki tracą energię, tworząc ciecz.
            
            ### Przykład:
            Podczas wrzenia wody cząsteczki w cieczy zyskują wystarczającą energię, aby pokonać oddziaływania między nimi i stać się gazem.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Ciśnienie gazu a zderzenia cząsteczek
            
            **1. Ciśnienie jako efekt zderzeń:**
            Cząsteczki gazu zderzają się z powierzchniami, wywierając ciśnienie. Większa liczba i intensywność zderzeń oznacza wyższe ciśnienie.
            
            **2. Zależność od temperatury:**
            Wzrost temperatury powoduje szybszy ruch cząsteczek i większą częstość zderzeń, co zwiększa ciśnienie.
            
            ### Przykład:
            W zamkniętym balonie ogrzewanie gazu powoduje wzrost ciśnienia, co może prowadzić do jego pęknięcia.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Wyjaśnienie właściwości materii na podstawie teorii kinetyczno-molekularnej
            
            **1. Gazy:**
            - Swobodny ruch cząsteczek.
            - Łatwe sprężanie i rozszerzanie.
            
            **2. Ciecze:**
            - Cząsteczki są blisko siebie, co daje większą gęstość niż gazy.
            - Możliwość płynięcia wynikająca z ruchu między cząsteczkami.
            
            **3. Ciała stałe:**
            - Silne oddziaływania między cząsteczkami.
            - Stały kształt i objętość.
            
            ### Przykład:
            Lodowaty blok (ciało stałe) po ogrzaniu topnieje do wody (ciecz), a następnie odparowuje do pary (gaz), co pokazuje, jak energia wpływa na właściwości materii.
            """;
}

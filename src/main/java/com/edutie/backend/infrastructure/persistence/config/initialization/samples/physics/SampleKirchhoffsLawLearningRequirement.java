package com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleKirchhoffsLawLearningRequirement {
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
                "Uczeń zna pierwsze i drugie prawo Kirchoffa i potrafi je zastosować w obwodach elektrycznych",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi zastosować pierwsze prawo Kirchoffa (zasada zachowania ładunku) do analizy obwodów elektrycznych",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi zastosować drugie prawo Kirchoffa (zasada zachowania energii) w obwodach elektrycznych",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi rozwiązywać układy równań z wykorzystaniem równań wynikających z praw Kirchoffa",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie zastosowanie praw Kirchoffa w praktycznych obwodach elektrycznych",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleKirchhoffsLawLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Prawo Kirchoffa";

    public static final String SUB_REQUIREMENT_1 = """
            ### Pierwsze i drugie prawo Kirchoffa
            Prawo Kirchoffa opisuje zasady dotyczące obwodów elektrycznych:
            
            - **Pierwsze prawo Kirchoffa** (zasada zachowania ładunku): suma prądów wchodzących do węzła w obwodzie elektrycznym jest równa sumie prądów wychodzących z tego węzła.
            - **Drugie prawo Kirchoffa** (zasada zachowania energii): suma napięć wzdłuż dowolnej zamkniętej pętli w obwodzie elektrycznym jest równa zeru.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Pierwsze prawo Kirchoffa (Zasada zachowania ładunku)
            Pierwsze prawo Kirchoffa, znane również jako zasada węzła, mówi, że suma prądów wchodzących do węzła w obwodzie elektrycznym musi być równa sumie prądów wychodzących z tego węzła:
            
            \\[
            I_{wej} = I_{wyj}
            \\]
            
            Gdzie:
            - \\( I_{wej} \\): suma prądów wchodzących do węzła,
            - \\( I_{wyj} \\): suma prądów wychodzących z węzła.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Drugie prawo Kirchoffa (Zasada zachowania energii)
            Drugie prawo Kirchoffa, znane jako zasada oczka, mówi, że suma napięć wzdłuż dowolnej zamkniętej pętli w obwodzie elektrycznym jest równa zeru:
            
            \\[
            \\sum_{i=1}^{n} V_i = 0
            \\]
            
            Gdzie:
            - \\( V_i \\): napięcie na i-tym elemencie w obwodzie.
            
            To prawo wynika z zasady zachowania energii i oznacza, że energia dostarczona przez źródło napięcia jest równoważona przez straty energii w elementach pasywnych (np. rezystorach).
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Rozwiązywanie układów równań za pomocą praw Kirchoffa
            Po zastosowaniu pierwszego i drugiego prawa Kirchoffa do obwodów elektrycznych można uzyskać układy równań, które opisują zależności między prądami i napięciami w obwodzie.
            
            Te układy równań można rozwiązywać za pomocą różnych metod, takich jak:
            - Metoda podstawiania,
            - Metoda przeciwnych macierzy,
            - Metoda Gaussa.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Zastosowanie praw Kirchoffa w praktycznych obwodach elektrycznych
            Prawa Kirchoffa mają szerokie zastosowanie w analizie obwodów elektrycznych, takich jak:
            
            - **Obwody z wieloma źródłami napięcia**: umożliwiają obliczenie prądów i napięć w różnych częściach obwodu.
            - **Obwody z elementami pasywnymi**: pozwalają na określenie spadków napięcia na rezystorach, cewkach i kondensatorach.
            - **Analiza układów złożonych**: stosowanie praw Kirchoffa pozwala na analizę obwodów złożonych z wielu gałęzi, umożliwiając wyznaczenie wszystkich nieznanych parametrów obwodu.
            """;
}

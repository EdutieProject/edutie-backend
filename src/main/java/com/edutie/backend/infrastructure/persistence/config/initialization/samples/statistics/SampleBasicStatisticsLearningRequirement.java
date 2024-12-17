package com.edutie.backend.infrastructure.persistence.config.initialization.samples.statistics;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleBasicStatisticsLearningRequirement {
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
                "Uczeń zna podstawowe miary tendencji centralnej: średnią, medianę i modę.",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczyć podstawowe miary rozproszenia: rozstęp i wariancję.",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozumie różnicę między próbą a populacją oraz zna podstawowe metody losowania próby.",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi tworzyć i interpretować podstawowe wykresy statystyczne, takie jak histogramy i wykresy kołowe.",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna zastosowanie podstawowych pojęć statystyki w codziennym życiu, np. w analizie wyników badań lub ankiet.",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleBasicStatisticsLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Podstawy statystyki";
    public static final String SUB_REQUIREMENT_1 = """
            ### Miary tendencji centralnej
            Miary tendencji centralnej opisują, gdzie skupione są dane w zestawie:
            - **Średnia arytmetyczna**: suma wartości podzielona przez ich liczbę.
            - **Mediana**: środkowa wartość zestawu po jego uporządkowaniu.
            - **Moda**: najczęściej występująca wartość w danych.
            
            Przykład: W zestawie [2, 3, 3, 5, 7]:
            - Średnia = 4,
            - Mediana = 3,
            - Moda = 3.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Miary rozproszenia
            Miary rozproszenia pokazują, jak bardzo dane różnią się od siebie:
            - **Rozstęp**: różnica między największą i najmniejszą wartością.
            - **Wariancja**: średnia kwadratów odchyleń od średniej.
            
            Przykład: W zestawie [3, 5, 7]:
            - Rozstęp = 7 - 3 = 4,
            - Wariancja = \\(\\frac{(3-5)^2 + (5-5)^2 + (7-5)^2}{3} = 2.67\\).
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Próba a populacja
            - **Populacja**: Cały zbiór elementów, które badamy (np. wszyscy uczniowie w szkole).
            - **Próba**: Podzbiór populacji używany do badania (np. 100 losowo wybranych uczniów).
            
            **Metody losowania próby**:
            - Proste losowe (każdy ma równe szanse bycia wybranym),
            - Warstwowe (populacja podzielona na grupy, z każdej wybiera się próbki).
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Podstawowe wykresy statystyczne
            - **Histogram**: Graficzna reprezentacja rozkładu danych w przedziałach.
            - **Wykres kołowy**: Przedstawia udział procentowy kategorii w całości.
            
            Przykład: Wyniki ankiety na temat ulubionych kolorów można przedstawić jako wykres kołowy.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Statystyka w codziennym życiu
            Statystyka znajduje zastosowanie w:
            - **Badaniach naukowych**: Analiza danych eksperymentalnych.
            - **Marketingu**: Ocena skuteczności kampanii reklamowych.
            - **Planowaniu miejskim**: Prognozy demograficzne i analiza ruchu drogowego.
            
            Dzięki statystyce możemy podejmować lepsze decyzje oparte na danych.
            """;
}

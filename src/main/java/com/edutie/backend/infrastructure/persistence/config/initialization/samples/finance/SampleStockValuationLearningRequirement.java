package com.edutie.backend.infrastructure.persistence.config.initialization.samples.finance;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleStockValuationLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Wstęp do wyceny akcji",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Wycena na podstawie wartości księgowej",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Metoda zdyskontowanych przepływów pieniężnych (DCF)",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Wycena na podstawie zysków i mnożników",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Model Gordon Growth (Model wzrostu Gordona)",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Metoda porównawcza",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Wycena przy użyciu wskaźnika P/E",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Wycena przy użyciu wskaźnika P/BV",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleStockValuationLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Wycena akcji";

    public static final String SUB_REQUIREMENT_1 = """
            ### Wstęp do wyceny akcji
            Wycena akcji jest procesem określania wartości spółki lub jej akcji na podstawie różnych metod. 
            Celem wyceny jest ustalenie, czy akcje są niedowartościowane, przewartościowane czy też rynkowa cena 
            odpowiada ich rzeczywistej wartości. Istnieje wiele różnych metod wyceny akcji, które różnią się w zależności 
            od przyjętych założeń i dostępnych danych.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Wycena na podstawie wartości księgowej
            Wycena na podstawie wartości księgowej polega na obliczeniu wartości akcji jako stosunku wartości 
            księgowej aktywów netto firmy do liczby jej wyemitowanych akcji. Jest to jedna z prostszych metod wyceny, 
            która jest jednak bardziej użyteczna w przypadku firm o stabilnym majątku i niewielkich perspektywach wzrostu.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Metoda zdyskontowanych przepływów pieniężnych (DCF)
            Metoda DCF opiera się na prognozowaniu przyszłych przepływów pieniężnych, które firma generuje, 
            i ich dyskontowaniu do wartości bieżącej. Ta metoda pozwala na wycenę akcji na podstawie rzeczywistej 
            wartości przyszłych zysków, uwzględniając ryzyko oraz czas. Jest to jedna z najdokładniejszych metod wyceny, 
            ale wymaga dokładnych prognoz finansowych.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Wycena na podstawie zysków i mnożników
            Wycena na podstawie zysków i mnożników polega na użyciu wskaźników, takich jak zysk na akcję (EPS) 
            lub stosunek ceny do zysku (P/E), w celu porównania firmy z innymi spółkami o podobnym profilu. 
            Ta metoda jest szybka, ale może być mniej precyzyjna, ponieważ opiera się na założeniu, że porównywane 
            firmy są podobne pod względem ryzyka i wzrostu.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Model Gordon Growth (Model wzrostu Gordona)
            Model Gordona to metoda wyceny, która zakłada stały, niezmienny wzrost dywidend w przyszłości. 
            Wartość akcji jest obliczana jako stosunek dywidendy na akcję do różnicy między stopą zwrotu a stopą 
            wzrostu dywidendy. Ta metoda jest stosowana w przypadku spółek wypłacających stabilne dywidendy.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Metoda porównawcza
            Metoda porównawcza polega na porównaniu wycenianej spółki z innymi spółkami na podstawie różnych wskaźników, 
            takich jak P/E, P/BV czy EV/EBITDA. Dzięki tej metodzie inwestorzy mogą szybko ocenić, jak spółka wypada 
            na tle konkurencji, co może pomóc w podejmowaniu decyzji inwestycyjnych.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Wycena przy użyciu wskaźnika P/E
            Wskaźnik P/E (Price-to-Earnings) jest stosunkiem ceny akcji do zysku na akcję (EPS). Jest to jeden z 
            najczęściej używanych wskaźników w wycenie akcji. Wartość P/E wskazuje, jak wiele inwestorzy są gotowi 
            zapłacić za każdą jednostkę zysku, co pomaga w ocenie, czy akcje są przewartościowane czy niedowartościowane.
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Wycena przy użyciu wskaźnika P/BV
            Wskaźnik P/BV (Price-to-Book Value) jest stosunkiem ceny akcji do wartości księgowej na akcję. 
            Używa się go głównie do wyceny firm, które posiadają duże aktywa, takie jak nieruchomości czy maszyny. 
            Wskaźnik ten pozwala ocenić, jak rynek wycenia aktywa firmy w porównaniu do ich wartości księgowej.
            """;
}

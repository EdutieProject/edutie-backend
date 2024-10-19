package com.edutie.backend.infrastucture.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;

import java.util.UUID;

public class SampleSetsLearningRequirement {

    public static final String LEARNING_REQUIREMENT_NAME = "Operacje na zbiorach";

    public static LearningRequirement getLearningRequirement(Educator educator) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi sprawnie posługiwać się symboliką matematyczną dotyczącą zbiorów",
                PromptFragment.of("""
                        Uczeń musi znać i rozumieć podstawowe symbole matematyczne dotyczące zbiorów oraz umieć stosować je w zadaniach. Kluczowe operacje to suma, iloczyn, różnica zbiorów oraz dopełnienie zbioru. Ważne jest również zrozumienie relacji zawierania.
                        
                        ### Symbole:
                        1. \\( A \\cup B \\) – suma zbiorów \\( A \\) i \\( B \\) (wszystkie elementy, które należą do \\( A \\), \\( B \\), lub obu).
                        2. \\( A \\cap B \\) – iloczyn zbiorów \\( A \\) i \\( B \\) (elementy, które należą do obu zbiorów).
                        3. \\( A \\setminus B \\) – różnica zbiorów \\( A \\) i \\( B \\) (elementy, które należą do \\( A \\), ale nie do \\( B \\)).
                        4. \\( \\overline{A} \\) – dopełnienie zbioru \\( A \\) (wszystkie elementy, które nie należą do \\( A \\)).
                        5. \\( A \\subseteq B \\) – zbiór \\( A \\) jest podzbiorem zbioru \\( B \\) (wszystkie elementy \\( A \\) należą do \\( B \\)).
                        
                        ### Przykład 1:
                        Dla zbiorów \\( A = \\{1, 2, 3, 4\\} \\) i \\( B = \\{3, 4, 5, 6\\} \\), znajdź:
                        1. \\( A \\cup B \\)
                        2. \\( A \\cap B \\)
                        3. \\( A \\setminus B \\)
                        
                        Rozwiązania:
                        1. \\( A \\cup B = \\{1, 2, 3, 4, 5, 6\\} \\)
                        2. \\( A \\cap B = \\{3, 4\\} \\)
                        3. \\( A \\setminus B = \\{1, 2\\} \\)
                        
                        ### Przykład 2:
                        Dla zbioru \\( A = \\{x \\in \\mathbb{Z} : x \\geq 5 \\} \\) i uniwersum \\( U = \\{x \\in \\mathbb{Z} : x \\geq 0 \\text{ i } x \\leq 10\\} \\), znajdź dopełnienie \\( \\overline{A} \\).
                        
                        Rozwiązanie:
                        \\( \\overline{A} = \\{x \\in U : x < 5 \\} = \\{0, 1, 2, 3, 4\\} \\).
                        """)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi wyznaczać sumy, iloczyny i różnice więcej niż dwóch zbiorów",
                PromptFragment.of("""
                        Uczeń musi rozumieć, jak operować na więcej niż dwóch zbiorach, stosując sumę, iloczyn oraz różnicę. Ważne jest, by znał hierarchię działań oraz umiał poprawnie interpretować nawiasy, jeśli są obecne.
                        
                        ### Symbole:
                        1. \\( A \\cup B \\cup C \\) – suma trzech zbiorów (elementy należące do \\( A \\), \\( B \\) lub \\( C \\)).
                        2. \\( A \\cap B \\cap C \\) – iloczyn trzech zbiorów (elementy wspólne dla \\( A \\), \\( B \\) i \\( C \\)).
                        3. \\( A \\setminus (B \\cup C) \\) – różnica zbioru \\( A \\) z sumą zbiorów \\( B \\) i \\( C \\) (elementy, które należą do \\( A \\), ale nie do \\( B \\) ani \\( C \\)).
                        
                        ### Przykład 1:
                        Dla zbiorów \\( A = \\{1, 2, 3\\} \\), \\( B = \\{2, 3, 4\\} \\) i \\( C = \\{3, 4, 5\\} \\), znajdź:
                        1. \\( A \\cup B \\cup C \\)
                        2. \\( A \\cap B \\cap C \\)
                        
                        Rozwiązania:
                        1. \\( A \\cup B \\cup C = \\{1, 2, 3, 4, 5\\} \\)
                        2. \\( A \\cap B \\cap C = \\{3\\} \\)
                        
                        ### Przykład 2:
                        Dla zbiorów \\( A = \\{1, 2, 3, 4\\} \\), \\( B = \\{2, 4, 6\\} \\) i \\( C = \\{1, 4, 5\\} \\), znajdź:
                        1. \\( A \\setminus (B \\cup C) \\)
                        
                        Rozwiązanie:
                        1. \\( B \\cup C = \\{1, 2, 4, 5, 6\\} \\)
                        2. \\( A \\setminus (B \\cup C) = \\{3\\} \\)
                        """)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi podać przykłady zbiorów A i B, jeśli podana jest ich suma, różnica lub iloczyn",
                PromptFragment.of("""
                        Uczeń musi rozumieć, jak na podstawie podanej sumy, różnicy lub iloczynu zbiorów \\( A \\) i \\( B \\) znaleźć przykłady tych zbiorów. Ważne jest, aby rozumiał zależności między zbiorami oraz poprawnie definiował ich elementy.
                        
                        ### Przykład 1 (suma):
                        Jeśli \\( A \\cup B = \\{1, 2, 3, 4\\} \\) i \\( A = \\{1, 2\\} \\), to \\( B \\) może być:
                        - \\( B = \\{3, 4\\} \\) (brak wspólnych elementów),
                        - lub \\( B = \\{2, 3, 4\\} \\) (wspólny element \\( 2 \\)).
                        
                        ### Przykład 2 (iloczyn):
                        Jeśli \\( A \\cap B = \\{2, 3\\} \\) i \\( A = \\{1, 2, 3\\} \\), to \\( B \\) może być:
                        - \\( B = \\{2, 3, 4\\} \\) (elementy wspólne to \\( 2, 3 \\)).
                        
                        ### Przykład 3 (różnica):
                        Jeśli \\( A \\setminus B = \\{1, 2\\} \\) i \\( A = \\{1, 2, 3\\} \\), to \\( B \\) może być:
                        - \\( B = \\{3\\} \\) (pozostały element \\( 3 \\) jest w \\( B \\)).
                        """)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zna pojęcie dopełnienia zbioru I potrafi zastosować je w działaniach na zbiorach",
                PromptFragment.of("""
                        Uczeń musi rozumieć, że dopełnienie zbioru to zbiór wszystkich elementów, które nie należą do danego zbioru, ale należą do ustalonego uniwersum. Ważne jest, by umiał operować dopełnieniem w działaniach takich jak suma, iloczyn i różnica zbiorów.
                        
                        ### Symbol:
                        \\( \\overline{A} \\) – dopełnienie zbioru \\( A \\).
                        
                        ### Przykład 1:
                        Dla uniwersum \\( U = \\{1, 2, 3, 4, 5, 6\\} \\) i \\( A = \\{1, 2, 3\\} \\), dopełnieniem zbioru \\( A \\) jest:
                        - \\( \\overline{A} = \\{4, 5, 6\\} \\).
                        
                        ### Przykład 2:
                        Dla \\( A = \\{2, 4, 6\\} \\) oraz \\( B = \\{1, 4, 5\\} \\) i uniwersum \\( U = \\{1, 2, 3, 4, 5, 6\\} \\), znajdź \\( \\overline{A \\cup B} \\).
                        
                        Rozwiązanie:
                        1. \\( A \\cup B = \\{1, 2, 4, 5, 6\\} \\),
                        2. \\( \\overline{A \\cup B} = \\{3\\} \\).
                        """)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi przeprowadzić proste dowody, w tym dowody „nie wprost\", dotyczące własności liczb rzeczywistych",
                PromptFragment.of("""
                        Uczeń musi rozumieć, jak przeprowadzać dowody dotyczące własności liczb rzeczywistych, w tym dowody "nie wprost". Dowód nie wprost polega na założeniu, że teza jest fałszywa, a następnie wykazaniu, że prowadzi to do sprzeczności.
                        
                        ### Przykład 1 (dowód bezpośredni):
                        Udowodnij, że suma dwóch liczb parzystych jest liczbą parzystą.
                        
                        **Rozwiązanie**:\s
                        Niech \\( a = 2k \\) oraz \\( b = 2m \\), gdzie \\( k \\) i \\( m \\) są liczbami całkowitymi. Wtedy:
                        \\[ a + b = 2k + 2m = 2(k + m) \\]
                        Suma \\( 2(k + m) \\) jest liczbą parzystą, więc suma dwóch liczb parzystych jest parzysta.
                        
                        ### Przykład 2 (dowód nie wprost):
                        Udowodnij, że nie istnieje najmniejsza liczba dodatnia.
                        
                        **Rozwiązanie**:
                        Załóżmy nie wprost, że istnieje najmniejsza liczba dodatnia, nazwana \\( x \\). Rozważmy liczbę \\( \\frac{x}{2} \\), która również jest dodatnia i mniejsza od \\( x \\). Sprzeczność! Zatem nie istnieje najmniejsza liczba dodatnia.
                        """)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi wyznaczyć dopełnienie zbioru liczbowego skończonego w przestrzeni R",
                PromptFragment.of("""
                        Uczeń musi rozumieć, jak przeprowadzać dowody dotyczące własności liczb rzeczywistych, w tym dowody "nie wprost". Dowód nie wprost polega na założeniu, że teza jest fałszywa, a następnie wykazaniu, że prowadzi to do sprzeczności.
                        
                        ### Przykład 1 (dowód bezpośredni):
                        Udowodnij, że suma dwóch liczb parzystych jest liczbą parzystą.
                        
                        **Rozwiązanie**:\s
                        Niech \\( a = 2k \\) oraz \\( b = 2m \\), gdzie \\( k \\) i \\( m \\) są liczbami całkowitymi. Wtedy:
                        \\[ a + b = 2k + 2m = 2(k + m) \\]
                        Suma \\( 2(k + m) \\) jest liczbą parzystą, więc suma dwóch liczb parzystych jest parzysta.
                        
                        ### Przykład 2 (dowód nie wprost):
                        Udowodnij, że nie istnieje najmniejsza liczba dodatnia.
                        
                        **Rozwiązanie**:
                        Załóżmy nie wprost, że istnieje najmniejsza liczba dodatnia, nazwana \\( x \\). Rozważmy liczbę \\( \\frac{x}{2} \\), która również jest dodatnia i mniejsza od \\( x \\). Sprzeczność! Zatem nie istnieje najmniejsza liczba dodatnia.
                        """)
        );
        return learningRequirement;
    }

}

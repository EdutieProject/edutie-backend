package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;

import java.util.UUID;

public class SampleModulusLearningRequirement {

    public static LearningRequirement getLearningRequirement(Educator educator) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId(UUID.fromString("3dcf1a7d-d9ea-4e9b-becb-af730841056f")));
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Uczeń zna definicję wartości bezwzględnej liczby rzeczywistej i jej interpretację geometryczną",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczyć wartość bezwzględną liczby",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń umie zapisać i obliczyć odległość na osi liczbowej między dwoma dowolnymi punktami",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń zaznacza na osi liczbowej liczby o danej wartości bezwzględnej",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń rozwiązuje proste równania z wartością bezwzględną typu |x-a| = b",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi zaznaczyć na osi liczbowej zbiory opisane za pomocą równań i nierówności z wartością bezwzględną typu: | x - a | = b, | x - a | < b, | x - a | > b",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi uprościć wyrażenie z wartością bezwzględną dla zmiennej z danego przedziału",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi na podstawie zbioru rozwiązań nierówności z wartością bezwzględną zapisać tę nierówność",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        return learningRequirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Równania i nierówności z wartością bezwzględną";
     public static final String SUB_REQUIREMENT_1 = """
             ### Koncept teoretyczny: Wartość bezwzględna
                         \s
             **1. Definicja wartości bezwzględnej liczby rzeczywistej:**
             Wartość bezwzględna liczby rzeczywistej \\(x\\), oznaczana jako \\(|x|\\), to miara odległości tej liczby od zera na osi liczbowej, niezależnie od jej znaku. Formalnie:
                         \s
             \\[
             |x| =\s
             \\begin{cases}\s
             x, & \\text{gdy } x \\geq 0 \\\\
             -x, & \\text{gdy } x < 0
             \\end{cases}
             \\]
                         \s
             **2. Interpretacja geometryczna:**
             Wartość bezwzględna to odległość punktu reprezentującego liczbę \\(x\\) od punktu 0 na osi liczbowej. Niezależnie od tego, czy \\(x\\) jest dodatnie, czy ujemne, wartość bezwzględna opisuje zawsze **nieujemną** odległość.
                         \s
             #### Przykład:
             - Dla \\(x = 5\\), \\(|5| = 5\\), co oznacza, że punkt \\(x = 5\\) jest oddalony o 5 jednostek od zera.
             - Dla \\(x = -7\\), \\(|-7| = 7\\), co oznacza, że punkt \\(x = -7\\) jest oddalony o 7 jednostek od zera.
                         \s
             ### Dlaczego jest to ważne w nauczaniu?
             1. **Fundament matematyki**: Wartość bezwzględna to kluczowy koncept, który uczniowie muszą znać, aby rozumieć bardziej złożone zagadnienia, takie jak równania i nierówności z wartością bezwzględną, odległość między punktami na płaszczyźnie czy analizy w wyższej matematyce.
              \s
             2. **Odległość w geometrii**: Interpretacja geometryczna wartości bezwzględnej ułatwia rozumienie odległości między liczbami na osi liczbowej i innych koncepcji geometrycznych, np. odległości między punktami w przestrzeni (dwuwymiarowej i trójwymiarowej).
                         \s
             3. **Intuicja matematyczna**: Wartość bezwzględna rozwija intuicyjne rozumienie pojęcia „odległości” oraz tego, że odległość jest zawsze liczbą dodatnią lub równą zero.
                         \s
             Podsumowując, zrozumienie wartości bezwzględnej stanowi fundament w kształtowaniu logicznego myślenia matematycznego i intuicji geometrycznej.
            \s""";

    public static final String SUB_REQUIREMENT_2 = """
            Uczeń musi znać definicję wartości bezwzględnej liczby:
                       \s
            \\[
            |x| =\s
            \\begin{cases}\s
            x, & \\text{gdy } x \\geq 0 \\\\
            -x, & \\text{gdy } x < 0
            \\end{cases}
            \\]
                       \s
            Uczeń powinien rozumieć, że wartość bezwzględna to odległość liczby od zera, zawsze nieujemna. Musi umieć obliczać wartość bezwzględną liczb dodatnich, ujemnych i zera.
                       \s
            ### Przykłady:
                       \s
            1. \\(|7| = 7\\) (liczba dodatnia pozostaje bez zmian).
            2. \\(|-5| = 5\\) (liczba ujemna zmienia znak).
            3. \\(|0| = 0\\) (wartość bezwzględna zera to 0).
           \s""";

    public static final String SUB_REQUIREMENT_3 = """
            Uczeń musi rozumieć, że odległość między dwoma punktami na osi liczbowej jest równa wartości bezwzględnej różnicy tych punktów. Wzór na odległość między punktami \\(a\\) i \\(b\\) to:
                       \s
            \\[
            d = |a - b|
            \\]
                       \s
            lub
                       \s
            \\[
            d = |b - a|
            \\]
                       \s
            Odległość zawsze będzie nieujemna.
                       \s
            ### Przykłady:
                       \s
            1. Odległość między punktami \\(3\\) i \\(-2\\):
               \\[
               d = |3 - (-2)| = |3 + 2| = |5| = 5
               \\]
                       \s
            2. Odległość między punktami \\(-4\\) i \\(1\\):
               \\[
               d = |-4 - 1| = |-5| = 5
               \\]
           \s""";

    public static final String SUB_REQUIREMENT_4 = """
            Uczeń musi rozumieć, że liczby o tej samej wartości bezwzględnej mają taką samą odległość od zera na osi liczbowej, ale mogą leżeć po obu stronach osi. Dla liczby \\(a\\), liczby o wartości bezwzględnej \\(|x| = a\\) to \\(x = a\\) oraz \\(x = -a\\).
                       \s
            ### Przykłady:
                       \s
            1. Zaznacz liczby o wartości bezwzględnej \\(|x| = 4\\):
               - Liczby to \\(x = 4\\) i \\(x = -4\\).
               - Na osi liczbowej zaznacz punkty \\(4\\) i \\(-4\\).
                       \s
            2. Zaznacz liczby o wartości bezwzględnej \\(|x| = 7\\):
               - Liczby to \\(x = 7\\) i \\(x = -7\\).
               - Na osi liczbowej zaznacz punkty \\(7\\) i \\(-7\\).
           \s""";

    public static final String SUB_REQUIREMENT_5 = """
            Uczeń musi zrozumieć definicję wartości bezwzględnej oraz zasady jej stosowania w równaniach. Wartość bezwzględna liczby |x| oznacza odległość x od zera i zawsze jest liczbą nieujemną. W równaniach typu |x-a| = b, gdzie b ≥ 0, należy rozważyć dwa przypadki:
                       \s
            1. x - a = b
            2. x - a = -b
                       \s
            Przykład 1:
            Rozwiązanie równania |x - 2| = 4
                       \s
            a) x - 2 = 4
            x = 4 + 2
            x = 6
                       \s
            b) x - 2 = -4
            x = -4 + 2
            x = -2
                       \s
            Rozwiązania: x = 6 lub x = -2
                       \s
            Przykład 2:
            Rozwiązanie równania |x + 5| = 3
                       \s
            a) x + 5 = 3
            x = 3 - 5
            x = -2
                       \s
            b) x + 5 = -3
            x = -3 - 5
            x = -8
                       \s
            Rozwiązania: x = -2 lub x = -8
                       \s
            Uczeń powinien również znać, że w przypadku gdy b < 0, równanie nie ma rozwiązań, ponieważ wartość bezwzględna nie może być ujemna.
           \s""";

    public static final String SUB_REQUIREMENT_6 = """
            Uczeń powinien wiedzieć, jak zaznaczać zbiory na osi liczbowej dla równań i nierówności z wartością bezwzględną. Kluczowe jest zrozumienie interpretacji geometrystycznej wartości bezwzględnej oraz sposobu rozwiązywania poszczególnych przypadków.
                       \s
            ### Równania | x - a | = b
            To równanie oznacza, że x jest w odległości b od a. Są dwa przypadki do rozważenia:
                       \s
            1. **x - a = b**  →  **x = a + b**
            2. **x - a = -b**  →  **x = a - b**
                       \s
            Przykład:
            Rozwiązanie | x - 3 | = 2
            - x = 3 + 2 → x = 5
            - x = 3 - 2 → x = 1
                       \s
            Rozwiązania: {1, 5}
                       \s
            ### Nierówności | x - a | < b
            Oznacza, że x jest w odległości mniejszej niż b od a, co tworzy przedział otwarty.
                       \s
            Przykład:
            Rozwiązanie | x - 4 | < 3
            x - 4 < 3  →  x < 7
            x - 4 > -3  →  x > 1
                       \s
            Ostateczny zbiór: (1, 7)
                       \s
            ### Nierówności | x - a | > b
            Oznacza, że x jest w odległości większej niż b od a, co tworzy dwa przedziały.
                       \s
            Przykład:
            Rozwiązanie | x - 5 | > 1
            x - 5 > 1  →  x > 6
            x - 5 < -1  →  x < 4
                       \s
            Ostateczny zbiór: (-∞, 4) ∪ (6, ∞)
                       \s
            Uczeń powinien umieć zaznaczać te zbiory na osi liczbowej, a także wiedzieć, że w przypadku nierówności końcowych w zbiorach otwartych używamy okrągłych nawiasów, a w zamkniętych — kwadratowych.
           \s""";

    public static final String SUB_REQUIREMENT_7 = """
            Uczeń musi zrozumieć, jak wartość bezwzględna wpływa na uproszczenie wyrażeń, biorąc pod uwagę przedział, w którym znajduje się zmienna. Kluczowe jest rozpoznanie, czy argument wartości bezwzględnej jest dodatni, ujemny czy równy zero w danym przedziale.
                       \s
            ### Uproszczenie z wartością bezwzględną
                       \s
            1. **Jeśli \\( x \\geq a \\)**, to \\( |x - a| = x - a \\).
            2. **Jeśli \\( x < a \\)**, to \\( |x - a| = -(x - a) = a - x \\).
                       \s
            ### Przykład 1:
            Uproszczenie wyrażenia \\( |x - 2| + 3 \\) dla \\( x \\geq 2 \\).
                       \s
            **Uproszczenie:**
            \\( |x - 2| = x - 2 \\) \s
            Wyrażenie: \\( (x - 2) + 3 = x + 1 \\)
                       \s
            ### Przykład 2:
            Uproszczenie wyrażenia \\( |x + 1| - 4 \\) dla \\( x < -1 \\).
                       \s
            **Uproszczenie:**
            \\( |x + 1| = -(x + 1) = -x - 1 \\) \s
            Wyrażenie: \\( (-x - 1) - 4 = -x - 5 \\)
                       \s
            ### Przykład 3:
            Uproszczenie wyrażenia \\( |2x - 4| \\) dla \\( x < 2 \\).
                       \s
            **Uproszczenie:**
            \\( |2x - 4| = -(2x - 4) = -2x + 4 \\) \s
            To wyrażenie zostaje \\( -2x + 4 \\).
                       \s
            ### Podsumowanie:
            Uczeń powinien być w stanie zidentyfikować przedział, w którym znajduje się zmienna, a następnie zastosować odpowiednie zasady przekształcania wartości bezwzględnej w celu uproszczenia wyrażenia.
           \s""";

    public static final String SUB_REQUIREMENT_8 = """
            Uczeń musi umieć interpretować zbiory rozwiązań nierówności z wartością bezwzględną i na ich podstawie poprawnie zapisać odpowiadające im nierówności. Kluczowe jest zrozumienie, jak fragmenty zbiorów odnoszą się do wartości bezwzględnej.
                       \s
            ### Zasady zapisywania nierówności:
                       \s
            1. **Dla zbioru postaci \\( (a, b) \\)**:
               - Nierówność ma formę \\( |x - c| < d \\), co oznacza, że \\( c - d < x < c + d \\).
              \s
            2. **Dla zbioru postaci \\( (-\\infty, a) \\) lub \\( (b, +\\infty) \\)**:
               - Nierówność może mieć formę \\( |x - c| > d \\), co oznacza, że \\( x < c - d \\) lub \\( x > c + d \\).
                       \s
            ### Przykład 1:
            Zbiór rozwiązań: \\( (-3, 1) \\)
                       \s
            **Zapis nierówności:**
            Dla zbioru \\( |x - (-1)| < 2 \\):
            - Zapisujemy: \\( -1 - 2 < x < -1 + 2 \\) → \\( -3 < x < 1 \\).
                       \s
            ### Przykład 2:
            Zbiór rozwiązań: \\( (-\\infty, -2) \\) ∪ \\( (2, +\\infty) \\)
                       \s
            **Zapis nierówności:**
            Dla zbioru \\( |x| > 2 \\):
            - Zapisujemy: \\( x < -2 \\) lub \\( x > 2 \\).
                       \s
            ### Przykład 3:
            Zbiór rozwiązań: \\( [1, 4] \\)
                       \s
            **Zapis nierówności:**
            Dla zbioru \\( |x - 2.5| \\leq 1.5 \\):
            - Zapisujemy: \\( 2.5 - 1.5 \\leq x \\leq 2.5 + 1.5 \\) → \\( 1 \\leq x \\leq 4 \\).
                       \s
            Uczeń powinien umieć analizować zbiory, a następnie przekształcać je w odpowiednie nierówności, uwzględniając wartości bezwzględne.
           \s""";

}

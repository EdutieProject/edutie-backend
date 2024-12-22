package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleQuadraticFunctionLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        if (learningRequirementPersistence.getRepository().findAll().stream().anyMatch(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME))) {
            log.info("Learning requirement {} already present in the DB, omitting seeding", LEARNING_REQUIREMENT_NAME);
            requirement = learningRequirementPersistence.getRepository().findAll().stream().filter(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME)).findFirst().get();
            isSeeded = true;
            return;
        }

        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Związek między wzorem funkcji kwadratowej w postaci ogólnej, a wzorem funkcji kwadratowej w postaci kanonicznej",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Miejsce zerowe funkcji kwadratowej. Wzór funkcji kwadratowej w postaci iloczynowej",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Szkicowanie wykresów funkcji kwadratowych. Odczytywanie własności funkcji kwadratowej na podstawie wykresu",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Wyznaczanie wzoru funkcji kwadratowej na podstawie jej własności.",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Najmniejsza oraz największa wartość funkcji kwadratowej w przedziale domkniętym",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi algebraicznie rozwiązywać równania kwadratowe z jedną niewiadomą",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi graficznie rozwiązywać równania i nierówności kwadratowe z jedną niewiadomą",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi napisać wzór funkcji kwadratowej o zadanych własnościach\n",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleModulusLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Funkcja kwadratowa";
     public static final String SUB_REQUIREMENT_1 = """
             Uczeń musi znać dwa wzory funkcji kwadratowej:
                          
             1. **Postać ogólna**: \\( f(x) = ax^2 + bx + c \\)
             2. **Postać kanoniczna**: \\( f(x) = a(x - p)^2 + q \\)
                          
             Uczeń powinien rozumieć, jak przejść z postaci ogólnej do kanonicznej poprzez **zamianę wzoru**. Kluczowy proces to **wyłączenie współczynnika a przed nawias** i **zastosowanie wzoru na kwadrat różnicy**.
                          
             ### Przykład:
                          
             Dla funkcji kwadratowej w postaci ogólnej: \s
             \\( f(x) = 2x^2 + 8x + 5 \\)
                          
             1. Wyłączamy \\(a = 2\\) przed nawias: \s
                \\( f(x) = 2(x^2 + 4x) + 5 \\)
                          
             2. Stosujemy wzór na kwadrat sumy: \s
                \\( x^2 + 4x = (x + 2)^2 - 4 \\), więc: \s
                \\( f(x) = 2((x + 2)^2 - 4) + 5 \\)
                          
             3. Obliczamy: \s
                \\( f(x) = 2(x + 2)^2 - 8 + 5 \\)
                          
             4. Ostatecznie: \s
                \\( f(x) = 2(x + 2)^2 - 3 \\)
                          
             Postać kanoniczna: \\( f(x) = 2(x + 2)^2 - 3 \\).
             """;

    public static final String SUB_REQUIREMENT_2 = """
            Uczeń musi znać wzór funkcji kwadratowej w postaci **iloczynowej**:
            
            \\[
            f(x) = a(x - x_1)(x - x_2)
            \\]
            
            gdzie \\(x_1\\) i \\(x_2\\) to **miejsca zerowe** funkcji, czyli punkty, w których \\(f(x) = 0\\).
            
            Uczeń powinien umieć obliczać miejsca zerowe, korzystając z **równania kwadratowego**:
            
            \\[
            x = \\frac{-b \\pm \\sqrt{b^2 - 4ac}}{2a}
            \\]
            
            Miejsca zerowe pozwalają zapisać funkcję w postaci iloczynowej.
            
            ### Przykład:
            
            Dla funkcji kwadratowej w postaci ogólnej:
            
            \\[
            f(x) = 2x^2 - 4x - 6
            \\]
            
            1. Obliczamy **dyskryminant**:
            
            \\[
            \\Delta = (-4)^2 - 4 \\cdot 2 \\cdot (-6) = 16 + 48 = 64
            \\]
            
            2. Obliczamy miejsca zerowe:
            
            \\[
            x_1 = \\frac{-(-4) - \\sqrt{64}}{2 \\cdot 2} = \\frac{4 - 8}{4} = -1
            \\]
            \\[
            x_2 = \\frac{-(-4) + \\sqrt{64}}{2 \\cdot 2} = \\frac{4 + 8}{4} = 3
            \\]
            
            3. Zapisujemy funkcję w postaci iloczynowej:
            
            \\[
            f(x) = 2(x + 1)(x - 3)
            \\]
            """;

    public static final String SUB_REQUIREMENT_3 = """
            Uczeń musi umieć narysować wykres funkcji kwadratowej na podstawie wzoru i odczytać z niego podstawowe własności funkcji.
                 \s
                  ### Co uczeń powinien wiedzieć:
                 \s
                  1. **Wierzchołek funkcji**:
                     - Współrzędne wierzchołka \\(V(p, q)\\) odczytuje się z postaci kanonicznej \\(f(x) = a(x - p)^2 + q\\).
                     - Dla postaci ogólnej \\(f(x) = ax^2 + bx + c\\), współrzędne wierzchołka to:
                       \\[
                       p = \\frac{-b}{2a}, \\quad q = f(p)
                       \\]
                 \s
                  2. **Miejsca zerowe**:
                     - Odczytuje się z przecięć wykresu z osią \\(x\\), czyli rozwiązań równania \\(f(x) = 0\\).
                 \s
                  3. **Kierunek ramion paraboli**:
                     - Jeśli \\(a > 0\\), ramiona paraboli są skierowane w górę.
                     - Jeśli \\(a < 0\\), ramiona są skierowane w dół.
                 \s
                  4. **Przecięcie z osią \\(y\\)**:
                     - Punkt przecięcia to \\((0, c)\\), gdzie \\(c\\) to wyraz wolny w postaci ogólnej \\(f(x) = ax^2 + bx + c\\).
                 \s
                  ### Przykład:
                 \s
                  Dla funkcji \\(f(x) = -x^2 + 4x - 3\\):
                 \s
                  1. **Wierzchołek**: \s
                     \\(p = \\frac{-4}{2 \\cdot (-1)} = 2\\), \s
                     \\(q = f(2) = -(2)^2 + 4 \\cdot 2 - 3 = 1\\), \s
                     więc wierzchołek to \\(V(2, 1)\\).
                 \s
                  2. **Miejsca zerowe**: \s
                     Rozwiązujemy \\( -x^2 + 4x - 3 = 0\\): \s
                     \\[
                     \\Delta = 4^2 - 4 \\cdot (-1) \\cdot (-3) = 16 - 12 = 4
                     \\]
                     \\[
                     x_1 = 1, \\quad x_2 = 3
                     \\]
                 \s
                  3. **Przecięcie z osią \\(y\\)**: \s
                     \\(f(0) = -3\\), więc punkt przecięcia to \\((0, -3)\\).
           \s""";

    public static final String SUB_REQUIREMENT_4 = """
            Uczeń musi umieć wyznaczyć wzór funkcji kwadratowej na podstawie jej własności, takich jak miejsca zerowe, wierzchołek, czy punkt przecięcia z osią \\(y\\).
            
            ### Co uczeń powinien wiedzieć:
            
            1. **Miejsca zerowe**:
               - Jeśli są znane miejsca zerowe \\(x_1\\) i \\(x_2\\), funkcję można zapisać w postaci iloczynowej:
                 \\[
                 f(x) = a(x - x_1)(x - x_2)
                 \\]
               - Współczynnik \\(a\\) można wyznaczyć, jeśli znany jest dodatkowy punkt (np. punkt przecięcia z osią \\(y\\)).
            
            2. **Wierzchołek**:
               - Jeśli znany jest wierzchołek \\(V(p, q)\\), funkcję można zapisać w postaci kanonicznej:
                 \\[
                 f(x) = a(x - p)^2 + q
                 \\]
               - Współczynnik \\(a\\) wyznaczamy, znając dodatkowy punkt na wykresie.
            
            3. **Punkt przecięcia z osią \\(y\\)**:
               - W postaci ogólnej \\(f(x) = ax^2 + bx + c\\), wyraz wolny \\(c\\) to punkt przecięcia z osią \\(y\\).
            
            ### Przykład:
            
            Funkcja ma miejsca zerowe \\(x_1 = 1\\) i \\(x_2 = 5\\), oraz przechodzi przez punkt \\( (0, -2) \\).
            
            1. Zapisujemy funkcję w postaci iloczynowej:
               \\[
               f(x) = a(x - 1)(x - 5)
               \\]
            
            2. Podstawiamy punkt \\( (0, -2) \\), aby wyznaczyć \\(a\\):
               \\[
               -2 = a(0 - 1)(0 - 5)
               \\]
               \\[
               -2 = a(1)(5) \\quad \\Rightarrow \\quad a = \\frac{-2}{5}
               \\]
            
            3. Ostateczny wzór funkcji:
               \\[
               f(x) = \\frac{-2}{5}(x - 1)(x - 5)
               \\]
            """;

    public static final String SUB_REQUIREMENT_5 = """
            Uczeń musi umieć wyznaczyć najmniejszą i największą wartość funkcji kwadratowej w przedziale domkniętym, analizując wartości w wierzchołku oraz na końcach przedziału.
            
            ### Co uczeń powinien wiedzieć:
            
            1. **Wartość w wierzchołku paraboli**:
               - Współrzędne wierzchołka to \\(x_v = \\frac{-b}{2a}\\). Wartość funkcji w wierzchołku to \\(f(x_v)\\).
               - Jeśli \\(x_v\\) leży w przedziale, sprawdzamy \\(f(x_v)\\) jako potencjalne maksimum lub minimum.
            
            2. **Wartości na końcach przedziału**:
               - Obliczamy wartości funkcji w punktach krańcowych przedziału, czyli \\(f(x_1)\\) i \\(f(x_2)\\), gdzie \\( [x_1, x_2] \\) to przedział domknięty.
            
            3. **Porównanie wartości**:
               - Najmniejsza i największa wartość to odpowiednio minimum i maksimum spośród \\(f(x_1)\\), \\(f(x_2)\\) oraz \\(f(x_v)\\) (jeśli \\(x_v\\) leży w przedziale).
            
            ### Przykład:
            
            Dla funkcji \\(f(x) = 2x^2 - 4x + 1\\) wyznacz największą i najmniejszą wartość w przedziale \\([0, 3]\\).
            
            1. **Wierzchołek**:
               \\[
               x_v = \\frac{-(-4)}{2 \\cdot 2} = 1
               \\]
               \\[
               f(1) = 2(1)^2 - 4(1) + 1 = -1
               \\]
            
            2. **Wartości na końcach przedziału**:
               \\[
               f(0) = 2(0)^2 - 4(0) + 1 = 1
               \\]
               \\[
               f(3) = 2(3)^2 - 4(3) + 1 = 7
               \\]
            
            3. **Porównanie**:
               Najmniejsza wartość to \\(-1\\) (w wierzchołku \\(x = 1\\)), a największa wartość to \\(7\\) (dla \\(x = 3\\)).
            """;

    public static final String SUB_REQUIREMENT_6 = """
            Uczeń musi umieć rozwiązywać równania kwadratowe z jedną niewiadomą za pomocą wzoru kwadratowego, faktoryzacji lub wyłączania wspólnego czynnika.
                        
            ### Co uczeń powinien wiedzieć:
                        
            1. **Równanie kwadratowe** ma postać \\( ax^2 + bx + c = 0 \\), gdzie \\( a \\neq 0 \\).
                        
            2. **Wzór kwadratowy**:
               - Uczeń stosuje wzór:
                 \\[
                 x = \\frac{-b \\pm \\sqrt{b^2 - 4ac}}{2a}
                 \\]
               - **Dyskryminant** \\( \\Delta = b^2 - 4ac \\):
                 - Jeśli \\( \\Delta > 0 \\), są dwa rozwiązania.
                 - Jeśli \\( \\Delta = 0 \\), jest jedno rozwiązanie.
                 - Jeśli \\( \\Delta < 0 \\), brak rozwiązań rzeczywistych.
                        
            3. **Faktoryzacja**:
               - Jeśli równanie można przekształcić do postaci \\( a(x - x_1)(x - x_2) = 0 \\), uczeń rozwiązuje równanie, znajdując \\( x_1 \\) i \\( x_2 \\).
                        
            4. **Wyłączanie wspólnego czynnika**:
               - Jeśli równanie ma wspólny czynnik, np. \\( 2x(x - 3) = 0 \\), uczeń wyłącza wspólny czynnik i rozwiązuje równanie \\( 2x = 0 \\) i \\( x - 3 = 0 \\).
                        
            ### Przykład 1 (wzór kwadratowy):
                        
            Rozwiąż równanie \\( 2x^2 - 4x - 6 = 0 \\).
                        
            1. Obliczamy dyskryminant:
               \\[
               \\Delta = (-4)^2 - 4 \\cdot 2 \\cdot (-6) = 16 + 48 = 64
               \\]
                        
            2. Zastosowanie wzoru kwadratowego:
               \\[
               x_1 = \\frac{-(-4) - \\sqrt{64}}{2 \\cdot 2} = \\frac{4 - 8}{4} = -1
               \\]
               \\[
               x_2 = \\frac{-(-4) + \\sqrt{64}}{2 \\cdot 2} = \\frac{4 + 8}{4} = 3
               \\]
                        
            Rozwiązania: \\( x_1 = -1 \\), \\( x_2 = 3 \\).
                        
            ### Przykład 2 (faktoryzacja):
                        
            Rozwiąż równanie \\( x^2 - 9 = 0 \\).
                        
            1. Faktoryzacja:
               \\[
               (x - 3)(x + 3) = 0
               \\]
                        
            2. Rozwiązania:
               \\[
               x_1 = 3, \\quad x_2 = -3
               \\]
            """;

    public static final String SUB_REQUIREMENT_7 = """
            Uczeń musi umieć interpretować wykres funkcji kwadratowej (paraboli) oraz wykorzystywać go do rozwiązywania równań i nierówności kwadratowych.
            
            ### Równania kwadratowe:
            Równanie kwadratowe ma postać \\( ax^2 + bx + c = 0 \\). Graficznie rozwiązania równania to punkty przecięcia wykresu paraboli z osią \\(x\\) (miejsca zerowe).
            
            #### Przykład:
            Rozwiąż równanie \\( x^2 - 4 = 0 \\) graficznie.
            
            1. Wykres funkcji \\( y = x^2 - 4 \\) to parabola o wierzchołku w punkcie \\( (0, -4) \\).
            2. Parabola przecina oś \\( x \\) w punktach \\( x = -2 \\) i \\( x = 2 \\).
            3. Rozwiązaniem równania są miejsca zerowe: \\( x = -2 \\) i \\( x = 2 \\).
            
            ### Nierówności kwadratowe:
            Dla nierówności \\( ax^2 + bx + c > 0 \\) lub \\( ax^2 + bx + c < 0 \\), uczeń musi określić, w jakich przedziałach wykres paraboli leży nad osią \\( x \\) (dla nierówności \\( > 0 \\)) lub pod osią \\( x \\) (dla nierówności \\( < 0 \\)).
            
            #### Przykład:
            Rozwiąż nierówność \\( x^2 - 4 > 0 \\) graficznie.
            
            1. Narysuj wykres funkcji \\( y = x^2 - 4 \\).
            2. Widzimy, że parabola leży nad osią \\( x \\) dla \\( x < -2 \\) i \\( x > 2 \\).
            3. Zatem rozwiązaniem nierówności są przedziały \\( (-\\infty, -2) \\cup (2, \\infty) \\).
            """;

    public static final String SUB_REQUIREMENT_8 = """
            Uczeń musi znać różne postacie funkcji kwadratowej (ogólną, kanoniczną, iloczynową) i potrafić wyznaczyć jej wzór na podstawie zadanych własności, takich jak miejsca zerowe, wierzchołek lub punkty przez które przechodzi wykres.
            
            ### Postać ogólna funkcji kwadratowej:
            Wzór funkcji kwadratowej w postaci ogólnej to:
            \\[
            f(x) = ax^2 + bx + c
            \\]
            
            ### Postać kanoniczna:
            \\[
            f(x) = a(x - p)^2 + q
            \\]
            Gdzie \\( (p, q) \\) to współrzędne wierzchołka paraboli.
            
            ### Postać iloczynowa:
            \\[
            f(x) = a(x - x_1)(x - x_2)
            \\]
            Gdzie \\( x_1 \\) i \\( x_2 \\) to miejsca zerowe funkcji.
            
            ### Przykład 1:
            Napisz wzór funkcji kwadratowej o miejscach zerowych \\( x_1 = -1 \\) i \\( x_2 = 3 \\), która przechodzi przez punkt \\( (0, -6) \\).
            
            1. Korzystamy z postaci iloczynowej:
               \\[
               f(x) = a(x + 1)(x - 3)
               \\]
            
            2. Podstawiamy punkt \\( (0, -6) \\) w celu wyznaczenia \\( a \\):
               \\[
               -6 = a(0 + 1)(0 - 3)
               \\]
               \\[
               -6 = a(-3)
               \\]
               \\[
               a = 2
               \\]
            
            3. Ostateczny wzór funkcji:
               \\[
               f(x) = 2(x + 1)(x - 3)
               \\]
            
            ### Przykład 2:
            Napisz wzór funkcji kwadratowej o wierzchołku \\( (2, -3) \\) i współczynniku \\( a = 1 \\).
            
            1. Korzystamy z postaci kanonicznej:
               \\[
               f(x) = (x - 2)^2 - 3
               \\]
            
            Ostateczny wzór funkcji: \\( f(x) = (x - 2)^2 - 3 \\).
            """;

}

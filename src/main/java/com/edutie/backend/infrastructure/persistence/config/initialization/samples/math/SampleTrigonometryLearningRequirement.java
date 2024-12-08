package com.edutie.backend.infrastructure.persistence.config.initialization.samples.math;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleTrigonometryLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Uczeń zna definicje funkcji trygonometrycznych w trójkącie prostokątnym",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi obliczać wartości wyrażeń zawierających funkcje trygonometryczne kątów o miarach 30\", 45\", 60•; ",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "uczeń potrafi obliczyć wartości funkcji trygonometrycznych kąta ostrego w trójkącie prostokątnym o danych długościach boków; ",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "uczeń potrafi obliczyć wartości pozostałych funkcji trygonometrycznych kąta wypukłego, gdy dona jest jedno z nich; ",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń umie zbudować w układzie współrzędnych dowolny kąt o mierze a, gdy dana jest wartość jednej funkcji trygonometrycznej tego kąta; ",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi przeprowadzać dowody tożsamości trygonometrycznych",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Uczeń potrafi uprszaczać wyrażenia zawierające funkcje trygonometryczne",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "potrafi stosować podstawowe tożsamości trygonometryczne dla dowolnego kąta, dla którego funkcje trygonometryczne są określone",
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


    private static String LEARNING_REQUIREMENT_NAME = "Trygonometria";

    private static String SUB_REQUIREMENT_1 = """
            Uczeń musi znać definicje funkcji trygonometrycznych: sinus, cosinus i tangens w odniesieniu do trójkąta prostokątnego.
            
            ### Co uczeń powinien wiedzieć:
            
            1. **Sinus** kąta \\( \\alpha \\):
               \\[
               \\sin \\alpha = \\frac{\\text{przeciwprostokątna}}{\\text{przyprostokątna naprzeciw kąta} \\alpha}
               \\]
            
            2. **Cosinus** kąta \\( \\alpha \\):
               \\[
               \\cos \\alpha = \\frac{\\text{przeciwprostokątna}}{\\text{przyprostokątna przyległa do kąta} \\alpha}
               \\]
            
            3. **Tangens** kąta \\( \\alpha \\):
               \\[
               \\tan \\alpha = \\frac{\\text{przyprostokątna naprzeciw kąta} \\alpha}{\\text{przyprostokątna przyległa do kąta} \\alpha}
               \\]
            
            ### Przykład:
            
            Dla trójkąta prostokątnego o kącie \\( \\alpha = 30^\\circ \\), gdzie przeciwprostokątna ma długość 10, a przyprostokątna naprzeciw kąta \\( \\alpha \\) ma długość 5:
            
            1. **Sinus**:
               \\[
               \\sin 30^\\circ = \\frac{5}{10} = 0,5
               \\]
            
            2. **Cosinus**:
               \\[
               \\cos 30^\\circ = \\frac{\\sqrt{75}}{10} \\approx 0,866
               \\]
            
            3. **Tangens**:
               \\[
               \\tan 30^\\circ = \\frac{5}{\\sqrt{75}} \\approx 0,577
               \\]
            """;

    private static String SUB_REQUIREMENT_2 = """
            Uczeń musi znać wartości funkcji trygonometrycznych dla kątów \\(30^\\circ\\), \\(45^\\circ\\) i \\(60^\\circ\\) oraz potrafić je wykorzystywać w obliczeniach.
            
            ### Wartości funkcji trygonometrycznych:
            
            1. **Dla kąta \\(30^\\circ\\)**:
               \\[
               \\sin 30^\\circ = \\frac{1}{2}, \\quad \\cos 30^\\circ = \\frac{\\sqrt{3}}{2}, \\quad \\tan 30^\\circ = \\frac{1}{\\sqrt{3}} \\approx 0{,}577
               \\]
            
            2. **Dla kąta \\(45^\\circ\\)**:
               \\[
               \\sin 45^\\circ = \\frac{\\sqrt{2}}{2}, \\quad \\cos 45^\\circ = \\frac{\\sqrt{2}}{2}, \\quad \\tan 45^\\circ = 1
               \\]
            
            3. **Dla kąta \\(60^\\circ\\)**:
               \\[
               \\sin 60^\\circ = \\frac{\\sqrt{3}}{2}, \\quad \\cos 60^\\circ = \\frac{1}{2}, \\quad \\tan 60^\\circ = \\sqrt{3} \\approx 1{,}732
               \\]
            
            ### Przykład:
            
            Oblicz wyrażenie:
            \\[
            2 \\sin 30^\\circ + 3 \\cos 45^\\circ - \\tan 60^\\circ
            \\]
            
            Rozwiązanie:
            
            1. Podstawiamy wartości funkcji trygonometrycznych:
               \\[
               2 \\cdot \\frac{1}{2} + 3 \\cdot \\frac{\\sqrt{2}}{2} - \\sqrt{3}
               \\]
            
            2. Obliczamy:
               \\[
               1 + \\frac{3\\sqrt{2}}{2} - 1{,}732 \\approx 1 + 2{,}121 - 1{,}732 = 1{,}389
               \\]
            """;

    private static String SUB_REQUIREMENT_3 = """
            Uczeń musi umieć obliczyć wartości funkcji trygonometrycznych (sinus, cosinus, tangens) dla kąta ostrego w trójkącie prostokątnym, znając długości boków trójkąta: przyprostokątnych i przeciwprostokątnej.
            
            ### Co uczeń powinien wiedzieć:
            
            1. **Sinus** kąta \\( \\alpha \\):
               \\[
               \\sin \\alpha = \\frac{\\text{przyprostokątna naprzeciw kąta} \\alpha}{\\text{przeciwprostokątna}}
               \\]
            
            2. **Cosinus** kąta \\( \\alpha \\):
               \\[
               \\cos \\alpha = \\frac{\\text{przyprostokątna przyległa do kąta} \\alpha}{\\text{przeciwprostokątna}}
               \\]
            
            3. **Tangens** kąta \\( \\alpha \\):
               \\[
               \\tan \\alpha = \\frac{\\text{przyprostokątna naprzeciw kąta} \\alpha}{\\text{przyprostokątna przyległa do kąta} \\alpha}
               \\]
            
            ### Przykład:
            
            W trójkącie prostokątnym przeciwprostokątna ma długość \\(10\\), przyprostokątna naprzeciw kąta \\( \\alpha \\) ma długość \\(6\\), a druga przyprostokątna ma długość \\(8\\). Oblicz wartości funkcji trygonometrycznych dla kąta \\( \\alpha \\).
            
            1. **Sinus**:
               \\[
               \\sin \\alpha = \\frac{6}{10} = 0,6
               \\]
            
            2. **Cosinus**:
               \\[
               \\cos \\alpha = \\frac{8}{10} = 0,8
               \\]
            
            3. **Tangens**:
               \\[
               \\tan \\alpha = \\frac{6}{8} = 0,75
               \\]
            """;

    private static String SUB_REQUIREMENT_4 = """
            Uczeń musi umieć obliczać wartości pozostałych funkcji trygonometrycznych kąta wypukłego, korzystając z jednej danej funkcji trygonometrycznej oraz odpowiednich tożsamości trygonometrycznych.
            
            ### Co uczeń powinien wiedzieć:
            
            1. **Tożsamości trygonometryczne**:
               - \\( \\sin^2 \\alpha + \\cos^2 \\alpha = 1 \\)
               - \\( 1 + \\tan^2 \\alpha = \\frac{1}{\\cos^2 \\alpha} \\)
               - \\( \\tan \\alpha = \\frac{\\sin \\alpha}{\\cos \\alpha} \\)
            
            2. **Symetria funkcji trygonometrycznych dla kątów wypukłych**:
               - Dla kąta \\(180^\\circ - \\alpha\\), wartości trygonometryczne mogą przyjmować odpowiednie znaki w zależności od ćwiartki układu współrzędnych.
            
            ### Przykład:
            
            Dana jest wartość \\( \\sin \\alpha = \\frac{3}{5} \\) i kąt \\( \\alpha \\) leży w II ćwiartce. Oblicz \\( \\cos \\alpha \\) i \\( \\tan \\alpha \\).
            
            1. Obliczamy \\( \\cos \\alpha \\) z tożsamości:
               \\[
               \\sin^2 \\alpha + \\cos^2 \\alpha = 1
               \\]
               \\[
               \\left( \\frac{3}{5} \\right)^2 + \\cos^2 \\alpha = 1
               \\]
               \\[
               \\frac{9}{25} + \\cos^2 \\alpha = 1
               \\]
               \\[
               \\cos^2 \\alpha = \\frac{16}{25}, \\quad \\cos \\alpha = -\\frac{4}{5} \\quad \\text{(w II ćwiartce \\( \\cos \\) jest ujemny)}
               \\]
            
            2. Obliczamy \\( \\tan \\alpha \\):
               \\[
               \\tan \\alpha = \\frac{\\sin \\alpha}{\\cos \\alpha} = \\frac{\\frac{3}{5}}{-\\frac{4}{5}} = -\\frac{3}{4}
               \\]
            
            Odpowiedź: \\( \\cos \\alpha = -\\frac{4}{5} \\), \\( \\tan \\alpha = -\\frac{3}{4} \\).
            """;

    private static String SUB_REQUIREMENT_5 = """
            Uczeń musi umieć zbudować kąt o mierze \\( \\alpha \\) w układzie współrzędnych, korzystając z wartości danej funkcji trygonometrycznej oraz wiedzy o znakach funkcji w różnych ćwiartkach.
            
            ### Co uczeń powinien wiedzieć:
            
            1. **Znaki funkcji trygonometrycznych w ćwiartkach**:
               - I ćwiartka: \\( \\sin \\alpha > 0, \\cos \\alpha > 0, \\tan \\alpha > 0 \\)
               - II ćwiartka: \\( \\sin \\alpha > 0, \\cos \\alpha < 0, \\tan \\alpha < 0 \\)
               - III ćwiartka: \\( \\sin \\alpha < 0, \\cos \\alpha < 0, \\tan \\alpha > 0 \\)
               - IV ćwiartka: \\( \\sin \\alpha < 0, \\cos \\alpha > 0, \\tan \\alpha < 0 \\)
            
            2. **Budowanie kąta**:
               Uczeń musi wiedzieć, jak z wartości danej funkcji wybrać odpowiednią ćwiartkę i zbudować kąt, korzystając z odległości od środka układu współrzędnych (długości boków).
            
            ### Przykład:
            
            Dana jest wartość \\( \\sin \\alpha = \\frac{3}{5} \\) i wiadomo, że \\( \\alpha \\) znajduje się w II ćwiartce. Zbuduj kąt \\( \\alpha \\) w układzie współrzędnych.
            
            1. Z \\( \\sin \\alpha = \\frac{3}{5} \\), znając odległość przyprostokątnej naprzeciw kąta \\( \\alpha \\), możemy obliczyć \\( \\cos \\alpha \\):
               \\[
               \\sin^2 \\alpha + \\cos^2 \\alpha = 1
               \\]
               \\[
               \\cos^2 \\alpha = 1 - \\left( \\frac{3}{5} \\right)^2 = 1 - \\frac{9}{25} = \\frac{16}{25}
               \\]
               \\[
               \\cos \\alpha = -\\frac{4}{5} \\quad \\text{(w II ćwiartce \\( \\cos \\alpha \\) jest ujemny)}
               \\]
            
            2. W układzie współrzędnych rysujemy trójkąt prostokątny, gdzie przyprostokątne mają długości \\( 3 \\) i \\( 4 \\), a przeciwprostokątna \\( 5 \\), umieszczając kąt w II ćwiartce.
            """;

    private static String SUB_REQUIREMENT_6 = """
            Uczeń musi znać podstawowe tożsamości trygonometryczne i umieć przekształcać wyrażenia w celu udowodnienia równości, korzystając z właściwości funkcji trygonometrycznych oraz algebraicznych operacji.
            
            ### Kluczowe tożsamości trygonometryczne:
            1. \\( \\sin^2 \\alpha + \\cos^2 \\alpha = 1 \\)
            2. \\( 1 + \\tan^2 \\alpha = \\frac{1}{\\cos^2 \\alpha} \\)
            3. \\( \\tan \\alpha = \\frac{\\sin \\alpha}{\\cos \\alpha} \\)
            4. \\( \\cot \\alpha = \\frac{1}{\\tan \\alpha} \\)
            
            ### Przykład dowodu:
            
            Udowodnij tożsamość:
            \\[
            1 - \\sin^2 \\alpha = \\cos^2 \\alpha
            \\]
            
            Rozwiązanie:
            
            1. Zauważamy, że możemy skorzystać z podstawowej tożsamości trygonometrycznej:
               \\[
               \\sin^2 \\alpha + \\cos^2 \\alpha = 1
               \\]
            
            2. Przekształcamy powyższą tożsamość:
               \\[
               \\cos^2 \\alpha = 1 - \\sin^2 \\alpha
               \\]
            
            3. To dowodzi równości:
               \\[
               1 - \\sin^2 \\alpha = \\cos^2 \\alpha
               \\]
            
            ### Inny przykład:
            
            Udowodnij tożsamość:
            \\[
            \\frac{1 - \\cos 2\\alpha}{\\sin 2\\alpha} = \\tan \\alpha
            \\]
            
            Rozwiązanie:
            
            1. Zastosujmy wzory redukcyjne:
               \\[
               \\cos 2\\alpha = \\cos^2 \\alpha - \\sin^2 \\alpha
               \\]
               oraz
               \\[
               \\sin 2\\alpha = 2 \\sin \\alpha \\cos \\alpha
               \\]
            
            2. Przekształcamy lewą stronę wyrażenia:
               \\[
               \\frac{1 - (\\cos^2 \\alpha - \\sin^2 \\alpha)}{2 \\sin \\alpha \\cos \\alpha} = \\frac{(1 - \\cos^2 \\alpha + \\sin^2 \\alpha)}{2 \\sin \\alpha \\cos \\alpha}
               \\]
            
            3. Korzystamy z tożsamości \\(1 - \\cos^2 \\alpha = \\sin^2 \\alpha\\):
               \\[
               \\frac{2 \\sin^2 \\alpha}{2 \\sin \\alpha \\cos \\alpha} = \\frac{\\sin \\alpha}{\\cos \\alpha} = \\tan \\alpha
               \\]
            
            Odpowiedź: tożsamość została udowodniona.
            """;

    private static String SUB_REQUIREMENT_7 = """
            Uczeń musi znać podstawowe tożsamości trygonometryczne oraz umieć stosować je w celu upraszczania wyrażeń. Kluczowe jest również rozumienie zależności między funkcjami trygonometrycznymi, takimi jak \\( \\sin \\), \\( \\cos \\), \\( \\tan \\), \\( \\cot \\) oraz ich właściwości.
            
            ### Tożsamości trygonometryczne:
            1. \\( \\sin^2 \\alpha + \\cos^2 \\alpha = 1 \\)
            2. \\( 1 + \\tan^2 \\alpha = \\frac{1}{\\cos^2 \\alpha} \\)
            3. \\( \\tan \\alpha = \\frac{\\sin \\alpha}{\\cos \\alpha} \\)
            4. \\( \\cot \\alpha = \\frac{1}{\\tan \\alpha} \\)
            
            ### Przykład 1:
            Uprość wyrażenie \\( \\frac{\\sin^2 \\alpha}{1 - \\cos^2 \\alpha} \\).
            
            1. Zauważamy, że \\( 1 - \\cos^2 \\alpha = \\sin^2 \\alpha \\) (z podstawowej tożsamości trygonometrycznej).
            2. Podstawiamy:
               \\[
               \\frac{\\sin^2 \\alpha}{\\sin^2 \\alpha} = 1
               \\]
            Rozwiązanie: \\( 1 \\).
            
            ### Przykład 2:
            Uprość wyrażenie \\( \\frac{\\tan \\alpha}{\\sec \\alpha} \\).
            
            1. Przekształcamy \\( \\sec \\alpha \\) na \\( \\frac{1}{\\cos \\alpha} \\):
               \\[
               \\frac{\\tan \\alpha}{\\sec \\alpha} = \\frac{\\frac{\\sin \\alpha}{\\cos \\alpha}}{\\frac{1}{\\cos \\alpha}} = \\sin \\alpha
               \\]
            
            Rozwiązanie: \\( \\sin \\alpha \\).
            
            ### Przykład 3:
            Uprość wyrażenie \\( \\frac{1}{\\cot \\alpha} + \\frac{1}{\\tan \\alpha} \\).
            
            1. Zauważamy, że \\( \\cot \\alpha = \\frac{1}{\\tan \\alpha} \\), więc:
               \\[
               \\frac{1}{\\cot \\alpha} + \\frac{1}{\\tan \\alpha} = \\tan \\alpha + \\tan \\alpha = 2 \\tan \\alpha
               \\]
            
            Rozwiązanie: \\( 2 \\tan \\alpha \\).
            """;

    private static String SUB_REQUIREMENT_8 = """
            Uczeń musi znać i stosować podstawowe tożsamości trygonometryczne dla dowolnych kątów, dla których funkcje trygonometryczne są określone, czyli kątów z różnych ćwiartek układu współrzędnych. Ważne jest rozumienie, jak poszczególne funkcje trygonometryczne przyjmują różne znaki w zależności od ćwiartki.
            
            ### Kluczowe tożsamości:
            1. \\( \\sin^2 \\alpha + \\cos^2 \\alpha = 1 \\)
            2. \\( \\tan \\alpha = \\frac{\\sin \\alpha}{\\cos \\alpha} \\)
            3. \\( 1 + \\tan^2 \\alpha = \\frac{1}{\\cos^2 \\alpha} \\)
            4. \\( \\cot \\alpha = \\frac{1}{\\tan \\alpha} \\)
            
            ### Przykład 1:
            Dla kąta \\( \\alpha = 120^\\circ \\), wyznacz \\( \\sin \\alpha \\), \\( \\cos \\alpha \\), \\( \\tan \\alpha \\) i sprawdź, czy spełniają tożsamość \\( \\sin^2 \\alpha + \\cos^2 \\alpha = 1 \\).
            
            Rozwiązanie:
            1. \\( \\alpha = 120^\\circ \\) leży w II ćwiartce, więc:
               - \\( \\sin 120^\\circ = \\frac{\\sqrt{3}}{2} \\)
               - \\( \\cos 120^\\circ = -\\frac{1}{2} \\)
               - \\( \\tan 120^\\circ = -\\sqrt{3} \\)
              \s
            2. Sprawdzamy tożsamość:
               \\[
               \\sin^2 120^\\circ + \\cos^2 120^\\circ = \\left( \\frac{\\sqrt{3}}{2} \\right)^2 + \\left( -\\frac{1}{2} \\right)^2 = \\frac{3}{4} + \\frac{1}{4} = 1
               \\]
            
            Tożsamość jest spełniona.
            
            ### Przykład 2:
            Dla kąta \\( \\alpha = 225^\\circ \\), oblicz \\( \\cot \\alpha \\), jeśli \\( \\sin \\alpha = -\\frac{\\sqrt{2}}{2} \\) i \\( \\cos \\alpha = -\\frac{\\sqrt{2}}{2} \\).
            
            Rozwiązanie:
            1. \\( \\cot \\alpha = \\frac{\\cos \\alpha}{\\sin \\alpha} = \\frac{-\\frac{\\sqrt{2}}{2}}{-\\frac{\\sqrt{2}}{2}} = 1 \\)
            
            Rozwiązanie: \\( \\cot 225^\\circ = 1 \\).
            """;
}

package com.edutie.backend.infrastucture.persistence.config;

public class SampleLearningResourceDefinitionData {
    public static final String LEARNING_REQUIREMENT_NAME = "Równania i nierówności z wartością bezwzględną";
     public static final String LEARNING_REQUIREMENT_DESCRIPTION = """
             ### Wartość bezwzględna — definicja
             Wartość bezwzględna liczby realnej to odległość tej liczby od zera na osi liczbowej. Oznaczana jest symbolem \\(|x|\\). Formalnie:
                          
             \\[
             |x| =\s
             \\begin{cases}\s
             x, & \\text{gdy } x \\geq 0 \\\\
             -x, & \\text{gdy } x < 0\s
             \\end{cases}
             \\]
                          
             Przykłady:
             - \\(|3| = 3\\)
             - \\(|-5| = 5\\)
             - \\(|0| = 0\\)
                          
             ### Równania z wartością bezwzględną
                          
             Równania z wartością bezwzględną mają postać:
                          
             \\[
             |f(x)| = a
             \\]
                          
             gdzie \\(a \\geq 0\\), a \\(f(x)\\) jest pewną funkcją, najczęściej liniową.
                          
             #### Przykład 1: \\( |x| = 3 \\)
                          
             To równanie oznacza, że liczba \\(x\\) jest oddalona od zera o 3 jednostki na osi liczbowej. Rozwiązanie jest następujące:
                          
             \\[
             x = 3 \\quad \\text{lub} \\quad x = -3
             \\]
                          
             Rozwiązania to: \\( x = 3 \\) oraz \\( x = -3 \\).
                          
             #### Przykład 2: \\( |2x - 1| = 5 \\)
                          
             1. Dla \\(2x - 1 = 5\\):
                          
             \\[
             2x = 6 \\quad \\Rightarrow \\quad x = 3
             \\]
                          
             2. Dla \\(2x - 1 = -5\\):
                          
             \\[
             2x = -4 \\quad \\Rightarrow \\quad x = -2
             \\]
                          
             Rozwiązania to: \\(x = 3\\) oraz \\(x = -2\\).
                          
             ### Nierówności z wartością bezwzględną
                          
             Nierówności z wartością bezwzględną dzielą się na dwa główne typy:
                          
             1. **Nierówności typu \\(|f(x)| \\leq a\\)**:
               \s
                Nierówność \\(|f(x)| \\leq a\\) oznacza, że odległość \\(f(x)\\) od zera jest mniejsza lub równa \\(a\\), co prowadzi do układu nierówności:
               \s
                \\[
                -a \\leq f(x) \\leq a
                \\]
               \s
                Przykład: \\( |x - 1| \\leq 3 \\)
                          
                \\[
                -3 \\leq x - 1 \\leq 3
                \\]
               \s
                Dodajemy 1 do każdej strony:
                          
                \\[
                -2 \\leq x \\leq 4
                \\]
                          
                Rozwiązaniem jest przedział \\(x \\in [-2, 4]\\).
                          
             2. **Nierówności typu \\(|f(x)| \\geq a\\)**:
               \s
                Nierówność \\(|f(x)| \\geq a\\) oznacza, że odległość \\(f(x)\\) od zera jest większa lub równa \\(a\\), co prowadzi do dwóch nierówności:
               \s
                \\[
                f(x) \\geq a \\quad \\text{lub} \\quad f(x) \\leq -a
                \\]
               \s
                Przykład: \\( |x + 2| \\geq 5 \\)
                          
                1. Dla \\(x + 2 \\geq 5\\):
                          
                \\[
                x \\geq 3
                \\]
                          
                2. Dla \\(x + 2 \\leq -5\\):
                          
                \\[
                x \\leq -7
                \\]
                          
                Rozwiązaniem jest: \\(x \\in (-\\infty, -7] \\cup [3, \\infty)\\).
                          
             ### Graficzna interpretacja
                          
             1. **Równania**: Rozwiązanie równania \\(|x| = a\\) oznacza, że \\(x\\) znajduje się dokładnie w odległości \\(a\\) od zera. Graficznie są to dwa punkty na osi liczbowej, \\(x = -a\\) oraz \\(x = a\\).
                          
             2. **Nierówności**:
                - **Nierówność \\(|x| \\leq a\\)**: rozwiązaniem są wszystkie punkty między \\(-a\\) a \\(a\\), czyli przedział \\([-a, a]\\).
                - **Nierówność \\(|x| \\geq a\\)**: rozwiązaniem są wszystkie punkty, których odległość od zera jest większa lub równa \\(a\\), czyli zbiór \\((-\\infty, -a] \\cup [a, \\infty)\\).
                          
             ### Podsumowanie
                          
             - Równania z wartością bezwzględną sprowadzają się do dwóch równań bez wartości bezwzględnej (dla wartości dodatniej i ujemnej).
             - Nierówności z wartością bezwzględną prowadzą do układów nierówności, które trzeba rozwiązać, dzieląc je na przypadki.
             - Graficznie wartość bezwzględna to odległość na osi liczbowej, co ułatwia interpretację rozwiązań równania lub nierówności.
                          
             Wartość bezwzględna to pojęcie kluczowe, które znajduje zastosowanie nie tylko w rozwiązywaniu równań i nierówności, ale także w wielu innych dziedzinach matematyki, takich jak analiza matematyczna czy geometria.
             """;



}

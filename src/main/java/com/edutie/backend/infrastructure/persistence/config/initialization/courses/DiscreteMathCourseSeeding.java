package com.edutie.backend.infrastructure.persistence.config.initialization.courses;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.backend.infrastructure.persistence.config.initialization.Seeding;
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.math.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DiscreteMathCourseSeeding {
    private final LessonPersistence lessonPersistence;
    private final SegmentPersistence segmentPersistence;
    private final LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;

    private Segment seedSegment(Lesson lesson, Seeding.SeededSegmentDetails details) {
        Segment segment = Segment.create(lesson.getAuthorEducator(), lesson);
        segment.setName(details.segmentName());
        segment.setSnippetDescription(details.segmentDescription());
        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                lesson.getAuthorEducator(),
                PromptFragment.of(details.learningResourceDefinitionTheoryOverview()),
                PromptFragment.of(details.learningResourceDefinitionExerciseOverview()),
                details.learningRequirements()
        );
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();
        segment.setLearningResourceDefinitionId(learningResourceDefinition.getId());
        segmentPersistence.save(segment).throwIfFailure();
        return segment;
    }

    private Segment seedSegment(Segment previousSegment, Seeding.SeededSegmentDetails details) {
        Segment segment = Segment.create(previousSegment.getAuthorEducator(), previousSegment);
        segment.setName(details.segmentName());
        segment.setSnippetDescription(details.segmentDescription());
        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                previousSegment.getAuthorEducator(),
                PromptFragment.of(details.learningResourceDefinitionTheoryOverview()),
                PromptFragment.of(details.learningResourceDefinitionExerciseOverview()),
                details.learningRequirements()
        );
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();
        segment.setLearningResourceDefinitionId(learningResourceDefinition.getId());
        segmentPersistence.save(segment).throwIfFailure();
        return segment;
    }

    public void discreteMathCourseSeeding(Course course) {
        seedDiscreteMathCourse(course);
    }
    private void seedDiscreteMathCourse(Course course) {
        Lesson firstLesson = Lesson.create(course.getAuthorEducator(), course);
        firstLesson.setName("Permutacje i kombinacje");
        lessonPersistence.save(firstLesson);
        seedSegmentsInPermutationsAndCombinations(firstLesson);


        Lesson secondLesson = Lesson.create(course.getAuthorEducator(), firstLesson);
        secondLesson.setName("Wielomiany Newtona");
        lessonPersistence.save(secondLesson);
        seedSegmentsInNewtonPolynomials(secondLesson);

        Lesson thirdLesson = Lesson.create(course.getAuthorEducator(), secondLesson);
        thirdLesson.setName("Diagramy Ferresa");
        lessonPersistence.save(thirdLesson);
        seedSegmentsInFerrersDiagrams(thirdLesson);

        Lesson fourthLesson = Lesson.create(course.getAuthorEducator(), secondLesson);
        fourthLesson.setName("Relacje równoważności");
        lessonPersistence.save(fourthLesson);
        seedSegmentsInEquivalenceRelations(fourthLesson);

        Lesson fifthLesson = Lesson.create(course.getAuthorEducator(), fourthLesson);
        fifthLesson.setName("Podziały zbiorów");
        lessonPersistence.save(fifthLesson);
        seedSegmentsInSetPartitions(fifthLesson);

        Lesson sixthLesson = Lesson.create(course.getAuthorEducator(), secondLesson);
        sixthLesson.setName("Równania diofantyczne");
        lessonPersistence.save(sixthLesson);
        seedSegmentsInDiophantineEquations(sixthLesson);

        Lesson seventhLesson = Lesson.create(course.getAuthorEducator(), sixthLesson);
        seventhLesson.setName("Liczba Bella i funkcje tworzące");
        lessonPersistence.save(seventhLesson);
        seedSegmentsInBellNumbersAndGeneratingFunctions(seventhLesson);
    }

    private void seedSegmentsInPermutationsAndCombinations(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Permutacje i kombinacje bez powtórzeń",
                "Dowiedz się, jak obliczać permutacje i kombinacje bez powtórzeń.",
                "Wytłumacz wzory na permutacje i kombinacje, używając przykładów jak układanie osób w kolejce.",
                "Kierujesz zespołem w parku rozrywki i musisz ustalić, w ilu różnych kolejnościach mogą ustawić się dzieci do nowej atrakcji.",
                Set.of(SamplePermutationsAndCombinationsLearningRequirement.getLearningRequirement())));

        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Permutacje i kombinacje z powtórzeniami",
                "Poznaj różnice w permutacjach i kombinacjach z powtórzeniami.",
                "Wytłumacz wzory na permutacje z powtórzeniami i kombinacje z powtórzeniami na przykładzie kul w pudełkach.",
                "Organizujesz festiwal balonów i musisz ustalić, na ile sposobów można rozdać kolorowe balony dzieciom, mając kilka balonów w tych samych kolorach.",
                Set.of(SamplePermutationsAndCombinationsLearningRequirement.getLearningRequirement(), SampleNewtonPolynomialsLearningRequirement.getLearningRequirement())));

        // Nowy segment dotyczący permutacji i kombinacji z powtórzeniami
        Segment third = seedSegment(second, new Seeding.SeededSegmentDetails(
                "Permutacje i kombinacje z powtórzeniami",
                "Poznaj różnice w permutacjach i kombinacjach z powtórzeniami.",
                "Wytłumacz, jak obliczać permutacje z powtórzeniami i kombinacje z powtórzeniami w kontekście wyboru grup z ograniczoną liczbą elementów.",
                "Organizujesz konkurs, w którym dzieci wybierają balony w określonej liczbie kolorów. Oblicz, na ile sposobów mogą je wybrać.",
                Set.of(SamplePermutationsAndCombinationsLearningRequirement.getLearningRequirement(), SampleSetsWithRepetitionsLearningRequirement.getLearningRequirement()))
        );
    }

    private void seedSegmentsInNewtonPolynomials(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Wielomiany Newtona i ich zastosowania",
                "Dowiedz się, czym są wielomiany Newtona i jak je stosować.",
                "Wyjaśnij, jak wielomiany Newtona pomagają w obliczaniu liczby sposobów rozmieszczania elementów.",
                "Jesteś architektem i musisz obliczyć, ile sposobów można zbudować wieże z dostępnych cegieł w różnych kolorach.",
                Set.of(SampleNewtonPolynomialsLearningRequirement.getLearningRequirement())));

        // Nowy segment dotyczący wielomianów Newtona w kontekście finansów
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Wielomiany Newtona w zastosowaniach finansowych",
                "Dowiedz się, jak wielomiany Newtona mogą pomóc w rozwiązywaniu problemów finansowych.",
                "Wytłumacz, jak wielomiany Newtona są wykorzystywane do obliczania możliwych rozwiązań w podziałach funduszy.",
                "Jako doradca finansowy musisz pomóc klientowi w podziale jego inwestycji na kilka części, zachowując równowagę między wydatkami.",
                Set.of(SampleNewtonPolynomialsLearningRequirement.getLearningRequirement(), SampleDiophantineEquationLearningRequirement.getLearningRequirement()))
        );
    }

    private void seedSegmentsInSetPartitions(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Podziały zbiorów na bloki",
                "Dowiedz się, czym są podziały zbiorów i jak je obliczać.",
                "Wyjaśnij pojęcie liczb Stirlinga oraz ich zastosowania w podziałach zbiorów.",
                "Zostałeś wybrany na lidera obozu i musisz podzielić dzieci na grupy, aby każda grupa mogła wykonać inne zadanie obozowe.",
                Set.of(SampleSetsWithRepetitionsLearningRequirement.getLearningRequirement())));

        // Nowy segment dotyczący liczb Stirlinga
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Liczby Stirlinga w podziałach zbiorów",
                "Poznaj liczby Stirlinga i ich zastosowanie w problemach z podziałami zbiorów na grupy.",
                "Wytłumacz, jak liczby Stirlinga pomagają obliczyć liczbę sposobów podziału zbioru na określoną liczbę niepustych podzbiorów.",
                "Zostałeś liderem drużyny w grze terenowej. Musisz podzielić uczestników na drużyny, aby każda z nich wykonała różne zadania.",
                Set.of(SampleSetsWithRepetitionsLearningRequirement.getLearningRequirement(), SampleEquivalenceRelationsLearningRequirement.getLearningRequirement()))
        );
    }

    private void seedSegmentsInEquivalenceRelations(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Definicja relacji równoważności",
                "Poznaj definicję relacji równoważności oraz jej właściwości.",
                "Wyjaśnij, jak relacje równoważności tworzą podziały zbiorów.",
                "Jesteś nauczycielem i musisz pogrupować uczniów według ich wyników w testach, aby stworzyć równoważne grupy uczniów.",
                Set.of(SampleEquivalenceRelationsLearningRequirement.getLearningRequirement())));

        // Nowy segment dotyczący relacji równoważności w kontekście grupowania uczniów
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Relacje równoważności i podziały zbiorów",
                "Poznaj, jak relacje równoważności pomagają w tworzeniu podziałów zbiorów na grupy.",
                "Wyjaśnij, jak każda relacja równoważności wprowadza podział zbioru na disjunktne klasy.",
                "Jako analityk musisz stworzyć zestaw klas równoważności dla danych uczestników w zadaniach grupowych.",
                Set.of(SampleEquivalenceRelationsLearningRequirement.getLearningRequirement(), SampleSetsWithRepetitionsLearningRequirement.getLearningRequirement()))
        );
    }

    private void seedSegmentsInFerrersDiagrams(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Podstawy diagramów Ferresa",
                "Dowiedz się, czym są diagramy Ferresa i jak je tworzyć.",
                "Wytłumacz, jak wizualizować podziały liczb przy pomocy diagramów Ferresa.",
                "Jako księgowy musisz rozłożyć budżet firmy na mniejsze części, używając diagramów Ferresa do analizy podziałów liczbowych.",
                Set.of(SampleFerrersDiagramLearningRequirement.getLearningRequirement())));

        // Nowy segment dotyczący diagramów Ferresa w analizie liczb całkowitych
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Diagramy Ferresa w analizie liczb całkowitych",
                "Poznaj zastosowanie diagramów Ferresa w analizie liczb całkowitych.",
                "Wytłumacz, jak diagramy Ferresa mogą pomóc w rozwiązywaniu problemów związanych z podziałem liczb.",
                "Jako badacz teorii liczb musisz przeanalizować różne podziały liczb przy użyciu diagramów Ferresa.",
                Set.of(SampleFerrersDiagramLearningRequirement.getLearningRequirement(), SampleDiophantineEquationLearningRequirement.getLearningRequirement()))
        );
    }

    private void seedSegmentsInDiophantineEquations(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Wprowadzenie do równań diofantycznych",
                "Dowiedz się, czym są równania diofantyczne i jakie mają zastosowania.",
                "Wyjaśnij metody rozwiązywania równań diofantycznych, takich jak metoda rozkładu na czynniki.",
                "Jesteś matematykiem, który pomaga rolnikom rozwiązać problem podziału pól na określoną liczbę obszarów z równą liczbą nasion.",
                Set.of(SampleDiophantineEquationLearningRequirement.getLearningRequirement())));

        // Nowy segment dotyczący równań diofantycznych w finansach
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Równania diofantyczne w finansach",
                "Dowiedz się, jak równania diofantyczne mogą pomóc w rozwiązywaniu problemów finansowych.",
                "Wytłumacz, jak równania diofantyczne mogą służyć do optymalizacji podziału zasobów w finansach.",
                "Jako analityk finansowy musisz rozwiązać problem podziału zasobów, aby jak najlepiej wykorzystać dostępne środki.",
                Set.of(SampleDiophantineEquationLearningRequirement.getLearningRequirement(), SampleNewtonPolynomialsLearningRequirement.getLearningRequirement()))
        );
    }

    private void seedSegmentsInBellNumbersAndGeneratingFunctions(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Liczba Bella i podziały zbiorów",
                "Dowiedz się, czym jest liczba Bella i jak jest związana z podziałami zbiorów.",
                "Wytłumacz, jak liczba Bella pomaga obliczyć liczbę wszystkich możliwych podziałów zbioru.",
                "Prowadzisz księgarnię i musisz podzielić różne książki na kategorie. Sprawdź, na ile sposobów możesz podzielić zbiór książek.",
                Set.of(SampleBellNumbersLearningRequirement.getLearningRequirement())));

        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Funkcje tworzące i ich zastosowania",
                "Poznaj funkcje tworzące jako narzędzie do rozwiązywania problemów kombinatorycznych.",
                "Wyjaśnij, jak funkcje tworzące pozwalają analizować liczby kombinacji i rozwiązywać złożone zadania matematyczne.",
                "Zostałeś strategiem w grze planszowej. Musisz obliczyć wszystkie możliwe ścieżki ruchu na planszy, korzystając z funkcji tworzącej.",
                Set.of(SampleGeneratingFunctionsLearningRequirement.getLearningRequirement()))
        );
    }


}

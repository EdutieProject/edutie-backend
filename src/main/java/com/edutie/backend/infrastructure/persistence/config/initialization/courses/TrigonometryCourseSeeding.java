package com.edutie.backend.infrastructure.persistence.config.initialization.courses;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
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
public class TrigonometryCourseSeeding {
    private final LessonPersistence lessonPersistence;
    private final SegmentPersistence segmentPersistence;
    private final LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;

    private Segment seedSegment(Lesson lesson, Seeding.SeededSegmentDetails details) {
        Segment segment = Segment.create(lesson.getAuthorEducator(), lesson);
        segment.setName(details.segmentName());
        segment.setSnippetDescription(details.segmentDescription());
        StaticLearningResourceDefinition staticLearningResourceDefinition = StaticLearningResourceDefinition.create(
                lesson.getAuthorEducator(),
                PromptFragment.of(details.learningResourceDefinitionTheoryOverview()),
                PromptFragment.of(details.learningResourceDefinitionExerciseOverview()),
                details.learningRequirements()
        );
        learningResourceDefinitionPersistence.save(staticLearningResourceDefinition).throwIfFailure();
        segment.setLearningResourceDefinitionId(staticLearningResourceDefinition.getId());
        segmentPersistence.save(segment).throwIfFailure();
        return segment;
    }

    private Segment seedSegment(Segment previousSegment, Seeding.SeededSegmentDetails details) {
        Segment segment = Segment.create(previousSegment.getAuthorEducator(), previousSegment);
        segment.setName(details.segmentName());
        segment.setSnippetDescription(details.segmentDescription());
        StaticLearningResourceDefinition staticLearningResourceDefinition = StaticLearningResourceDefinition.create(
                previousSegment.getAuthorEducator(),
                PromptFragment.of(details.learningResourceDefinitionTheoryOverview()),
                PromptFragment.of(details.learningResourceDefinitionExerciseOverview()),
                details.learningRequirements()
        );
        learningResourceDefinitionPersistence.save(staticLearningResourceDefinition).throwIfFailure();
        segment.setLearningResourceDefinitionId(staticLearningResourceDefinition.getId());
        segmentPersistence.save(segment).throwIfFailure();
        return segment;
    }

    public void trigonometryCourseSeeding(Course course) {
        seedSampleLessonsInCourse(course);
    }

    private void seedSampleLessonsInCourse(Course course) {
        Lesson firstLesson = Lesson.create(course.getAuthorEducator(), course);
        firstLesson.setName("Podstawy trygonometrii");
        lessonPersistence.save(firstLesson);
        seedSegmentsInFirstLesson(firstLesson);

        Lesson secondLesson = Lesson.create(course.getAuthorEducator(), firstLesson);
        secondLesson.setName("Zaawansowane tożsamości trygonometryczne");
        lessonPersistence.save(secondLesson);
        seedSegmentsInSecondLesson(secondLesson);

        Lesson thirdLesson = Lesson.create(course.getAuthorEducator(), firstLesson);
        thirdLesson.setName("Trygonometria w geometrii i fizyce");
        lessonPersistence.save(thirdLesson);
        seedSegmentsInThirdLesson(thirdLesson);

        Lesson fourthLesson = Lesson.create(course.getAuthorEducator(), thirdLesson);
        fourthLesson.setName("Zastosowania trygonometrii w praktyce");
        lessonPersistence.save(fourthLesson);
        seedSegmentsInFourthLesson(fourthLesson);

        Lesson fifthLesson = Lesson.create(course.getAuthorEducator(), fourthLesson);
        fifthLesson.setName("Trygonometria w analizie algebraicznej");
        lessonPersistence.save(fifthLesson);
        seedSegmentsInFifthLesson(fifthLesson);

        Lesson sixthLesson = Lesson.create(course.getAuthorEducator(), fourthLesson);
        sixthLesson.setName("Trygonometria w informatyce i technologii");
        lessonPersistence.save(sixthLesson);
        seedSegmentsInSixthLesson(sixthLesson);
    }

    private void seedSegmentsInFirstLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Definicje funkcji trygonometrycznych",
                "Dowiedz się, czym są sinus, cosinus, tangens i cotangens.",
                "Wprowadzenie do definicji funkcji trygonometrycznych na podstawie trójkąta prostokątnego.",
                "Pewien architekt musi zaprojektować most, wykorzystując kąt nachylenia. Pomóż mu określić proporcje, które zadecydują o wytrzymałości konstrukcji.",
                Set.of(SampleBasicTrigonometryLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Relacje pomiędzy funkcjami trygonometrycznymi",
                "Poznaj relacje między funkcjami trygonometrycznymi, które pozwalają na bardziej zaawansowane obliczenia.",
                "Opis relacji między funkcjami oraz ich wykorzystania w praktycznych obliczeniach.",
                "Statek kosmiczny manewruje między planetami. Użyj właściwości funkcji trygonometrycznych, aby określić optymalną trajektorię.",
                Set.of(SampleBasicTrigonometryLearningRequirement.getLearningRequirement(), SampleTrigonometricRelationsLearningRequirement.getLearningRequirement())
        ));
        Segment third = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Jednostkowe koło trygonometryczne",
                "Zrozumienie jednostkowego koła jako narzędzia do wizualizacji funkcji trygonometrycznych.",
                "Użycie jednostkowego koła do wyznaczania wartości funkcji dla dowolnych kątów.",
                "Astronom obserwuje niebo i potrzebuje pomocy w przewidywaniu położenia gwiazd na podstawie ich współrzędnych.",
                Set.of(SampleBasicTrigonometryLearningRequirement.getLearningRequirement(), SampleUnitCircleLearningRequirement.getLearningRequirement())
        ));
        Segment fourth = seedSegment(third, new Seeding.SeededSegmentDetails(
                "Funkcje odwrotne trygonometryczne",
                "Poznaj funkcje odwrotne do sinusa, cosinusa i tangensa.",
                "Opis zastosowań funkcji odwrotnych w obliczeniach kątów.",
                "Inżynier potrzebuje obliczyć kąty w trójkącie na podstawie jego boków. Pomóż mu za pomocą funkcji odwrotnych.",
                Set.of(SampleTrigonometricRelationsLearningRequirement.getLearningRequirement(), SampleLawOfSinesLearningRequirement.getLearningRequirement())
        ));
    }

    private void seedSegmentsInSecondLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Tożsamości podstawowe",
                "Przypomnienie najważniejszych tożsamości, które pomagają w upraszczaniu złożonych wyrażeń matematycznych.",
                "Opis zastosowań tożsamości w upraszczaniu wyrażeń matematycznych.",
                "Inżynier projektuje systemy hydrauliczne w oparciu o zmieniające się kąty rur. Twoim zadaniem jest pomóc mu określić wymagane parametry.",
                Set.of(SampleTrigonometricIdentitiesLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Tożsamości iloczynowe",
                "Poznaj wzory, które przekształcają sumy sinusów i cosinusów w iloczyny.",
                "Opis przykładów praktycznych zastosowań wzorów iloczynowych w fizyce.",
                "Muzyk eksperymentuje z różnymi dźwiękami w celu uzyskania harmonii. Pomóż mu wyjaśnić, jak fale dźwiękowe oddziałują na siebie.",
                Set.of(SampleTrigonometricIdentitiesLearningRequirement.getLearningRequirement(), SampleUnitCircleLearningRequirement.getLearningRequirement())
        ));
        Segment third = seedSegment(second, new Seeding.SeededSegmentDetails(
                "Tożsamości zaawansowane",
                "Poznaj bardziej skomplikowane zależności w trygonometrii.",
                "Opis wykorzystania zaawansowanych tożsamości w rozwiązywaniu równań trygonometrycznych.",
                "Naukowiec analizuje okresowość fal elektromagnetycznych. Pomóż mu rozwiązać równanie opisujące interferencję fal.",
                Set.of(SampleTrigonometricIdentitiesLearningRequirement.getLearningRequirement())
        ));
    }

    private void seedSegmentsInThirdLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Rozwiązywanie trójkątów",
                "Zastosowanie twierdzenia sinusów i cosinusów do rozwiązywania trójkątów.",
                "Opis, jak obliczać boki i kąty dowolnych trójkątów przy użyciu funkcji trygonometrycznych.",
                "Ekspedycja w góry wymaga określenia długości ścieżki między dwoma punktami na podstawie widocznych kątów.",
                Set.of(SampleLawOfSinesLearningRequirement.getLearningRequirement(), SampleLawOfCosinesLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Trygonometria w praktyce",
                "Zastosowanie trygonometrii w rozwiązywaniu problemów rzeczywistych.",
                "Opis, jak trygonometria jest wykorzystywana w geodezji, architekturze, inżynierii.",
                "Geodeta musi zmierzyć wysokość góry, używając kąta widzenia. Pomóż mu obliczyć wysokość na podstawie odległości i kąta.",
                Set.of(SampleBasicTrigonometryLearningRequirement.getLearningRequirement(), SampleLawOfSinesLearningRequirement.getLearningRequirement())
        ));
    }

    private void seedSegmentsInFourthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Geometria w rzeczywistości",
                "Zastosowanie trygonometrii do rozwiązywania problemów rzeczywistych.",
                "Opis zastosowań w geodezji, nawigacji i architekturze.",
                "Geodeta musi zmierzyć wysokość góry, używając kąta widzenia. Pomóż mu obliczyć wysokość na podstawie odległości i kąta.",
                Set.of(SampleBasicTrigonometryLearningRequirement.getLearningRequirement(), SampleLawOfSinesLearningRequirement.getLearningRequirement())
        ));
    }

    private void seedSegmentsInFifthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Trygonometria i algebra",
                "Zastosowanie trygonometrii w analizie funkcji kwadratowych.",
                "Opis rozwiązania równań kwadratowych przy użyciu trygonometrii.",
                "Pomóż inżynierowi obliczyć wytrzymałość konstrukcji, wykorzystując równania kwadratowe i funkcje trygonometryczne.",
                Set.of(SampleQuadraticFunctionLearningRequirement.getLearningRequirement(), SampleTrigonometricRelationsLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Rozwiązywanie równań trygonometrycznych",
                "Zastosowanie równań trygonometrycznych w rozwiązywaniu problemów z geometrii analitycznej.",
                "Opis, jak rozwiązywać równania trygonometryczne w kontekście geometrii analitycznej.",
                "Pomóż architektowi określić kąty w wielokącie, wiedząc, że niektóre z jego boków mają znane długości.",
                Set.of(SampleTrigonometricRelationsLearningRequirement.getLearningRequirement(), SampleModulusLearningRequirement.getLearningRequirement())
        ));
        Segment third = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Trygonometria w przestrzeni 3D",
                "Zastosowanie trygonometrii w analizie przestrzennej i geometrii trójwymiarowej.",
                "Opis rozwiązywania problemów geometrycznych w przestrzeni 3D przy użyciu trygonometrii.",
                "Geodeta mierzy odległości i kąty w trójwymiarowym terenie. Pomóż mu obliczyć odległość między dwoma punktami w przestrzeni.",
                Set.of(SampleLawOfCosinesLearningRequirement.getLearningRequirement(), SampleLawOfSinesLearningRequirement.getLearningRequirement())
        ));
        Segment fourth = seedSegment(third, new Seeding.SeededSegmentDetails(
                "Transformacje trygonometryczne",
                "Zastosowanie trygonometrii w przekształceniach i obrotach w przestrzeni.",
                "Opis użycia trygonometrii do obrotów i transformacji w przestrzeni.",
                "Inżynier projektuje roboty, które poruszają się po złożonych trajektoriach. Pomóż mu obliczyć kąty obrotów na podstawie danych wejściowych.",
                Set.of(SampleTrigonometricRelationsLearningRequirement.getLearningRequirement(), SampleSetsLearningRequirement.getLearningRequirement())
        ));
    }

    private void seedSegmentsInSixthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Trygonometria w komputerach",
                "Zastosowanie funkcji trygonometrycznych w technologii komputerowej.",
                "Opis użycia trygonometrii w algorytmach komputerowych i grafice komputerowej.",
                "Programista opracowuje algorytmy do generowania realistycznych obrazów 3D. Pomóż mu wyznaczyć odpowiednie kąty w przestrzeni.",
                Set.of(SampleBasicTrigonometryLearningRequirement.getLearningRequirement(), SampleTrigonometricRelationsLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Symulacje komputerowe z trygonometrią",
                "Zastosowanie trygonometrii w symulacjach komputerowych, takich jak grafika 3D czy wirtualna rzeczywistość.",
                "Opis użycia funkcji trygonometrycznych do tworzenia efektów w grafice komputerowej i wirtualnych środowiskach.",
                "Tworzysz grę komputerową, w której postacie poruszają się po zakrzywionych trajektoriach. Pomóż zaprogramować odpowiednie ruchy postaci.",
                Set.of(SampleBasicTrigonometryLearningRequirement.getLearningRequirement(), SampleUnitCircleLearningRequirement.getLearningRequirement())
        ));
        Segment third = seedSegment(second, new Seeding.SeededSegmentDetails(
                "Algorytmy generowania obrazów",
                "Zastosowanie funkcji trygonometrycznych w algorytmach generujących realistyczne obrazy.",
                "Opis, jak funkcje trygonometryczne mogą pomóc w generowaniu obrazów 3D oraz efektów świetlnych.",
                "Programista musi obliczyć odpowiednie kąty i odległości dla renderowania obiektów 3D w grze komputerowej.",
                Set.of(SampleTrigonometricRelationsLearningRequirement.getLearningRequirement(), SampleUnitCircleLearningRequirement.getLearningRequirement())
        ));
        Segment fourth = seedSegment(third, new Seeding.SeededSegmentDetails(
                "Wykorzystanie trygonometrii w robotyce",
                "Zastosowanie trygonometrii w projektowaniu robotów i automatyce.",
                "Opis użycia funkcji trygonometrycznych w obliczaniu trajektorii ruchu robotów.",
                "Pomóż inżynierowi obliczyć kąt obrotu ramienia robota, aby dotrzeć do wyznaczonego punktu w przestrzeni.",
                Set.of(SampleLawOfCosinesLearningRequirement.getLearningRequirement(), SampleLawOfSinesLearningRequirement.getLearningRequirement())
        ));
        Segment fifth = seedSegment(third, new Seeding.SeededSegmentDetails(
                "Trygonometria w sztucznej inteligencji",
                "Zastosowanie trygonometrii w obliczeniach związanych z analizą danych i AI.",
                "Opis, jak trygonometria pomaga w modelowaniu ruchów obiektów w sztucznej inteligencji.",
                "Pomóż inżynierowi AI zaprogramować agenta, który potrafi poruszać się po mapie, unikając przeszkód za pomocą obliczeń trygonometrycznych.",
                Set.of(SampleLawOfSinesLearningRequirement.getLearningRequirement(), SampleUnitCircleLearningRequirement.getLearningRequirement())
        ));
    }

}

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
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.statistics.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class StatisticsCourseSeeding {
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

    public void statisticsCourseSeeding(Course course) {
        seedSampleLessonsInCourse(course);
    }

    private void seedSampleLessonsInCourse(Course course) {
        Lesson firstLesson = Lesson.create(course.getAuthorEducator(), course);
        firstLesson.setName("Wprowadzenie do statystyki");
        lessonPersistence.save(firstLesson);
        seedSegmentsInFirstLesson(firstLesson);

        Lesson secondLesson = Lesson.create(course.getAuthorEducator(), firstLesson);
        secondLesson.setName("Rozkład Bernoulliego");
        lessonPersistence.save(secondLesson);
        seedSegmentsInSecondLesson(secondLesson);

        Lesson thirdLesson = Lesson.create(course.getAuthorEducator(), firstLesson);
        thirdLesson.setName("Rozkład geometryczny");
        lessonPersistence.save(thirdLesson);
        seedSegmentsInThirdLesson(thirdLesson);

        Lesson fourthLesson = Lesson.create(course.getAuthorEducator(), thirdLesson);
        fourthLesson.setName("Rozkład hipergeometryczny");
        lessonPersistence.save(fourthLesson);
        seedSegmentsInFourthLesson(fourthLesson);
    }

    private void seedSegmentsInFirstLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Podstawowe pojęcia statystyki",
                "Poznaj podstawowe terminy, takie jak zmienne losowe, wartości oczekiwane i wariancja.",
                "Podczas opisywania teorii wprowadź ucznia w świat statystyki, pokazując jej praktyczne zastosowania.",
                "Zadania mogą obejmować proste obliczenia wartości oczekiwanej i wariancji na przykładzie rzutów monetą.",
                Set.of(SampleBasicStatisticsLearningRequirement.getLearningRequirement())
        ));
    }

    private void seedSegmentsInSecondLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Definicja rozkładu Bernoulliego",
                "Rozkład Bernoulliego opisuje wyniki jednego eksperymentu z dwoma możliwymi rezultatami.",
                "Podczas wprowadzania rozkładu Bernoulliego podkreśl jego zastosowanie w praktyce, np. sukces/porażka.",
                "Zadanie może dotyczyć obliczenia prawdopodobieństw sukcesu i porażki przy różnych parametrach p.",
                Set.of(SampleBernoulliDistributionLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Średnia i wariancja rozkładu Bernoulliego",
                "Naucz się obliczać średnią i wariancję dla rozkładu Bernoulliego.",
                "Opis powinien uwzględniać wzory matematyczne oraz przykłady.",
                "Zadanie może obejmować obliczenia średniej i wariancji przy danych parametrach p.",
                Set.of(SampleBernoulliDistributionLearningRequirement.getLearningRequirement())
        ));
    }

    private void seedSegmentsInThirdLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Definicja rozkładu geometrycznego",
                "Rozkład geometryczny opisuje liczbę prób potrzebnych do osiągnięcia pierwszego sukcesu.",
                "Podczas wprowadzania rozkładu geometrycznego podkreśl jego praktyczne zastosowania, takie jak testy jakości.",
                "Zadanie może dotyczyć obliczenia prawdopodobieństw dla różnych liczby prób i parametru p.",
                Set.of(SampleGeometricDistributionLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Średnia i wariancja rozkładu geometrycznego",
                "Dowiedz się, jak obliczać średnią i wariancję dla rozkładu geometrycznego.",
                "Opis powinien zawierać wzory i konkretne przykłady zastosowań.",
                "Zadanie może obejmować obliczenia wartości średniej i wariancji dla rozkładu geometrycznego z danym p.",
                Set.of(SampleGeometricDistributionLearningRequirement.getLearningRequirement())
        ));
    }

    private void seedSegmentsInFourthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Definicja rozkładu hipergeometrycznego",
                "Rozkład hipergeometryczny opisuje wybór próbek bez zwracania w populacji o określonej liczbie sukcesów.",
                "Wprowadź ucznia w zrozumienie rozkładu hipergeometrycznego, podając przykłady praktyczne, np. losowanie kart.",
                "Zadanie może dotyczyć obliczenia prawdopodobieństwa sukcesu przy określonych parametrach próby.",
                Set.of(SampleHypergeometricDistributionLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Wzory dla rozkładu hipergeometrycznego",
                "Naucz się stosować wzory do obliczania prawdopodobieństw w rozkładzie hipergeometrycznym.",
                "Opis powinien zawierać matematyczne wzory i konkretne przykłady zastosowań.",
                "Zadanie może dotyczyć obliczeń związanych z losowaniem próbek z określonych populacji.",
                Set.of(SampleHypergeometricDistributionLearningRequirement.getLearningRequirement())
        ));
    }
}

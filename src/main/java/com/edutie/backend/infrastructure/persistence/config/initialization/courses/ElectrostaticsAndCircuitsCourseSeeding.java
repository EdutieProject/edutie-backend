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
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ElectrostaticsAndCircuitsCourseSeeding {
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

    public void electrostaticsAndCircuitsCourseSeeding(Course course) {
        seedSampleLessonsInCourse(course);
    }

    private void seedSampleLessonsInCourse(Course course) {
        Lesson firstLesson = Lesson.create(course.getAuthorEducator(), course);
        firstLesson.setName("Wstęp do elektrostatyki");
        lessonPersistence.save(firstLesson);
        seedSegmentsInFirstLesson(firstLesson);

        Lesson secondLesson = Lesson.create(course.getAuthorEducator(), firstLesson);
        secondLesson.setName("Pola elektryczne i potencjał");
        lessonPersistence.save(secondLesson);
        seedSegmentsInSecondLesson(secondLesson);

        Lesson thirdLesson = Lesson.create(course.getAuthorEducator(), secondLesson);
        thirdLesson.setName("Obwody elektryczne");
        lessonPersistence.save(thirdLesson);
        seedSegmentsInThirdLesson(thirdLesson);

        Lesson fourthLesson = Lesson.create(course.getAuthorEducator(), thirdLesson);
        fourthLesson.setName("Prawo Kirchhoffa i zasady obwodów");
        lessonPersistence.save(fourthLesson);
        seedSegmentsInFourthLesson(fourthLesson);

        Lesson fifthLesson = Lesson.create(course.getAuthorEducator(), thirdLesson);
        fifthLesson.setName("Zastosowania obwodów elektrycznych");
        lessonPersistence.save(fifthLesson);
        seedSegmentsInFifthLesson(fifthLesson);
    }

    private void seedSegmentsInFirstLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Ładunki elektryczne",
                "Czym są ładunki elektryczne i jak oddziałują na siebie.",
                "Wprowadź podstawowe pojęcia elektrostatyki i przykłady z życia codziennego.",
                "",
                Set.of(SampleElectricChargesLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Prawo Coulomba",
                "Poznaj podstawowe prawo opisujące siły między ładunkami elektrycznymi.",
                "Zwróć uwagę na wektory i skalarną naturę siły Coulomba.",
                "Zadanie może polegać na obliczeniu siły między dwoma ładunkami w próżni.",
                Set.of(SampleCoulombLawLearningRequirement.getLearningRequirement())
        ));
    }

    private void seedSegmentsInSecondLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Pole elektryczne",
                "Czym jest pole elektryczne i jak je wyznaczyć.",
                "",
                "Zadanie może polegać na wyznaczeniu wektora pola elektrycznego w danym punkcie.",
                Set.of(SampleElectricFieldLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Potencjał elektryczny",
                "Zrozumienie potencjału elektrycznego i różnicy potencjałów.",
                "",
                "Zadanie powinno obejmować obliczenia pracy wykonanej przy przemieszczaniu ładunku w polu elektrycznym.",
                Set.of(SampleElectricPotentialLearningRequirement.getLearningRequirement())
        ));
    }

    private void seedSegmentsInThirdLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Prąd elektryczny i rezystancja",
                "Podstawowe pojęcia związane z przepływem prądu.",
                "",
                "Zadanie może polegać na zastosowaniu prawa Ohma do analizy prostego obwodu.",
                Set.of(SampleElectricCurrentLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Elementy obwodów",
                "Omówienie podstawowych elementów obwodów: rezystorów, kondensatorów i baterii.",
                "",
                "Zadanie może obejmować analizę prostych kombinacji rezystorów w szeregu i równolegle.",
                Set.of(SampleCircuitComponentsLearningRequirement.getLearningRequirement())
        ));
    }

    private void seedSegmentsInFourthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Prawo Kirchhoffa",
                "Zasady zachowania energii i ładunku w obwodach.",
                "",
                "Zadanie może dotyczyć analizy obwodu wielowęzłowego z zastosowaniem praw Kirchhoffa.",
                Set.of(SampleKirchhoffsLawLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Obwody z prawem kirchoffa",
                "Prawo Kirchoffa dotyczy każdego obwodu.",
                "",
                "",
                Set.of(SampleKirchhoffsLawLearningRequirement.getLearningRequirement(), SampleElectricPotentialLearningRequirement.getLearningRequirement() )
        ));
    }

    private void seedSegmentsInFifthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Zastosowanie obwodów",
                "Praktyczne zastosowania obwodów elektrycznych w urządzeniach codziennego użytku.",
                "",
                "Zadanie może polegać na analizie schematu elektrycznego prostego urządzenia.",
                Set.of(SampleCircuitComponentsLearningRequirement.getLearningRequirement(), SampleKirchhoffsLawLearningRequirement.getLearningRequirement())
        ));
    }
}

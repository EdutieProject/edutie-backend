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
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ThermodynamicsCourseSeeding {
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

    public void thermodynamicsCourseSeeding(Course course) {
        seedSampleLessonsInCourse(course);
    }


    private void seedSampleLessonsInCourse(Course course) {
        Lesson firstLesson = Lesson.create(course.getAuthorEducator(), course);
        firstLesson.setName("Wstęp do termodynamiki");
        lessonPersistence.save(firstLesson);
        seedSegmentsInFirstLesson(firstLesson);

        Lesson secondLesson = Lesson.create(course.getAuthorEducator(), firstLesson);
        secondLesson.setName("Ciepło");
        lessonPersistence.save(secondLesson);
        seedSegmentsInSecondLesson(secondLesson);

        Lesson thirdLesson = Lesson.create(course.getAuthorEducator(), firstLesson);
        thirdLesson.setName("Gaz idealny");
        lessonPersistence.save(thirdLesson);
        seedSegmentsInThirdLesson(thirdLesson);

        Lesson fourthLesson = Lesson.create(course.getAuthorEducator(), thirdLesson);
        fourthLesson.setName("Przemiany gazu");
        lessonPersistence.save(fourthLesson);
        seedSegmentsInFourthLesson(fourthLesson);

        Lesson fifthLesson = Lesson.create(course.getAuthorEducator(), fourthLesson);
        fifthLesson.setName("Silnik cieplny");
        lessonPersistence.save(fifthLesson);
        seedSegmentsInFifthLesson(fifthLesson);

        Lesson sixthLesson = Lesson.create(course.getAuthorEducator(), fifthLesson);
        sixthLesson.setName("Drugie prawo termodynamiki");
        lessonPersistence.save(sixthLesson);
        seedSegmentsInSixthLesson(sixthLesson);

        Lesson seventhLesson = Lesson.create(course.getAuthorEducator(), fifthLesson);
        seventhLesson.setName("Wydajność silnika cieplnego");
        lessonPersistence.save(seventhLesson);
        seedSegmentsInSeventhLesson(seventhLesson);
    }

    private void seedSegmentsInFirstLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Pierwsza zasada w termodynamice",
                "Termodynamika ma swoje zasady. Pierwsza zasada termodynamiki: nie gadamy o termodynamice.",
                "Podczas opisywania teorii miej na uwadzę to, że jest to początek nauki ucznia! Bądź miły i zachęć go nauki, podkreślając użyteczność wymagań nauczania.",
                "",
                Set.of(SampleFirstLawThermodynamicsLearningRequirement.getLearningRequirement())));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Teoria kinetyczno molekularna",
                "Poznaj teorię tego, jak działa gaz na poziomie jego molekuł",
                "",
                "",
                Set.of(SampleKineticMolecularTheoryLearningRequirement.getLearningRequirement())
        ));
        Segment third = seedSegment(second, new Seeding.SeededSegmentDetails(
                "Teoria o idealnym gazie",
                "W fizyce często zakłada się uproszczenia, dzięki którym łatwiej jest coś policzyć. Takim uproszczeniem jest teoretyczny gaz idealny.",
                "",
                "",
                Set.of(SampleIdealGasTheoryLearningRequirement.getLearningRequirement())
        ));
    }


    private void seedSegmentsInSecondLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Temperatura - czym ona jest?",
                "Słupek termometra pokazuje coś więcej, niż wskazówkę o tym czy zakładać kurtkę.",
                "","",
                Set.of(SampleTemperatureAndHeatLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Skąd bierze się ciepło",
                "Zobaczymy jak wyglądają fale radiowe przy pomocy trygonometrii.",
                "",
                "Niech zadanie dotyczy tego jak cząstki ocierając się o siebie mogą powodować ciepło. ",
                Set.of(SampleTemperatureAndHeatLearningRequirement.getLearningRequirement(), SampleKineticMolecularTheoryLearningRequirement.getLearningRequirement())
        ));
        Segment third = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Ciepło które przychodzi i odchodzi",
                "Energię można dać i odebrać. Dlatego potrzebujemy klimatyzacji.",
                "",
                "Niech zadanie dotyczy tego jak klimatyzacja poprzez pompowanie zimnego powietrza odbiera energię z zamkniętego układu.",
                Set.of(SampleTemperatureAndHeatLearningRequirement.getLearningRequirement(), SampleFirstLawThermodynamicsLearningRequirement.getLearningRequirement())
        ));
    }


    private void seedSegmentsInThirdLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Prawo gazu idealnego",
                "Zasada, która pozwala obliczyć, jak gaz idealny zachowuje się w określonych warunkach.",
                "",
                "Niech zadanie dotyczy wykorzystania równania stanu gazu idealnego: PV = nRT.",
                Set.of(SampleIdealGasTheoryLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Równanie Clapeyrona",
                "Równanie ogólne dla gazu idealnego, które obejmuje zależność między ciśnieniem, objętością i temperaturą.",
                "",
                "Zadanie powinno obejmować przekształcenia równania Clapeyrona i zastosowanie w różnych sytuacjach.",
                Set.of(SampleIdealGasTheoryLearningRequirement.getLearningRequirement())
        ));
    }



    private void seedSegmentsInFourthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Przemiana izotermiczna",
                "Przemiana gazu, podczas której temperatura pozostaje stała.",
                "",
                "Zadanie może dotyczyć obliczeń pracy wykonanej w procesie izotermicznym.",
                Set.of(SampleIdealGasTheoryLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Przemiana izochoryczna",
                "Przemiana gazu, w której objętość pozostaje stała.",
                "",
                "Zadanie powinno obejmować zrozumienie relacji między ciśnieniem i temperaturą.",
                Set.of(SampleIdealGasTheoryLearningRequirement.getLearningRequirement(), SampleKineticMolecularTheoryLearningRequirement.getLearningRequirement())
        ));
        Segment third = seedSegment(second, new Seeding.SeededSegmentDetails(
                "Przemiana izobaryczna",
                "Przemiana gazu, w której ciśnienie jest stałe.",
                "",
                "Zadanie może dotyczyć obliczeń zmian energii wewnętrznej gazu w tej przemianie.",
                Set.of(SampleIdealGasTheoryLearningRequirement.getLearningRequirement(), SampleHeatEngineLearningRequirement.getLearningRequirement())
        ));
        Segment fourth = seedSegment(third, new Seeding.SeededSegmentDetails(
                "Przemiana adiabatyczna",
                "Przemiana gazu, podczas której nie zachodzi wymiana ciepła z otoczeniem.",
                "",
                "Zadanie może obejmować obliczenia pracy wykonanej w procesie adiabatycznym.",
                Set.of(SampleIdealGasTheoryLearningRequirement.getLearningRequirement(), SampleTemperatureAndHeatLearningRequirement.getLearningRequirement())
        ));
    }

    private void seedSegmentsInFifthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Zasada działania silnika cieplnego",
                "Jak silnik cieplny przekształca ciepło w pracę?",
                "",
                "Zadanie powinno dotyczyć analizy przepływu energii w silniku cieplnym.",
                Set.of(SampleHeatEngineLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Cykl Carnota",
                "Najbardziej wydajny cykl termodynamiczny dla silnika cieplnego.",
                "",
                "Zadanie może obejmować obliczenie pracy wykonanej w cyklu Carnota.",
                Set.of(SampleHeatEngineLearningRequirement.getLearningRequirement(), SampleTemperatureAndHeatLearningRequirement.getLearningRequirement())
        ));
    }

    private void seedSegmentsInSixthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Entropia",
                "Podstawowe pojęcie związane z nieodwracalnością procesów termodynamicznych.",
                "",
                "Zadanie może obejmować obliczenia zmian entropii w procesach termodynamicznych.",
                Set.of(SampleSecondLawThermodynamicsRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Drugie prawo termodynamiki",
                "Nieodwracalność procesów i kierunek przepływu energii.",
                "",
                "Zadanie powinno obejmować analizę nieodwracalnych przemian gazu.",
                Set.of(SampleSecondLawThermodynamicsRequirement.getLearningRequirement(), SampleHeatEngineLearningRequirement.getLearningRequirement())
        ));
    }


    private void seedSegmentsInSeventhLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Definicja wydajności silnika cieplnego",
                "Poznaj, jak obliczyć, jaka część energii cieplnej jest zamieniana na pracę.",
                "",
                "Zadanie może dotyczyć stosowania wzoru na wydajność silnika cieplnego.",
                Set.of(SampleThermalEngineEfficiencyRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Praktyczne ograniczenia wydajności",
                "Dowiedz się, dlaczego żaden rzeczywisty silnik nie osiąga wydajności cyklu Carnota.",
                "",
                "Zadanie może obejmować analizę strat energii w rzeczywistych silnikach.",
                Set.of(SampleThermalEngineEfficiencyRequirement.getLearningRequirement(), SampleFirstLawThermodynamicsLearningRequirement.getLearningRequirement())
        ));
    }

}

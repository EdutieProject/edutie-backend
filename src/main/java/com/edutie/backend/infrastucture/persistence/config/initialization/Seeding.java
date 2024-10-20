package com.edutie.backend.infrastucture.persistence.config.initialization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.course.tag.CourseTag;
import com.edutie.backend.domain.studyprogram.course.tag.persistence.CourseTagPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.backend.infrastucture.persistence.config.initialization.samples.math.SampleModulusLearningRequirement;
import com.edutie.backend.infrastucture.persistence.config.initialization.samples.math.SampleQuadraticFunctionLearningRequirement;
import com.edutie.backend.infrastucture.persistence.config.initialization.samples.math.SampleSetsLearningRequirement;
import com.edutie.backend.infrastucture.persistence.config.initialization.samples.math.SampleTrigonometryLearningRequirement;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Seeding class for seeding database with sample data
 *
 * @author Kjur0
 * @version 0.6
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class Seeding {
    final int MAX_SEEDED_COURSES = 1;
    final int MAX_SEEDED_SCIENCES = 1;
    private final SciencePersistence sciencePersistence;
    private final CoursePersistence coursePersistence;
    private final LessonPersistence lessonPersistence;
    private final SegmentPersistence segmentPersistence;
    private final AdministratorPersistence administratorPersistence;
    private final EducatorPersistence educatorPersistence;
    //    private final ExerciseTypePersistence exerciseTypePersistence;
    private final LearningRequirementPersistence learningRequirementPersistence;
    private final LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    private final LearningResultPersistence learningResultPersistence;
    private final CourseTagPersistence courseTagPersistence;
    private final LearningResourcePersistence learningResourcePersistence;
    private final StudentPersistence studentPersistence;
    private final UserId uid = new UserId();
    private final Administrator administrator = Administrator.create(uid);
    private final Educator educator = Educator.create(uid, administrator);
    private final Student student = Student.create(new UserId());
    private CourseTag courseTag;

    private void initializeProfiles() {
        log.info("Seeding profiles for user of id {}", uid);
        studentPersistence.save(student);
        administratorPersistence.save(administrator);
        educatorPersistence.save(educator);
    }

    private void initializeLearningRequirements() {
        log.info("Seeding learning requirements...");
        learningRequirementPersistence.save(SampleModulusLearningRequirement.getLearningRequirement(educator)).throwIfFailure();
        learningRequirementPersistence.save(SampleQuadraticFunctionLearningRequirement.getLearningRequirement(educator)).throwIfFailure();
        learningRequirementPersistence.save(SampleTrigonometryLearningRequirement.getLearningRequirement(educator)).throwIfFailure();
        learningRequirementPersistence.save(SampleSetsLearningRequirement.getLearningRequirement(educator)).throwIfFailure();
    }

    private record SeededSegmentDetails(
            String segmentName,
            String segmentDescription,
            String learningResourceDefinitionTheoryOverview,
            String learningResourceDefinitionExerciseOverview,
            Set<LearningRequirement> learningRequirements
    ) {
    }

    ;

    private Segment seedSegment(Lesson lesson, SeededSegmentDetails details) {
        Segment segment = Segment.create(educator, lesson);
        segment.setName(details.segmentName);
        segment.setSnippetDescription(details.segmentDescription);
        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                educator,
                PromptFragment.of(details.learningResourceDefinitionTheoryOverview),
                PromptFragment.of(details.learningResourceDefinitionExerciseOverview),
                details.learningRequirements
        );
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();
        segment.setLearningResourceDefinitionId(learningResourceDefinition.getId());
        segmentPersistence.save(segment).throwIfFailure();
        return segment;
    }

    private Segment seedSegment(Segment previousSegment, SeededSegmentDetails details) {
        Segment segment = Segment.create(educator, previousSegment);
        segment.setName(details.segmentName);
        segment.setSnippetDescription(details.segmentDescription);
        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                educator,
                PromptFragment.of(details.learningResourceDefinitionTheoryOverview),
                PromptFragment.of(details.learningResourceDefinitionExerciseOverview),
                details.learningRequirements
        );
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();
        segment.setLearningResourceDefinitionId(learningResourceDefinition.getId());
        segmentPersistence.save(segment).throwIfFailure();
        return segment;
    }

    /**
     * Seed database with sample study program
     * <p>This will create random number of sciences</p>
     *
     * @see #seedStudyProgram()
     * @since 0.5
     */
    @PostConstruct
    @Transactional
    public void seeding() {
        log.info("======================");
        log.info("  DB SEEDING - START  ");
        log.info("======================");
        initializeProfiles();
        initializeLearningRequirements();
        seedLearningResourceDefinition();
        seedStudyProgram();
        log.info("=====================");
        log.info("  DB SEEDING - END   ");
        log.info("=====================");
    }

    /**
     * Seed random number of sciences
     *
     * @since 0.5
     */
    private void seedStudyProgram() {
        log.info("Seeding study program...");
        Science science = seedScience("Matematyka", "Królowa nauk");
        seedSampleCourse(science, "Przykładowy kurs", "Kurs to może trochę przesadzona nazwa... powinniśmy te programy nazwać inaczej");
    }


    /**
     * Seeds science
     *
     * @param name        science name
     * @param description science description
     * @return seeded science
     */
    private Science seedScience(String name, String description) {
        Science science = Science.create(educator).getValue();
        science.setName(name);
        science.setDescription(description);
        sciencePersistence.save(science);
        return science;
    }

    private void seedSampleCourse(Science science, String name, String description) {
        Course course = Course.create(educator, science);
        course.setName(name);
        course.setDescription(description);
        coursePersistence.save(course);
        seedSampleLessonsInCourse(course);
    }

    private void seedSampleLessonsInCourse(Course course) {
        Lesson firstLesson = Lesson.create(educator, course);
        firstLesson.setName("Pierwsza lekcja");
        lessonPersistence.save(firstLesson);
        seedSegmentsInFirstLesson(firstLesson);

        Lesson secondLesson = Lesson.create(educator, firstLesson);
        secondLesson.setName("Poszerzanie horyzontów");
        lessonPersistence.save(secondLesson);
        seedSegmentsInSecondLesson(secondLesson);

        Lesson thirdLesson = Lesson.create(educator, secondLesson);
        thirdLesson.setName("Następna lekcja");
        lessonPersistence.save(thirdLesson);
        seedSegmentsInThirdLesson(thirdLesson);

        Lesson fourthLesson = Lesson.create(educator, secondLesson);
        fourthLesson.setName("Zadania dodatkowe");
        lessonPersistence.save(fourthLesson);
        seedSegmentsInFourthLesson(fourthLesson);

        Lesson fifthLesson = Lesson.create(educator, fourthLesson);
        fifthLesson.setName("Tu jest najciężej...");
        lessonPersistence.save(fifthLesson);
        seedSegmentsInFifthLesson(fifthLesson);
    }

    private void seedSegmentsInFirstLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new SeededSegmentDetails(
                "Wartość bewzwzględna w oceanie",
                "Naucz się wartości bezwzględnej w otoczeniu oceanu. Myśl o głębokości jak o osi liczbowej!",
                "Podczas opisywania teorii miej na uwadzę to, że jest to początek nauki ucznia! Bądź miły i zachęć go nauki, podkreślając użyteczność wymagań nauczania.",
                "Zadanie powinno zawierać metaforę wartości bezwzględnej jako głębokości morza. Niech fabuła w zadaniu dotyczy skakania z różnych punktów pływającego statku.",
                Set.of(SampleModulusLearningRequirement.getLearningRequirement(educator))));
        Segment second = seedSegment(first, new SeededSegmentDetails(
                "Owoce i warzywa",
                "Pomyśl o zbiorach metaforycznie...",
                "Podkreśl fundamentalne i ważne znaczenie zbiorów w matematyce, również na bardziej zaawansowanym jej poziomie. Używaj przykładów i metafor porównujących elementy zbiorów do owoców i warzyw.",
                "Niech zadanie porównuje zbiory i ich elementy do koszyka warzyw i owoców.",
                Set.of(SampleSetsLearningRequirement.getLearningRequirement(educator))
        ));
    }

    ;

    private void seedSegmentsInSecondLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new SeededSegmentDetails(
                "Zbudujmy taras",
                "Chcesz wybudować taras dla swojego domu. Będziesz opisywał jego kształt za pomocą równań!",
                "Opis teorii powinien zawierać przykłady na temat tego jak funkcja kwadratowa może opisywać budowanie różnych struktur.",
                "Zadanie powinno zawierać metaforę paraboli funkcji kwadratowej jako kształt tarasu. Niech uczeń opisze równaniami kształt tarasu w domu którym buduje! Dodatkowo, może opisywać równaniami kształty terkatory i balustrady.",
                Set.of(SampleQuadraticFunctionLearningRequirement.getLearningRequirement(educator))
        ));
        Segment second = seedSegment(first, new SeededSegmentDetails(
                "Fale radiowe",
                "Zobaczymy jak wyglądają fale radiowe przy pomocy trygonometrii.",
                "Opis teorii powinien zawierać przykłady na temat tego jakie zastosowanie ma trygonometria w opisie fal stosowanych we współczesnych technologiach.",
                "Zadanie powinno zawierać przykład zastosowania trygonometrii jako fale radiowe w krótkofalówkach.",
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement(educator))
        ));
        Segment third = seedSegment(first, new SeededSegmentDetails(
                "Maszt i nadawanie internetu",
                "Fale radiowe nadawane przez maszty dają nam dostęp do internetu. Nauczmy się na ich przykładzie!",
                "Opis teorii powinien zawierać przykłady na temat tego jakie zastosowanie ma trygonometria w emitowaniu fal 5G które daja nam internet",
                "Zadanie powinno opisywać za pomocą trygonometrii geometrię i zastosowanie fal 5G. Potem, obliczmy za pomocą wartosci bezwzględnej wysokości masztu nadawczego nad poziomem morza ale również wysokość względną.",
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement(educator), SampleModulusLearningRequirement.getLearningRequirement(educator))
        ));
        Segment fourth = seedSegment(third, new SeededSegmentDetails(
                "Kapelusz grzyba jako parabola",
                "Zauważ kształty obecne wokół nas... Czy grzyby nie przypominają ci paraboli?",
                "Umieść w opisie teorii odnośniki do różnych rodzai grzybów. Wpleć to umiejętnie w zagadnienia teoretyczne tak, aby zaciekawić ucznia.",
                "Zadanie powinno porównywać odwróconą parabolę funkcji kwadratowej do kształtu kapelusza grzyba.",
                Set.of(SampleQuadraticFunctionLearningRequirement.getLearningRequirement(educator))
        ));
        Segment fifth = seedSegment(third, new SeededSegmentDetails(
                "Misja na planetę Zettarion",
                "Astronauci z Ziemi, Kapitan Alva i Doktor Nero, zostali wysłani na misję eksploracyjną na nowo odkrytą planetę Zettarion. Zettarion jest niezwykłą planetą, ponieważ jej powierzchnia składa się z tajemniczych kryształów energetycznych o różnych właściwościach. Kryształy te dzielą się na dwie kategorie: kryształy A i kryształy B. Każdy kryształ emituje różną ilość energii, mierzoną w jednostkach absolutnych (wartość bezwzględna), a zbiory kryształów są szczególnie ważne dla rozwiązania zagadki planetarnej energii.",
                "Umieść w opisie teoretycznym odnośniki i metafory do tego jak zagadnienia mogą być wykorzystywane w odkrywaniu kosmosu.",
                """
                        Niech zadanie dotyczy poniższej fabuły:
                        Astronauci z Ziemi, Kapitan Alva i Doktor Nero, zostali wysłani na misję eksploracyjną na nowo odkrytą planetę Zettarion. Zettarion jest niezwykłą planetą, ponieważ jej powierzchnia składa się z tajemniczych kryształów energetycznych o różnych właściwościach. Kryształy te dzielą się na dwie kategorie: **kryształy A** i **kryształy B**. Każdy kryształ emituje różną ilość energii, mierzoną w jednostkach absolutnych (wartość bezwzględna), a zbiory kryształów są szczególnie ważne dla rozwiązania zagadki planetarnej energii.
                        """,
                Set.of(SampleModulusLearningRequirement.getLearningRequirement(educator), SampleSetsLearningRequirement.getLearningRequirement(educator))
        ));
    }

    ;

    private void seedSegmentsInThirdLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new SeededSegmentDetails(
                "Uczniowie na maturze",
                "Sytuacja z którą pewnie możesz się utożsamiać...",
                "Zaprezentuj że trygonometria może opisywać częstotliwość w różnych badaniach i w społeczeństwie.",
                "Zadanie powinno opisywać częstotliwość stresowania się uczniów na maturze jako funkcja trygonometryczna.",
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement(educator))
        ));
        Segment second = seedSegment(first, new SeededSegmentDetails(
                "Wykresy i inne",
                "Tym razem bardziej matematycznie...",
                "Spróbuj uczniowi opisać korelację i podobieństwa funkcji kwadratowej i zagadnień trygonometrycznych na płaszczyźnie kartezjańskiej.",
                "Niech zadanie poleci uczniowi narysowanie wykresów na osobnej kartce i opisanie ich w odpowiedzi na zadanie.",
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement(educator), SampleQuadraticFunctionLearningRequirement.getLearningRequirement(educator))
        ));
    }

    ;

    private void seedSegmentsInFourthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new SeededSegmentDetails(
                "Praca domowa dla uczniów",
                "Uczniowie nie lubią prac domowych!",
                "Zaprezentuj funkcje kwadratową oraz jej monotoniczność na przykładzie uczniów którzy nie lubią prac domowych. Niech opis nakreśli w wyobraźni ucznia wykres mający kształt paraboli, opisujący to ile uczniów nie lubi robić prac domowych.",
                "Niech zadanie zakłada że zależność ilości uczniów robiących prace domowe do zadawanych prac domowych ma kształt funkcji kwadratowej o ujemnym współczynniku. Niech uczeń obliczy właściwości tej funkcji.",
                Set.of(SampleQuadraticFunctionLearningRequirement.getLearningRequirement(educator))
        ));

        Segment second = seedSegment(first, new SeededSegmentDetails(
                "Zarządzanie Produkcją w Fabryce Stal-Mach",
                "W nowoczesnej fabryce Stal-Mach specjalizującej się w produkcji wyrobów ze stali, wykorzystywanych w branży budowlanej, zarządcy napotkali problem optymalizacji procesów produkcyjnych...",
                """
                        Dopasuj kontekst teoretyczny do fabuły podanej poniżej. Niech teoria zawiera metafory i odnośniki dotyczące fabuły.
                        W nowoczesnej fabryce Stal-Mach specjalizującej się w produkcji wyrobów ze stali, wykorzystywanych w branży budowlanej, zarządcy napotkali problem optymalizacji procesów produkcyjnych. Fabryka posiada kilka linii produkcyjnych o różnych parametrach wydajności, a także różnorodne ilości surowców stalowych, które muszą być efektywnie wykorzystane.\s
                        """,
                """
                        Niech zadanie dotyczy poniższej fabuły:
                        W nowoczesnej fabryce Stal-Mach specjalizującej się w produkcji wyrobów ze stali, wykorzystywanych w branży budowlanej, zarządcy napotkali problem optymalizacji procesów produkcyjnych. Fabryka posiada kilka linii produkcyjnych o różnych parametrach wydajności, a także różnorodne ilości surowców stalowych, które muszą być efektywnie wykorzystane.\s
                        """,
                Set.of(SampleQuadraticFunctionLearningRequirement.getLearningRequirement(educator), SampleSetsLearningRequirement.getLearningRequirement(educator))
        ));

        Segment third = seedSegment(second, new SeededSegmentDetails(
                "Optymalizacja Pracy Laboratorium Analiz Chemicznych",
                "Laboratorium Analiz Chemicznych \"ChemLab\" specjalizuje się w analizie próbek środowiskowych, takich jak woda, gleba i powietrze...",
                """
                        Niech opis teorii odwołuje się do fabuły podanej poniżej:
                        Laboratorium Analiz Chemicznych "ChemLab" specjalizuje się w analizie próbek środowiskowych, takich jak woda, gleba i powietrze. Jednym z wyzwań, przed którymi stoi laboratorium, jest efektywna organizacja analizy dużej liczby próbek, które są dostarczane z różnych zakątków kraju. Laboratorium to musi jednocześnie uwzględniać wymogi czasowe i precyzję pomiarów.
                        Umieść metafory i odnośniki do realnego zastosowania zagadnień podnaych w wymaganiach na podstawie fabuły.
                        """,
                """
                        Niech zadanie które stworzysz odnosi się do poniższej fabuły: 
                        Laboratorium Analiz Chemicznych "ChemLab" specjalizuje się w analizie próbek środowiskowych, takich jak woda, gleba i powietrze. Jednym z wyzwań, przed którymi stoi laboratorium, jest efektywna organizacja analizy dużej liczby próbek, które są dostarczane z różnych zakątków kraju. Laboratorium to musi jednocześnie uwzględniać wymogi czasowe i precyzję pomiarów.
                        Aby zoptymalizować proces analizy, laboratorium zdecydowało się wykorzystać koncepcje trygonometrii i teorii zbiorów do zarządzania rotacją próbek i alokacją sprzętu pomiarowego.
                        """,
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement(educator), SampleSetsLearningRequirement.getLearningRequirement(educator))
        ));
    }

    ;

    private void seedSegmentsInFifthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new SeededSegmentDetails(
                "Wyłowienie titanica",
                "Rozwiążmy zagadkę: jak wyłowić statek Titanic?",
                "Zaprezentuj jak można wykorzystać naukowy kontekst i wymagania nauczania do potencjalnej misji wyłowienia zatopionego statku.",
                "Niech zadanie stawia na kreatywność ucznia. Powinno zawierać pewne wskazówki co do tego jak możnaby było użyć matematycznych właściwości aby wyłowić titanica. Opisz maszyny które możnaby wykorzystać i niech uczeń wykorzysta działania matematyczne aby maszyny wyłowiły zatopiony statek.",
                Set.of(SampleQuadraticFunctionLearningRequirement.getLearningRequirement(educator), SampleTrigonometryLearningRequirement.getLearningRequirement(educator))
        ));

        Segment second = seedSegment(first, new SeededSegmentDetails(
                "Konstruowanie helikoptera",
                "Rozważmy funkcjonalności helikoptera w kontekście matematycznym...",
                "Niech teoretyczny kontekst zawiera mało tekstu i dużo przykładów z prostymi wytłumaczeniami. Tłumacząc, odwołuj się do konstrukcji maszyn latających na przykład helikoptera.",
                "Niech zadanie stawia na kreatywność ucznia. Niech uczeń najpierw obliczy częstotliwość obrotu śmigieł helikoptera. Potem niech obliczy wartości bezwzględne na podstawie wysokości budynków i wysokości na której leci helikopter.",
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement(educator), SampleModulusLearningRequirement.getLearningRequirement(educator))
        ));

        Segment third = seedSegment(second, new SeededSegmentDetails(
                "Nauka z dinozaurami",
                " Na terenach dzisiejszej Patagonii żyły dwa gigantyczne dinozaury, Brachiosaurus i Tyrannosaurus Rex. Pewnego dnia postanowiły spotkać się na polanie w środku gęstego lasu...",
                "Niech opis będzie zawierał referencje dotyczące fabuły: Na terenach dzisiejszej Patagonii żyły dwa gigantyczne dinozaury, Brachiosaurus i Tyrannosaurus Rex. Pewnego dnia postanowiły spotkać się na polanie w środku gęstego lasu...",
                """
                        Na terenach dzisiejszej Patagonii żyły dwa gigantyczne dinozaury, Brachiosaurus i Tyrannosaurus Rex. Pewnego dnia postanowiły spotkać się na polanie w środku gęstego lasu.
                        Dopasuj zadanie tak, aby wykorzystywało otaczające drzewa czy długości ciał dinozaurów do obliczeń.
                        """,
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement(educator), SampleModulusLearningRequirement.getLearningRequirement(educator))
        ));
    }

    ;

    /**
     * Seed random number of courses in science
     *
     * @param science science in which the courses will be created
     * @see #seedCourse(int, Science)
     * @since 0.5
     */
    private void seedCourses(Science science) {
        int courses = (int) Math.ceil(Math.random() * MAX_SEEDED_COURSES);
        for (int i = 0; i < courses; i++)
            seedCourse(i, science);
    }

    /**
     * Seed course
     *
     * @param i       courser number
     * @param science science in which the course will be created
     * @see #seedLessons(Course)
     * @since 0.5
     */
    private void seedCourse(int i, Science science) {
        Course course = Course.create(educator, science);
        course.setName("Course" + i);
        course.setDescription("Description of Course" + i);
        course.setAccessible(true);

        if (courseTag != null)
            course.addTag(courseTag);
        courseTag = CourseTag.create("Course Tag " + i);
        courseTagPersistence.save(courseTag);
        course.addTag(courseTag);

        course.update(uid);
        coursePersistence.save(course);
        seedLessons(course);
    }

    /**
     * Seed lessons in course
     *
     * @param course course to seed
     * @see LessonSeeder#seedLessons()
     * @since 0.5
     */
    private void seedLessons(Course course) {
        LessonSeeder ls = new LessonSeeder(course);
        ls.seedLessons();
    }

    private void seedLearningResourceDefinition() {
        log.info("Seeding LRD...");

        learningRequirementPersistence.save(SampleModulusLearningRequirement.getLearningRequirement(educator)).throwIfFailure();
        learningRequirementPersistence.save(SampleQuadraticFunctionLearningRequirement.getLearningRequirement(educator)).throwIfFailure();

        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                educator,
                PromptFragment.of("Opisz również rozwiązywanie równań kwadratowych z wartością bezwzględną. Dopasuj trudność do trudności podanych wymagań"),
                PromptFragment.of("""
                        Zadanie powinno zawierać fabułę dotyczącą projektowania miasta.
                        Niech problemy dotyczą wytyczenia miejsc ścieżek, budynków, tras autobusowych lub ulic.
                        Niech zadanie uwzględnia chociaż jeden przykład równania kwadratowego z wartością bezwzględną.
                        Przykład ten powinien być dopasowany trudnością do trudności podanych wcześniej wymagań
                        """),
                Set.of(SampleModulusLearningRequirement.getLearningRequirement(educator), SampleQuadraticFunctionLearningRequirement.getLearningRequirement(educator))
        );
        learningResourceDefinitionPersistence.save(learningResourceDefinition);
        log.info("Seeded LRD with id: {}", learningResourceDefinition.getId().identifierValue().toString());
    }

    /**
     * Helper class for seeding lessons in courses using predefined variants
     *
     * @author Kjur0
     * @version 0.5
     * @since 0.5
     */
    private class LessonSeeder {
        private final Course course;

        /**
         * Constructor of lesson seeder
         *
         * @param course course in which lessons will be seeded
         */
        public LessonSeeder(Course course) {
            this.course = course;
        }

        /**
         * Seed lessons in course using random variant
         *
         * @see LessonSeeder#variant1()
         * @see LessonSeeder#variant2()
         * @since 0.5
         */
        @Transactional
        public void seedLessons() {
            switch ((int) Math.ceil(Math.random() * 2)) {
                case 1 -> variant1();
                case 2 -> variant2();
            }
        }

        /**
         * Seed first lesson in course
         *
         * @return first lesson
         * @since 0.5
         */
        private @NonNull Lesson seedFirstLesson() {
            Lesson lesson = Lesson.create(educator, course);
            lesson.setName("Lesson 1");
            lesson.setDescription("Description of Lesson 1");
            lesson.update(uid);
            lessonPersistence.save(lesson);
            seedSegments(lesson);
            return lesson;
        }

        /**
         * Variant 1
         * <details><pre class="mermaid">
         * graph
         *  l1 --> l2 --> l3
         *  l2 --> l4 --> l5 --> l6 --> l7
         *  l5 --> l8 --> l9
         * </pre></details>
         *
         * @see #seedFirstLesson()
         * @see #seedLesson(int, Lesson)
         * @since 0.5
         **/
        public void variant1() {
            Lesson l1 = seedFirstLesson();

            Lesson l2 = seedLesson(2, l1);
            seedLesson(3, l2);

            l1 = seedLesson(5, seedLesson(4, l2));
            seedLesson(7, seedLesson(6, l1));

            seedLesson(9, seedLesson(8, l1));
        }

        /**
         * Variant 2
         * <details><pre class="mermaid">
         * graph
         *  l1 --> l2 --> l3 & l4
         *  l1 --> l5 & l6
         *  l3 --> l7 --> l8 --> l9 & l10
         *  l7 --> l11
         *  l4 --> l12 & l13
         *  l13 --> l14 --> l15 & l16
         *  l16 --> l17
         *  l5 --> l18 --> l19
         *  l6 --> l20 --> l21 --> l22 & l23 & l24
         *  l23 --> l25
         *  l24 --> l26 --> l27
         * </pre></details>
         *
         * @see #seedFirstLesson()
         * @see #seedLesson(int, Lesson)
         * @since 0.5
         */
        public void variant2() {
            Lesson l1 = seedFirstLesson();

            Lesson l2 = seedLesson(2, l1);

            Lesson l3 = seedLesson(7, seedLesson(3, l2));
            seedLesson(11, l2);
            l3 = seedLesson(8, l3);
            seedLesson(9, l3);
            seedLesson(10, l3);

            l3 = seedLesson(4, l2);
            seedLesson(12, l3);
            l3 = seedLesson(14, seedLesson(13, l3));
            seedLesson(15, l3);
            seedLesson(17, seedLesson(16, l3));

            seedLesson(19, seedLesson(18, seedLesson(5, l1)));

            l2 = seedLesson(21, seedLesson(20, seedLesson(6, l1)));
            seedLesson(22, l2);
            seedLesson(25, seedLesson(23, l2));
            seedLesson(27, seedLesson(26, seedLesson(24, l2)));
        }

        /**
         * Seed lesson in course
         *
         * @param i    lesson number
         * @param prev previous lesson
         * @return seeded lesson
         * @see #seedSegments(Lesson)
         * @since 0.5
         */
        private @NonNull Lesson seedLesson(int i, Lesson prev) {
            Lesson lesson = Lesson.create(educator, course);
            lesson.setName("Lesson" + i);
            lesson.setDescription("Description of Lesson" + i);
            lesson.setPreviousElement(prev);
            lesson.update(uid);
            lessonPersistence.save(lesson);
            seedSegments(lesson);
            return lesson;
        }

        /**
         * Seed segments in lesson
         *
         * @param lesson lesson to seed
         * @see SegmentSeeder#seedSegments()
         * @since 0.5
         */
        private void seedSegments(Lesson lesson) {
            SegmentSeeder ss = new SegmentSeeder(lesson);
            ss.seedSegments();
        }

        /**
         * Helper class for seeding segments in lessons using predefined variants
         *
         * @author Kjur0
         * @version 0.5
         * @since 0.5
         */
        private class SegmentSeeder {
            //			private final ExerciseType et;
            private final Lesson lesson;

            /**
             * Constructor
             *
             * @param lesson lesson to seed the segments into
             */
            public SegmentSeeder(Lesson lesson) {
                this.lesson = lesson;

//				et = ExerciseType.create(educator).getValue();
//				et.setName("Exercise Type 1");
//				et.setDescription(PromptFragment.of("Description of Exercise Type 1"));

//				exerciseTypePersistence.save(et);
            }

            /**
             * Seed segments in lesson using random variant
             *
             * @see SegmentSeeder#variant1()
             * @see SegmentSeeder#variant2()
             * @see SegmentSeeder#variant3()
             * @since 0.5
             */
            @Transactional
            public void seedSegments() {
                switch ((int) Math.ceil(Math.random() * 3)) {
                    case 1 -> variant1();
                    case 2 -> variant2();
                    case 3 -> variant3();
                }
            }

            /**
             * Seed first segment in lesson
             *
             * @return first segment
             * @since 0.6
             */
            private @NonNull Segment seedFirstSegment() {
//				Science science = lesson.getCourse().getScience();
//				LearningRequirement lr1 = LearningRequirement.create(educator, science).getValue();
//				lr1.setName("Learning Requirement 1");
//				lr1.setDescription(PromptFragment.of("Description of Learning Requirement 1"));
//				learningRequirementPersistence.save(lr1);

                Segment s1 = Segment.create(educator, lesson);
                s1.setName("Segment 1");
                s1.setSnippetDescription("Snippet description of Segment 1");
                s1.update(uid);
                segmentPersistence.save(s1);

                return s1;
            }

            /**
             * Variant 1
             * <details><pre class="mermaid">
             * graph
             *  s1 --> s2 --> s3
             *  s2 --> s4 --> s5 --> s6 --> s7
             *  s5 --> s8 --> s9
             * </pre></details>
             *
             * @see #seedFirstSegment()
             * @see #seedSegment(int, Segment)
             * @since 0.5
             **/
            public void variant1() {
                Segment s1 = seedFirstSegment();

                Segment s2 = seedSegment(2, s1);
                seedSegment(3, s2);

                s1 = seedSegment(5, seedSegment(4, s2));
                seedSegment(7, seedSegment(6, s1));

                seedSegment(9, seedSegment(8, s1));
            }

            /**
             * Variant 2
             * <details><pre class="mermaid">
             * graph
             *  l1 --> l2 --> l3 & l4
             *  l1 --> l5 & l6
             *  l3 --> l7 --> l8 --> l9 & l10
             *  l7 --> l11
             *  l4 --> l12 & l13
             *  l13 --> l14 --> l15 & l16
             *  l16 --> l17
             *  l5 --> l18 --> l19
             *  l6 --> l20 --> l21 --> l22 & l23 & l24
             *  l23 --> l25
             *  l24 --> l26 --> l27
             * </pre></details>
             *
             * @see #seedFirstSegment()
             * @see #seedSegment(int, Segment)
             * @since 0.5
             */
            public void variant2() {
                Segment s1 = seedFirstSegment();

                Segment s2 = seedSegment(2, s1);

                Segment s3 = seedSegment(7, seedSegment(3, s2));
                seedSegment(11, s3);
                s3 = seedSegment(8, s3);
                seedSegment(9, s3);
                seedSegment(10, s3);

                s3 = seedSegment(4, s2);
                seedSegment(12, s3);
                s3 = seedSegment(14, seedSegment(13, s3));
                seedSegment(15, s3);
                seedSegment(17, seedSegment(16, s3));

                seedSegment(19, seedSegment(18, seedSegment(5, s1)));

                s2 = seedSegment(21, seedSegment(20, seedSegment(6, s1)));
                seedSegment(22, s2);
                seedSegment(25, seedSegment(23, s2));
                seedSegment(27, seedSegment(26, seedSegment(24, s2)));
            }

            /**
             * Variant 3
             * <details><pre class="mermaid">
             * graph
             *  s1 --> s2 & s3 & s4
             *  s2 --> s5 --> s6 --> s7 & s8
             *  s5 --> s9
             *  s3 --> s10 & s11
             *  s11 --> s12 --> s13 & s14
             *  s14 --> s15
             *  s4 --> s16 --> s17
             *  s16 --> s18 --> s19
             * </pre></details>
             *
             * @see #seedFirstSegment()
             * @see #seedSegment(int, Segment)
             * @since 0.5
             */
            public void variant3() {
                Segment s1 = seedFirstSegment();

                Segment s2 = seedSegment(5, seedSegment(2, s1));
                seedSegment(9, s2);
                Segment s3 = seedSegment(6, s2);
                seedSegment(7, s3);
                seedSegment(8, s3);

                s2 = seedSegment(3, s1);
                seedSegment(10, s2);
                s3 = seedSegment(12, seedSegment(11, s2));
                seedSegment(13, s3);
                seedSegment(15, seedSegment(14, s3));

                s2 = seedSegment(16, seedSegment(4, s1));
                seedSegment(17, s2);
                seedSegment(19, seedSegment(18, s2));
            }

            /**
             * Seed segment in lesson
             *
             * @param i    lesson number
             * @param prev previous lesson
             * @return seeded lesson
             * @since 0.6
             */
            private @NonNull Segment seedSegment(int i, Segment prev) {
                Segment segment = Segment.create(educator, lesson);
                segment.setName("Segment" + i);
                segment.setSnippetDescription("Snippet description of Segment" + i);
                segment.setPreviousElement(prev);
                segment.update(uid);
                segmentPersistence.save(segment);
                return segment;
            }
        }
    }

}

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
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.math.SampleModulusLearningRequirement;
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.math.SampleQuadraticFunctionLearningRequirement;
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.math.SampleSetsLearningRequirement;
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.math.SampleTrigonometryLearningRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class SampleCourseSeeding {
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

    public void sampleCourseLessonSeeding(Course course) {
        seedSampleLessonsInCourse(course);
    }


    private void seedSampleLessonsInCourse(Course course) {
        Lesson firstLesson = Lesson.create(course.getAuthorEducator(), course);
        firstLesson.setName("Pierwsza lekcja");
        lessonPersistence.save(firstLesson);
        seedSegmentsInFirstLesson(firstLesson);

        Lesson secondLesson = Lesson.create(course.getAuthorEducator(), firstLesson);
        secondLesson.setName("Poszerzanie horyzontów");
        lessonPersistence.save(secondLesson);
        seedSegmentsInSecondLesson(secondLesson);

        Lesson thirdLesson = Lesson.create(course.getAuthorEducator(), secondLesson);
        thirdLesson.setName("Następna lekcja");
        lessonPersistence.save(thirdLesson);
        seedSegmentsInThirdLesson(thirdLesson);

        Lesson fourthLesson = Lesson.create(course.getAuthorEducator(), secondLesson);
        fourthLesson.setName("Zadania dodatkowe");
        lessonPersistence.save(fourthLesson);
        seedSegmentsInFourthLesson(fourthLesson);

        Lesson fifthLesson = Lesson.create(course.getAuthorEducator(), fourthLesson);
        fifthLesson.setName("Tu jest najciężej...");
        lessonPersistence.save(fifthLesson);
        seedSegmentsInFifthLesson(fifthLesson);
    }

    private void seedSegmentsInFirstLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Wartość bewzwzględna w oceanie",
                "Naucz się wartości bezwzględnej w otoczeniu oceanu. Myśl o głębokości jak o osi liczbowej!",
                "Podczas opisywania teorii miej na uwadzę to, że jest to początek nauki ucznia! Bądź miły i zachęć go nauki, podkreślając użyteczność wymagań nauczania.",
                "Zadanie powinno zawierać metaforę wartości bezwzględnej jako głębokości morza. Niech fabuła w zadaniu dotyczy skakania z różnych punktów pływającego statku.",
                Set.of(SampleModulusLearningRequirement.getLearningRequirement())));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Owoce i warzywa",
                "Pomyśl o zbiorach metaforycznie...",
                "Podkreśl fundamentalne i ważne znaczenie zbiorów w matematyce, również na bardziej zaawansowanym jej poziomie. Używaj przykładów i metafor porównujących elementy zbiorów do owoców i warzyw.",
                "Niech zadanie porównuje zbiory i ich elementy do koszyka warzyw i owoców.",
                Set.of(SampleSetsLearningRequirement.getLearningRequirement())
        ));
    }

    ;

    private void seedSegmentsInSecondLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Zbudujmy taras",
                "Chcesz wybudować taras dla swojego domu. Będziesz opisywał jego kształt za pomocą równań!",
                "Opis teorii powinien zawierać przykłady na temat tego jak funkcja kwadratowa może opisywać budowanie różnych struktur.",
                "Zadanie powinno zawierać metaforę paraboli funkcji kwadratowej jako kształt tarasu. Niech uczeń opisze równaniami kształt tarasu w domu którym buduje! Dodatkowo, może opisywać równaniami kształty terkatory i balustrady.",
                Set.of(SampleQuadraticFunctionLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Fale radiowe",
                "Zobaczymy jak wyglądają fale radiowe przy pomocy trygonometrii.",
                "Opis teorii powinien zawierać przykłady na temat tego jakie zastosowanie ma trygonometria w opisie fal stosowanych we współczesnych technologiach.",
                "Zadanie powinno zawierać przykład zastosowania trygonometrii jako fale radiowe w krótkofalówkach.",
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement())
        ));
        Segment third = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Maszt i nadawanie internetu",
                "Fale radiowe nadawane przez maszty dają nam dostęp do internetu. Nauczmy się na ich przykładzie!",
                "Opis teorii powinien zawierać przykłady na temat tego jakie zastosowanie ma trygonometria w emitowaniu fal 5G które daja nam internet",
                "Zadanie powinno opisywać za pomocą trygonometrii geometrię i zastosowanie fal 5G. Potem, obliczmy za pomocą wartosci bezwzględnej wysokości masztu nadawczego nad poziomem morza ale również wysokość względną.",
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement(), SampleModulusLearningRequirement.getLearningRequirement())
        ));
        Segment fourth = seedSegment(third, new Seeding.SeededSegmentDetails(
                "Kapelusz grzyba jako parabola",
                "Zauważ kształty obecne wokół nas... Czy grzyby nie przypominają ci paraboli?",
                "Umieść w opisie teorii odnośniki do różnych rodzai grzybów. Wpleć to umiejętnie w zagadnienia teoretyczne tak, aby zaciekawić ucznia.",
                "Zadanie powinno porównywać odwróconą parabolę funkcji kwadratowej do kształtu kapelusza grzyba.",
                Set.of(SampleQuadraticFunctionLearningRequirement.getLearningRequirement())
        ));
        Segment fifth = seedSegment(third, new Seeding.SeededSegmentDetails(
                "Misja na planetę Zettarion",
                "Astronauci z Ziemi, Kapitan Alva i Doktor Nero, zostali wysłani na misję eksploracyjną na nowo odkrytą planetę Zettarion. Zettarion jest niezwykłą planetą, ponieważ jej powierzchnia składa się z tajemniczych kryształów energetycznych o różnych właściwościach. Kryształy te dzielą się na dwie kategorie: kryształy A i kryształy B. Każdy kryształ emituje różną ilość energii, mierzoną w jednostkach absolutnych (wartość bezwzględna), a zbiory kryształów są szczególnie ważne dla rozwiązania zagadki planetarnej energii.",
                "Umieść w opisie teoretycznym odnośniki i metafory do tego jak zagadnienia mogą być wykorzystywane w odkrywaniu kosmosu.",
                """
                        Niech zadanie dotyczy poniższej fabuły:
                        Astronauci z Ziemi, Kapitan Alva i Doktor Nero, zostali wysłani na misję eksploracyjną na nowo odkrytą planetę Zettarion. Zettarion jest niezwykłą planetą, ponieważ jej powierzchnia składa się z tajemniczych kryształów energetycznych o różnych właściwościach. Kryształy te dzielą się na dwie kategorie: **kryształy A** i **kryształy B**. Każdy kryształ emituje różną ilość energii, mierzoną w jednostkach absolutnych (wartość bezwzględna), a zbiory kryształów są szczególnie ważne dla rozwiązania zagadki planetarnej energii.
                        """,
                Set.of(SampleModulusLearningRequirement.getLearningRequirement(), SampleSetsLearningRequirement.getLearningRequirement())
        ));
    }

    ;

    private void seedSegmentsInThirdLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Uczniowie na maturze",
                "Sytuacja z którą pewnie możesz się utożsamiać...",
                "Zaprezentuj że trygonometria może opisywać częstotliwość w różnych badaniach i w społeczeństwie.",
                "Zadanie powinno opisywać częstotliwość stresowania się uczniów na maturze jako funkcja trygonometryczna.",
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement())
        ));
        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Wykresy i inne",
                "Tym razem bardziej matematycznie...",
                "Spróbuj uczniowi opisać korelację i podobieństwa funkcji kwadratowej i zagadnień trygonometrycznych na płaszczyźnie kartezjańskiej.",
                "Niech zadanie poleci uczniowi narysowanie wykresów na osobnej kartce i opisanie ich w odpowiedzi na zadanie.",
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement(), SampleQuadraticFunctionLearningRequirement.getLearningRequirement())
        ));
    }

    ;

    private void seedSegmentsInFourthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Praca domowa dla uczniów",
                "Uczniowie nie lubią prac domowych!",
                "Zaprezentuj funkcje kwadratową oraz jej monotoniczność na przykładzie uczniów którzy nie lubią prac domowych. Niech opis nakreśli w wyobraźni ucznia wykres mający kształt paraboli, opisujący to ile uczniów nie lubi robić prac domowych.",
                "Niech zadanie zakłada że zależność ilości uczniów robiących prace domowe do zadawanych prac domowych ma kształt funkcji kwadratowej o ujemnym współczynniku. Niech uczeń obliczy właściwości tej funkcji.",
                Set.of(SampleQuadraticFunctionLearningRequirement.getLearningRequirement())
        ));

        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
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
                Set.of(SampleQuadraticFunctionLearningRequirement.getLearningRequirement(), SampleSetsLearningRequirement.getLearningRequirement())
        ));

        Segment third = seedSegment(second, new Seeding.SeededSegmentDetails(
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
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement(), SampleSetsLearningRequirement.getLearningRequirement())
        ));
    }

    ;

    private void seedSegmentsInFifthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Wyłowienie titanica",
                "Rozwiążmy zagadkę: jak wyłowić statek Titanic?",
                "Zaprezentuj jak można wykorzystać naukowy kontekst i wymagania nauczania do potencjalnej misji wyłowienia zatopionego statku.",
                "Niech zadanie stawia na kreatywność ucznia. Powinno zawierać pewne wskazówki co do tego jak możnaby było użyć matematycznych właściwości aby wyłowić titanica. Opisz maszyny które możnaby wykorzystać i niech uczeń wykorzysta działania matematyczne aby maszyny wyłowiły zatopiony statek.",
                Set.of(SampleQuadraticFunctionLearningRequirement.getLearningRequirement(), SampleTrigonometryLearningRequirement.getLearningRequirement())
        ));

        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Konstruowanie helikoptera",
                "Rozważmy funkcjonalności helikoptera w kontekście matematycznym...",
                "Niech teoretyczny kontekst zawiera mało tekstu i dużo przykładów z prostymi wytłumaczeniami. Tłumacząc, odwołuj się do konstrukcji maszyn latających na przykład helikoptera.",
                "Niech zadanie stawia na kreatywność ucznia. Niech uczeń najpierw obliczy częstotliwość obrotu śmigieł helikoptera. Potem niech obliczy wartości bezwzględne na podstawie wysokości budynków i wysokości na której leci helikopter.",
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement(), SampleModulusLearningRequirement.getLearningRequirement())
        ));

        Segment third = seedSegment(second, new Seeding.SeededSegmentDetails(
                "Nauka z dinozaurami",
                " Na terenach dzisiejszej Patagonii żyły dwa gigantyczne dinozaury, Brachiosaurus i Tyrannosaurus Rex. Pewnego dnia postanowiły spotkać się na polanie w środku gęstego lasu...",
                "Niech opis będzie zawierał referencje dotyczące fabuły: Na terenach dzisiejszej Patagonii żyły dwa gigantyczne dinozaury, Brachiosaurus i Tyrannosaurus Rex. Pewnego dnia postanowiły spotkać się na polanie w środku gęstego lasu...",
                """
                        Na terenach dzisiejszej Patagonii żyły dwa gigantyczne dinozaury, Brachiosaurus i Tyrannosaurus Rex. Pewnego dnia postanowiły spotkać się na polanie w środku gęstego lasu.
                        Dopasuj zadanie tak, aby wykorzystywało otaczające drzewa czy długości ciał dinozaurów do obliczeń.
                        """,
                Set.of(SampleTrigonometryLearningRequirement.getLearningRequirement(), SampleModulusLearningRequirement.getLearningRequirement())
        ));
    }
}

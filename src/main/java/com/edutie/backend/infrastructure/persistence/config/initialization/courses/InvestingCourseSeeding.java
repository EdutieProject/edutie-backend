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
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.finance.*;
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class InvestingCourseSeeding {
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

    public void investingCourseSeeding(Course course) {
        seedSampleLessonsInCourse(course);
    }


    private void seedSampleLessonsInCourse(Course course) {
        Lesson firstLesson = Lesson.create(course.getAuthorEducator(), course);
        firstLesson.setName("Wstęp do rynku akcji");
        lessonPersistence.save(firstLesson);
        seedSegmentsInFirstLesson(firstLesson);

        Lesson secondLesson = Lesson.create(course.getAuthorEducator(), firstLesson);
        secondLesson.setName("Siły rządzące rynkiem");
        lessonPersistence.save(secondLesson);
        seedSegmentsInSecondLesson(secondLesson);

        Lesson thirdLesson = Lesson.create(course.getAuthorEducator(), firstLesson);
        thirdLesson.setName("Aktywa i instrumenty finansowe");
        lessonPersistence.save(thirdLesson);
        seedSegmentsInThirdLesson(thirdLesson);

        Lesson additionalLesson = Lesson.create(course.getAuthorEducator(), thirdLesson);
        additionalLesson.setName("Inwestycje alternatywne");
        lessonPersistence.save(additionalLesson);
        seedSegmentsInAdditionalLesson(additionalLesson);

        Lesson fourthLesson = Lesson.create(course.getAuthorEducator(), secondLesson);
        fourthLesson.setName("Inwestowanie w firmy");
        lessonPersistence.save(fourthLesson);
        seedSegmentsInFourthLesson(fourthLesson);

        Lesson fifthLesson = Lesson.create(course.getAuthorEducator(), fourthLesson);
        fifthLesson.setName("Cykl kredytowy");
        lessonPersistence.save(fifthLesson);
        seedSegmentsInFifthLesson(fifthLesson);

        Lesson sixthLesson = Lesson.create(course.getAuthorEducator(), fifthLesson);
        sixthLesson.setName("Hossa i bessa");
        lessonPersistence.save(sixthLesson);
        seedSegmentsInSixthLesson(sixthLesson);

        Lesson seventhLesson = Lesson.create(course.getAuthorEducator(), fifthLesson);
        seventhLesson.setName("Sektory");
        lessonPersistence.save(seventhLesson);
        seedSegmentsInSeventhLesson(seventhLesson);
    }

    private void seedSegmentsInFirstLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Czym jest akcja?",
                "Dowiedz się, czym jest akcja i jak daje inwestorowi udział w firmie.",
                "Wytłumacz, że akcje to udziały własnościowe w firmach, pozwalające na współdecydowanie (akcje zwykłe) lub jedynie na zyski (akcje uprzywilejowane).",
                "Uczeń analizuje przykładowe dane spółki i oblicza wartość swoich potencjalnych akcji.",
                Set.of(SampleStockBasicsLearningRequirement.getLearningRequirement())));

        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Rodzaje inwestycji",
                "Dowiedz się, jak akcje wpisują się w różne strategie inwestycyjne.",
                "Porównaj akcje, obligacje i fundusze ETF, wyjaśniając, jak różnią się pod względem ryzyka i potencjalnych zysków.",
                "Uczeń tworzy tabelę pokazującą korzyści i ryzyka różnych form inwestowania.",
                Set.of(SampleStockBasicsLearningRequirement.getLearningRequirement(), SampleCompanyTypesInvestmentLearningRequirement.getLearningRequirement())));

        Segment third = seedSegment(second, new Seeding.SeededSegmentDetails(
                "Skąd bierze się wartość akcji?",
                "Zrozum mechanizmy, które wpływają na cenę akcji, takie jak zyski firmy i sentyment rynku.",
                "Wyjaśnij, jak analiza fundamentalna i techniczna pomagają ocenić wartość akcji.",
                "Uczeń analizuje wycenę wybranej akcji, uwzględniając wskaźniki finansowe i dane rynkowe.",
                Set.of(SampleStockBasicsLearningRequirement.getLearningRequirement(), SampleStockValuationLearningRequirement.getLearningRequirement())));

        Segment fourth = seedSegment(third, new Seeding.SeededSegmentDetails(
                "Zarządzanie ryzykiem w inwestowaniu",
                "Poznaj podstawowe metody ograniczania ryzyka inwestycyjnego.",
                "Omów strategie takie jak dywersyfikacja portfela i korzystanie z zleceń stop-loss na przykładzie dynamicznego rynku.",
                "Uczeń projektuje portfel akcji, uwzględniając strategie redukcji ryzyka.",
                Set.of(SampleCompanyTypesInvestmentLearningRequirement.getLearningRequirement(), SampleStockValuationLearningRequirement.getLearningRequirement())));
    }


    private void seedSegmentsInSecondLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Czym są siły rynkowe?",
                "Dowiedz się, jak popyt i podaż wpływają na ceny akcji.",
                "Wytłumacz zjawiska takie jak bańki spekulacyjne, analizując przykłady historyczne.",
                "Uczeń bada wykresy zmian cen popularnych akcji i ocenia wpływ sił rynkowych na ich wartość.",
                Set.of(SampleMarketEfficiencyLearningRequirement.getLearningRequirement())));

        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Efektywność rynku a inwestowanie",
                "Dowiedz się, czym jest hipoteza rynku efektywnego (EMH).",
                "Przedstaw różne poziomy efektywności rynku (słaby, półsilny, silny) na przykładzie notowań akcji.",
                "Uczeń analizuje przypadki wycieków informacji i ich wpływ na ceny akcji.",
                Set.of(SampleMarketEfficiencyLearningRequirement.getLearningRequirement(), SampleStockValuationLearningRequirement.getLearningRequirement())));

        Segment third = seedSegment(second, new Seeding.SeededSegmentDetails(
                "Sentyment i emocje na rynku",
                "Poznaj wpływ irracjonalnych decyzji inwestorów na rynek.",
                "Przedstaw manie inwestycyjne (np. kryptowaluty w 2017 roku) i ich efekty na dłuższą metę.",
                "Uczeń analizuje dane rynkowe pod kątem wpływu emocji na zmienność cen.",
                Set.of(SampleSentimentalCyclesLearningRequirement.getLearningRequirement())));

        Segment fourth = seedSegment(third, new Seeding.SeededSegmentDetails(
                "Popyt i podaż w praktyce",
                "Zrozum, jak decyzje inwestorów indywidualnych i instytucjonalnych wpływają na rynek.",
                "Wyjaśnij interakcje na rynkach podczas sesji giełdowych, uwzględniając dane o wolumenach i trendach.",
                "Uczeń symuluje działanie na giełdzie w warunkach zmieniających się trendów popytowo-podażowych.",
                Set.of(SampleMarketEfficiencyLearningRequirement.getLearningRequirement(), SampleSentimentalCyclesLearningRequirement.getLearningRequirement())));
    }

    private void seedSegmentsInAdditionalLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Jak można inwestować inaczej?",
                "Poznaj różnorodne sposoby inwestowania poza tradycyjnymi rynkami akcji i obligacji.",
                "Wytłumacz, czym są inwestycje alternatywne, takie jak nieruchomości, kryptowaluty, sztuka czy surowce, oraz jakie mają zalety i wady.",
                "Uczeń przygotowuje analizę porównawczą różnych rodzajów inwestycji alternatywnych pod kątem ryzyka, płynności i potencjalnych zysków.",
                Set.of(SampleAlternativeInvestmentsLearningRequirement.getLearningRequirement())));

        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Efektywność rynku inwestycji alternatywnych",
                "Dowiedz się, czy rynki inwestycji alternatywnych są efektywne oraz jakie czynniki wpływają na ich funkcjonowanie.",
                "Wyjaśnij, jak teoria efektywności rynku odnosi się do mniej płynnych rynków, takich jak rynek sztuki czy nieruchomości, wykorzystując konkretne przykłady.",
                "Uczeń analizuje dane historyczne dotyczące inwestycji alternatywnych, próbując ocenić ich przewidywalność i efektywność.",
                Set.of(SampleMarketEfficiencyLearningRequirement.getLearningRequirement(), SampleAlternativeInvestmentsLearningRequirement.getLearningRequirement())));

        Segment third = seedSegment(second, new Seeding.SeededSegmentDetails(
                "Jak sentyment wpływa na inwestycje alternatywne?",
                "Poznaj wpływ emocji i trendów rynkowych na ceny aktywów alternatywnych.",
                "Omów przypadki manii inwestycyjnych, takich jak boom na kryptowaluty w 2017 roku, i wyjaśnij, jak sentyment kształtuje popyt na aktywa alternatywne.",
                "Uczeń analizuje zmienność cen aktywów alternatywnych w kontekście sentymentu rynkowego, identyfikując kluczowe trendy i punkty zwrotne.",
                Set.of(SampleSentimentalCyclesLearningRequirement.getLearningRequirement(), SampleAlternativeInvestmentsLearningRequirement.getLearningRequirement())));
    }

    private void seedSegmentsInThirdLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Czym są instrumenty finansowe?",
                "Dowiedz się, czym są instrumenty finansowe i jak dzielą się na różne kategorie.",
                "Wytłumacz pojęcia takie jak akcje, obligacje i fundusze ETF, korzystając z przykładów popularnych firm i rynków.",
                "Uczeń tworzy tabelę porównawczą różnych instrumentów finansowych, uwzględniając ich ryzyko, potencjalne zyski i płynność.",
                Set.of(SampleFinancialInstrumentsLearningRequirement.getLearningRequirement())));

        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Podział instrumentów finansowych",
                "Zrozum różnicę między instrumentami dłużnymi, kapitałowymi i pochodnymi.",
                "Podaj przykład instrumentów dłużnych (np. obligacje skarbowe), kapitałowych (np. akcje spółek) i pochodnych (np. opcje) w kontekście ich wykorzystania w inwestycjach.",
                "Uczeń analizuje przykład portfela inwestycyjnego i klasyfikuje znajdujące się w nim instrumenty.",
                Set.of(SampleFinancialInstrumentsLearningRequirement.getLearningRequirement())));

        Segment third = seedSegment(second, new Seeding.SeededSegmentDetails(
                "Instrumenty pochodne",
                "Poznaj zastosowanie instrumentów pochodnych w zarządzaniu ryzykiem i spekulacji.",
                "Wyjaśnij działanie kontraktów futures i opcji na przykładzie zabezpieczania ceny ropy naftowej przez linię lotniczą.",
                "Uczeń symuluje zakup opcji na akcje i obserwuje, jak zmiana ceny bazowego aktywa wpływa na wartość opcji.",
                Set.of(SampleFinancialInstrumentsLearningRequirement.getLearningRequirement(), SampleStockValuationLearningRequirement.getLearningRequirement())));
    }

    private void seedSegmentsInFourthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Czym jest inwestowanie w wartość?",
                "Poznaj strategię inwestowania w wartość, opartą na wyszukiwaniu niedoszacowanych firm.",
                "Wytłumacz, jak inwestorzy tacy jak Warren Buffett wybierają akcje spółek o solidnych fundamentach finansowych, wykorzystując wskaźniki takie jak P/E i P/B.",
                "Uczeń analizuje raport finansowy wybranej spółki i ocenia, czy jej akcje mogą być niedoszacowane.",
                Set.of(SampleCompanyTypesInvestmentLearningRequirement.getLearningRequirement(), SampleStockValuationLearningRequirement.getLearningRequirement())));

        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Czym jest inwestowanie we wzrost?",
                "Dowiedz się, jak inwestorzy skupiają się na firmach o wysokim potencjale wzrostu przychodów.",
                "Wyjaśnij strategię inwestowania w firmy technologiczne, takie jak Tesla czy Amazon, i ryzyka związane z tym podejściem.",
                "Uczeń analizuje dynamicznie rozwijającą się firmę i ocenia jej potencjał wzrostu przy użyciu danych finansowych.",
                Set.of(SampleCompanyTypesInvestmentLearningRequirement.getLearningRequirement())));

        Segment third = seedSegment(second, new Seeding.SeededSegmentDetails(
                "Styl mieszany – inwestowanie w wartość i wzrost",
                "Poznaj strategię łączącą inwestowanie w wartość i wzrost w zrównoważony sposób.",
                "Podaj przykłady inwestorów łączących te style, korzystając z danych rynkowych i wskaźników takich jak PEG.",
                "Uczeń tworzy symulowany portfel inwestycyjny, zawierający zarówno akcje wzrostowe, jak i wartościowe.",
                Set.of(SampleCompanyTypesInvestmentLearningRequirement.getLearningRequirement())));
    }

    private void seedSegmentsInFifthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Czym jest cykl kredytowy?",
                "Dowiedz się, jak zmiany w dostępności kredytów wpływają na gospodarkę i rynek akcji.",
                "Wytłumacz etapy cyklu kredytowego, takie jak ekspansja kredytowa i kontrakcja, na przykładzie kryzysu finansowego z 2008 roku.",
                "Uczeń analizuje dane dotyczące stóp procentowych i kredytów w gospodarce, próbując przewidzieć ich wpływ na rynek akcji.",
                Set.of(SampleCreditCycleImpactOnStockMarketLearningRequirement.getLearningRequirement())));

        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Wpływ stóp procentowych na rynek akcji",
                "Poznaj zależność między decyzjami banków centralnych a zmianami na rynku kapitałowym.",
                "Wytłumacz, jak podwyżki lub obniżki stóp procentowych wpływają na koszt kredytów i wycenę firm.",
                "Uczeń symuluje wpływ zmieniających się stóp procentowych na akcje wybranej firmy.",
                Set.of(SampleCreditCycleImpactOnStockMarketLearningRequirement.getLearningRequirement())));
    }

    private void seedSegmentsInSixthLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Czym jest hossa?",
                "Zrozum, czym jest hossa i jakie czynniki ją wywołują na rynku kapitałowym.",
                "Przedstaw przykłady hoss historycznych, takich jak wzrost rynków po 2009 roku, oraz ich główne przyczyny.",
                "Uczeń analizuje dane historyczne, identyfikując początki i końce wybranych okresów hossy.",
                Set.of(SampleEconomicCyclesLearningRequirement.getLearningRequirement(), SampleSentimentalCyclesLearningRequirement.getLearningRequirement())));

        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Czym jest bessa?",
                "Zrozum, czym jest bessa i jakie czynniki ją wywołują na rynku kapitałowym.",
                "Omów kryzysy gospodarcze, takie jak bessa z 2008 roku, i ich konsekwencje dla inwestorów.",
                "Uczeń analizuje dane historyczne, identyfikując kluczowe wydarzenia prowadzące do bessy.",
                Set.of(SampleEconomicCyclesLearningRequirement.getLearningRequirement(), SampleSentimentalCyclesLearningRequirement.getLearningRequirement())));
    }

    private void seedSegmentsInSeventhLesson(Lesson lesson) {
        Segment first = seedSegment(lesson, new Seeding.SeededSegmentDetails(
                "Czym są sektory na rynku akcji?",
                "Dowiedz się, jak spółki są grupowane w sektory na rynku kapitałowym.",
                "Przedstaw sektory takie jak technologie, energia czy opieka zdrowotna, i wskaż różnice w ich wynikach finansowych.",
                "Uczeń tworzy diagram przedstawiający różne sektory i porównuje ich historyczne stopy zwrotu.",
                Set.of(SampleSectorCorrelationsLearningRequirement.getLearningRequirement())));

        Segment second = seedSegment(first, new Seeding.SeededSegmentDetails(
                "Korelacje między sektorami",
                "Poznaj, jak sektory wpływają na siebie nawzajem oraz jak zmiany gospodarcze mogą zmieniać ich korelacje.",
                "Podaj przykład, jak rosnące ceny energii wpływają na sektor transportowy i konsumencki.",
                "Uczeń analizuje dane rynkowe i oblicza korelacje między sektorami w różnych okresach gospodarczych.",
                Set.of(SampleSectorCorrelationsLearningRequirement.getLearningRequirement())));

        Segment third = seedSegment(second, new Seeding.SeededSegmentDetails(
                "Rotacja sektorowa",
                "Dowiedz się, jak inwestorzy zmieniają swoje portfele, podążając za trendami w sektorach.",
                "Wyjaśnij strategię rotacji sektorowej na przykładzie wzrostu popularności technologii po pandemii COVID-19.",
                "Uczeń projektuje portfel z uwzględnieniem trendów w wybranych sektorach.",
                Set.of(SampleSectorCorrelationsLearningRequirement.getLearningRequirement(), SampleSentimentalCyclesLearningRequirement.getLearningRequirement())));
    }


}

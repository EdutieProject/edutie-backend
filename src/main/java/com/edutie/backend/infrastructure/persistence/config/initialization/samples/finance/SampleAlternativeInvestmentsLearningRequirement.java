package com.edutie.backend.infrastructure.persistence.config.initialization.samples.finance;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleAlternativeInvestmentsLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        if (learningRequirementPersistence.getRepository().findAll().stream().anyMatch(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME))) {
            requirement = learningRequirementPersistence.getRepository().findAll().stream().filter(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME)).findFirst().get();
            isSeeded = true;
            return;
        }
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Wprowadzenie do inwestycji alternatywnych",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Inwestowanie w alkohole: wina, whisky, piwa",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Przedmioty kolekcjonerskie: sztuka, monety, znaczki",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Inwestowanie w zegarki luksusowe",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirement.appendSubRequirement(
                "Surowce: złoto, srebro, ropa naftowa",
                PromptFragment.of(SUB_REQUIREMENT_5)
        );
        learningRequirement.appendSubRequirement(
                "Inwestycje alternatywne w nieruchomości",
                PromptFragment.of(SUB_REQUIREMENT_6)
        );
        learningRequirement.appendSubRequirement(
                "Zalety i wady inwestycji alternatywnych",
                PromptFragment.of(SUB_REQUIREMENT_7)
        );
        learningRequirement.appendSubRequirement(
                "Ryzyka związane z inwestycjami alternatywnymi",
                PromptFragment.of(SUB_REQUIREMENT_8)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleAlternativeInvestmentsLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Inwestycje alternatywne";

    public static final String SUB_REQUIREMENT_1 = """
            ### Wprowadzenie do inwestycji alternatywnych
            Inwestycje alternatywne to rodzaj inwestycji, które różnią się od tradycyjnych aktywów, takich jak akcje czy obligacje. Są to np. alkohole, przedmioty kolekcjonerskie, zegarki czy surowce. Inwestowanie w te aktywa może oferować różne korzyści, ale wiąże się także z wyzwaniami i ryzykiem. Inwestycje alternatywne mogą pełnić funkcję dywersyfikacyjną w portfelu inwestycyjnym, a ich atrakcyjność wzrasta w okresach niepewności na tradycyjnych rynkach finansowych.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Inwestowanie w alkohole: wina, whisky, piwa
            Inwestycje w alkohole, takie jak wina, whisky czy piwa, stają się coraz popularniejsze. Wina, zwłaszcza te z renomowanych winnic, mogą zyskiwać na wartości z upływem czasu. Whisky i inne alkohole mogą osiągać wysokie ceny na aukcjach, zwłaszcza te z limitowanych edycji. Inwestowanie w alkohole wiąże się z potrzebą specjalistycznej wiedzy, aby skutecznie rozpoznać produkty, które będą rosły na wartości.
            - **Wina** – inwestowanie w wina polega na nabywaniu butelek, które będą zyskiwać na wartości.
            - **Whisky** – limitowane edycje, butelki z długą historią mogą osiągać wysokie ceny na rynku wtórnym.
            - **Piwa** – piwa rzemieślnicze lub limitowane edycje mogą również stanowić wartość inwestycyjną, choć rynek ten jest mniejszy niż w przypadku wina czy whisky.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Przedmioty kolekcjonerskie: sztuka, monety, znaczki
            Inwestycje w przedmioty kolekcjonerskie, takie jak dzieła sztuki, monety czy znaczki pocztowe, są jednym z najstarszych rodzajów inwestycji alternatywnych. Te przedmioty mogą zyskiwać na wartości na podstawie ich rzadkości, jakości oraz popytu ze strony kolekcjonerów. Istnieje także potencjał wzrostu wartości związany z historycznym lub kulturowym znaczeniem.
            - **Sztuka** – dzieła sztuki, zwłaszcza te uznanych artystów, mogą być bardzo cenne na rynku aukcyjnym.
            - **Monety** – rzadkie monety mogą osiągać wysokie ceny w zależności od ich wieku, stanu zachowania i rzadkości.
            - **Znaczki** – kolekcjonowanie znaczków pocztowych to pasja, która może również przynieść zyski na rynku wtórnym.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Inwestowanie w zegarki luksusowe
            Zegarki luksusowe stały się popularnym instrumentem inwestycyjnym, który może przynieść zyski, zwłaszcza w przypadku limitowanych edycji lub zegarków od renomowanych marek, takich jak Rolex czy Patek Philippe. Zegarki mogą zyskiwać na wartości dzięki ich unikalności, prestiżowi oraz popytowi na rynku wtórnym. Często inwestowanie w zegarki wymaga specjalistycznej wiedzy o poszczególnych modelach i ich rynku.
            """;

    public static final String SUB_REQUIREMENT_5 = """
            ### Surowce: złoto, srebro, ropa naftowa
            Inwestycje w surowce to klasyczny sposób ochrony przed inflacją oraz inwestowanie w aktywa o trwałej wartości. Złoto i srebro są powszechnie uważane za bezpieczne przystanie inwestycyjne w czasach niepewności gospodarczej. Ropa naftowa oraz inne surowce energetyczne stanowią ważną część globalnej gospodarki i mogą oferować zyski w zależności od zmienności cen na rynku.
            - **Złoto** – najpopularniejszy surowiec inwestycyjny, szczególnie w formie sztabek lub monet.
            - **Srebro** – mniej kosztowne niż złoto, ale również cenione przez inwestorów.
            - **Ropa naftowa** – surowiec energetyczny, którego ceny mogą podlegać dużym fluktuacjom.
            """;

    public static final String SUB_REQUIREMENT_6 = """
            ### Inwestycje alternatywne w nieruchomości
            Nieruchomości są jedną z popularniejszych form inwestycji alternatywnych, zwłaszcza w kontekście wynajmu lub zakupów na rynku wtórnym. Nieruchomości komercyjne, mieszkalne czy grunty mogą zapewniać stabilny dochód pasywny, a ich wartość może rosnąć w zależności od lokalizacji i stanu rynku nieruchomości.
            - **Nieruchomości mieszkalne** – zakup mieszkań lub domów w celach inwestycyjnych.
            - **Nieruchomości komercyjne** – inwestowanie w biura, sklepy, centra handlowe, które generują dochody z wynajmu.
            - **Grunty** – kupowanie ziemi, która może zyskać na wartości w wyniku zmian w planie zagospodarowania przestrzennego.
            """;

    public static final String SUB_REQUIREMENT_7 = """
            ### Zalety i wady inwestycji alternatywnych
            Inwestycje alternatywne oferują różne korzyści, takie jak dywersyfikacja portfela, potencjał wysokich zysków oraz odporność na zmiany na tradycyjnych rynkach finansowych. Jednak wiążą się także z pewnymi wadami:
            - **Zalety** – dywersyfikacja ryzyka, możliwość wysokich zysków, ochrona przed inflacją.
            - **Wady** – brak płynności, trudności w wycenie, ryzyko związane z rynkiem specyficznym dla danego rodzaju inwestycji.
            """;

    public static final String SUB_REQUIREMENT_8 = """
            ### Ryzyka związane z inwestycjami alternatywnymi
            Inwestowanie w alternatywne aktywa wiąże się z różnymi rodzajami ryzyka:
            - **Ryzyko płynności** – trudności w sprzedaży aktywa w przypadku potrzeby szybkiej realizacji inwestycji.
            - **Ryzyko oceny wartości** – brak jednolitych standardów wyceny może prowadzić do trudności w określeniu wartości rynkowej.
            - **Ryzyko rynkowe** – zmiany popytu, regulacje prawne oraz inne czynniki mogą wpłynąć na wartość danego aktywa.
            """;
}

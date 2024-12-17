package com.edutie.backend.infrastructure.persistence.config.initialization.samples.finance;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;

public class SampleCompanyTypesInvestmentLearningRequirement {
    private static boolean isSeeded = false;
    private static LearningRequirement requirement = null;

    public static void seedInDatabase(Educator educator, LearningRequirementPersistence learningRequirementPersistence) {
        if (learningRequirementPersistence.getRepository().findAll().stream().anyMatch(o -> o.getName().equals(LEARNING_REQUIREMENT_NAME))) {
            isSeeded = true;
            return;
        }
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.setName(LEARNING_REQUIREMENT_NAME);
        learningRequirement.appendSubRequirement(
                "Wprowadzenie do rodzajów firm na rynku akcji",
                PromptFragment.of(SUB_REQUIREMENT_1)
        );
        learningRequirement.appendSubRequirement(
                "Inwestowanie w wartość: analiza spółek wartościowych",
                PromptFragment.of(SUB_REQUIREMENT_2)
        );
        learningRequirement.appendSubRequirement(
                "Inwestowanie w wzrost: analiza spółek wzrostowych",
                PromptFragment.of(SUB_REQUIREMENT_3)
        );
        learningRequirement.appendSubRequirement(
                "Styl mieszany: połączenie inwestowania w wartość i wzrost",
                PromptFragment.of(SUB_REQUIREMENT_4)
        );
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        isSeeded = true;
        requirement = learningRequirement;
    }

    public static LearningRequirement getLearningRequirement() {
        if (!isSeeded) {
            throw new RuntimeException(SampleCompanyTypesInvestmentLearningRequirement.class.getSimpleName() + " not seeded!");
        }
        return requirement;
    }

    public static final String LEARNING_REQUIREMENT_NAME = "Rodzaje firm na rynku akcji";

    public static final String SUB_REQUIREMENT_1 = """
            ### Wprowadzenie do rodzajów firm na rynku akcji
            Na rynku akcji istnieją różne typy firm, w które można inwestować. Firmy te różnią się pod względem tempa wzrostu, stabilności finansowej, sektora działalności oraz poziomu ryzyka. Zrozumienie tych rodzajów firm jest kluczowe dla inwestora, który chce wybrać odpowiednią strategię inwestycyjną i dostosować swoje inwestycje do celów finansowych.
            """;

    public static final String SUB_REQUIREMENT_2 = """
            ### Inwestowanie w wartość: analiza spółek wartościowych
            Inwestowanie w wartość polega na poszukiwaniu akcji firm, które są obecnie niedowartościowane w stosunku do ich wewnętrznej wartości. Inwestorzy kierują się zasadą, że rynek często wycenia firmy poniżej ich rzeczywistej wartości, oferując w ten sposób atrakcyjne możliwości inwestycyjne. Firmy wartościowe zazwyczaj charakteryzują się stabilnymi wynikami finansowymi, solidnymi fundamentami oraz niskimi wskaźnikami wyceny, takimi jak cena do zysku (P/E) czy cena do wartości księgowej (P/B).
            - **Cechy firm wartościowych**: stabilne zyski, niski poziom zadłużenia, wypłata dywidend.
            - **Przykłady**: spółki z tradycyjnych branż, takich jak energetyka, przemysł, sektor finansowy.
            """;

    public static final String SUB_REQUIREMENT_3 = """
            ### Inwestowanie w wzrost: analiza spółek wzrostowych
            Inwestowanie w wzrost polega na poszukiwaniach firm, które mają duży potencjał wzrostu w przyszłości, ale które często są wyceniane wyżej niż inne firmy z powodu swoich przyszłych perspektyw. Inwestorzy skupiają się na firmach, które wykazują szybki rozwój przychodów, zysków lub innowacyjnych produktów. Spółki wzrostowe mają tendencję do reinwestowania swoich zysków w rozwój, a ich wycena może być znacznie wyższa niż w przypadku firm wartościowych.
            - **Cechy firm wzrostowych**: wysoka dynamika wzrostu przychodów i zysków, innowacyjność, duży potencjał na rynkach wschodzących.
            - **Przykłady**: firmy technologiczne, start-upy, firmy z sektora biotechnologii.
            """;

    public static final String SUB_REQUIREMENT_4 = """
            ### Styl mieszany: połączenie inwestowania w wartość i wzrost
            Styl mieszany polega na łączeniu dwóch podejść: inwestowania w wartość oraz inwestowania w wzrost. Inwestorzy stosujący ten styl starają się wybrać firmy, które łączą cechy obu typów. Mogą inwestować w firmy, które oferują zarówno atrakcyjne wyceny (wartość), jak i solidne perspektywy wzrostu. Styl mieszany jest bardziej zrównoważony, gdyż pozwala na dywersyfikację portfela oraz równoważenie ryzyka z potencjałem wzrostu.
            - **Cechy firm w stylu mieszanym**: solidne fundamenty finansowe, potencjał wzrostu w przyszłości, rozsądna wycena.
            - **Przykłady**: spółki technologiczne, które są już dobrze ustabilizowane, ale nadal mają duży potencjał wzrostu (np. liderzy w swoich branżach).
            """;
}

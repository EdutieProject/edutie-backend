package com.edutie.backend.domain.personalization.learningresourcegenerationschema.details;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.common.PersonalizedDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.rules.TheoryPersonalizationRule;
import com.edutie.backend.domain.personalization.student.Student;
import lombok.Getter;

import java.util.List;

/**
 * Details of the theory generation but with the personalization rules
 */
@Getter
public class TheoryPersonalizedDetails extends PersonalizedDetails<TheoryPersonalizationRule> {
    private PromptFragment overviewDescription;
    private PromptFragment graphDescription;

    private static TheoryPersonalizedDetails initializeFrom(TheoryDetails theoryDetails) {
        TheoryPersonalizedDetails theoryPersonalizationDetails = new TheoryPersonalizedDetails();
        theoryPersonalizationDetails.overviewDescription = theoryDetails.getOverviewDescription();
        theoryPersonalizationDetails.graphDescription = theoryDetails.getGraphDescription();
        return theoryPersonalizationDetails;
    }

    public static TheoryPersonalizedDetails create(List<KnowledgeCorrelation> knowledgeCorrelations,
                                                   TheoryDetails theoryDetails,
                                                   Student student) {
        TheoryPersonalizedDetails theoryPersonalizationDetails = initializeFrom(theoryDetails);
        theoryPersonalizationDetails.createPersonalizationRules(knowledgeCorrelations, student);
        return theoryPersonalizationDetails;
    }
}

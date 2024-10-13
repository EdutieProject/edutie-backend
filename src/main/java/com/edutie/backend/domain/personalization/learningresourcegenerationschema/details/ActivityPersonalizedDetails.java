package com.edutie.backend.domain.personalization.learningresourcegenerationschema.details;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.details.base.PersonalizedDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.rules.ActivityPersonalizationRule;
import com.edutie.backend.domain.personalization.student.Student;
import lombok.Getter;

import java.util.List;

/**
 * Details of the activity generation but with the personalization rules
 */
@Getter
public class ActivityPersonalizedDetails extends PersonalizedDetails<ActivityPersonalizationRule> {
    private PromptFragment exerciseDescription;
    private PromptFragment hintsDescription;

    private static ActivityPersonalizedDetails initializeFrom(ActivityDetails activityDetails) {
        ActivityPersonalizedDetails activityPersonalizationDetails = new ActivityPersonalizedDetails();
        activityPersonalizationDetails.exerciseDescription = activityDetails.getExerciseDescription();
        activityPersonalizationDetails.hintsDescription = activityDetails.getHintsDescription();
        return activityPersonalizationDetails;
    }

    public static ActivityPersonalizedDetails create(List<KnowledgeCorrelation> knowledgeCorrelations,
                                                     ActivityDetails activityDetails,
                                                     Student student) {
        ActivityPersonalizedDetails activityPersonalizationDetails = initializeFrom(activityDetails);
        activityPersonalizationDetails.createPersonalizationRules(knowledgeCorrelations, student);
        return activityPersonalizationDetails;
    }
}

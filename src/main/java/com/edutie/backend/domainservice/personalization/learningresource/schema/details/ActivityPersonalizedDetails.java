package com.edutie.backend.domainservice.personalization.learningresource.schema.details;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.common.PersonalizedDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domainservice.personalization.learningresource.schema.details.rules.ActivityPersonalizationRule;
import lombok.Getter;

import java.util.Set;

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

    public static ActivityPersonalizedDetails create(ActivityDetails activityDetails,
                                                     Student student,
                                                     LearningResultPersistence learningResultPersistence,
                                                     Set<KnowledgeCorrelation> knowledgeCorrelations) {
        ActivityPersonalizedDetails activityPersonalizationDetails = initializeFrom(activityDetails);
        activityPersonalizationDetails.createPersonalizationRules(student, learningResultPersistence, knowledgeCorrelations);
        return activityPersonalizationDetails;
    }

    @Override
    protected void createPersonalizationRules(Student student, LearningResultPersistence learningResultPersistence, Set<KnowledgeCorrelation> knowledgeCorrelations) {

    }
}

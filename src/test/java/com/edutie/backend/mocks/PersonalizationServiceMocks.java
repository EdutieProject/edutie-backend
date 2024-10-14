package com.edutie.backend.mocks;

import com.edutie.backend.domainservice.personalization.learningresource.schema.details.ActivityPersonalizedDetails;
import com.edutie.backend.domainservice.personalization.learningresource.schema.details.TheoryPersonalizedDetails;
import com.edutie.backend.domainservice.personalization.activity.ActivityPersonalizationService;
import com.edutie.backend.domainservice.personalization.theory.TheoryPersonalizationService;
import validation.WrapperResult;

import java.util.List;

public class PersonalizationServiceMocks {
    public static ActivityPersonalizationService activityPersonalizationServiceMock() {
        return (details, student, knowledgeCorrelations) -> WrapperResult.successWrapper(ActivityPersonalizedDetails.create(List.of(), details, student));
    }

    public static TheoryPersonalizationService theoryPersonalizationServiceMock() {
        return (details, student, knowledgeCorrelations) -> WrapperResult.successWrapper(TheoryPersonalizedDetails.create(List.of(), details, student));
    }
}

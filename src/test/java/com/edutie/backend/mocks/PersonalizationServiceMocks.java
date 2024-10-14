package com.edutie.backend.mocks;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.details.ActivityPersonalizedDetails;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.details.TheoryPersonalizedDetails;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domainservice.personalization.activity.ActivityPersonalizationService;
import com.edutie.backend.domainservice.personalization.theory.TheoryPersonalizationService;
import validation.WrapperResult;

import java.util.List;
import java.util.Set;

public class PersonalizationServiceMocks {
    public static ActivityPersonalizationService activityPersonalizationServiceMock() {
        return (details, student, knowledgeCorrelations) -> WrapperResult.successWrapper(ActivityPersonalizedDetails.create(List.of(), details, student));
    }

    public static TheoryPersonalizationService theoryPersonalizationServiceMock() {
        return (details, student, knowledgeCorrelations) -> WrapperResult.successWrapper(TheoryPersonalizedDetails.create(List.of(), details, student));
    }
}

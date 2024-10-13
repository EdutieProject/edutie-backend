package com.edutie.backend.domainservice.personalization.activity;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.details.ActivityPersonalizedDetails;
import com.edutie.backend.domain.personalization.student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.WrapperResult;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ActivityPersonalizationServiceImplementation implements ActivityPersonalizationService {
    @Override
    public WrapperResult<ActivityPersonalizedDetails> personalize(ActivityDetails details, Student student, Set<KnowledgeCorrelation> knowledgeCorrelations) {
        return WrapperResult.failureWrapper(new Error("NOT-IMPLEMENTED", ""));
    }
}

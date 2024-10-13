package com.edutie.backend.domainservice.personalization.theory;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.details.TheoryPersonalizedDetails;
import com.edutie.backend.domain.personalization.student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.WrapperResult;

import java.util.Set;

@RequiredArgsConstructor
@Component
public class TheoryPersonalizationServiceImplementation implements TheoryPersonalizationService {
    @Override
    public WrapperResult<TheoryPersonalizedDetails> personalize(TheoryDetails details, Student student, Set<KnowledgeCorrelation> knowledgeCorrelations) {
        return WrapperResult.failureWrapper(new Error("NOT-IMPLEMENTED", ""));
    }
}

package com.edutie.backend.domainservice.personalization;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.common.Personalizable;
import com.edutie.backend.domain.personalization.student.Student;
import validation.WrapperResult;

import java.util.Set;

public interface PersonalizationService<TPersonalized, T extends Personalizable> {
    WrapperResult<TPersonalized> personalize(T details, Student student, Set<KnowledgeCorrelation> knowledgeCorrelations);
}

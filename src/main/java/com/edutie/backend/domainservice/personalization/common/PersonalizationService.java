package com.edutie.backend.domainservice.personalization.common;

import com.edutie.backend.domain.personalization.common.AbsoluteDefinition;
import com.edutie.backend.domain.personalization.student.Student;
import validation.WrapperResult;

public interface PersonalizationService<TPersonalized, TDefinition extends AbsoluteDefinition> {
    WrapperResult<TPersonalized> personalize(TDefinition absoluteDefinition, Student student);
}

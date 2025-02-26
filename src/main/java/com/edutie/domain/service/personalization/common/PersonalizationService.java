package com.edutie.domain.service.personalization.common;

import com.edutie.domain.core.personalization.common.AbsoluteDefinition;
import com.edutie.domain.core.learning.student.Student;
import validation.WrapperResult;

public interface PersonalizationService<TPersonalized, TDefinition extends AbsoluteDefinition> {
    WrapperResult<TPersonalized> personalize(TDefinition absoluteDefinition, Student student);
}

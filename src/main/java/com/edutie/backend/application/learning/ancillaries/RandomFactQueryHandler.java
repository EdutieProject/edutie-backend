package com.edutie.backend.application.learning.ancillaries;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.ancillaries.queries.RandomFactQuery;
import com.edutie.backend.application.learning.ancillaries.viewmodels.RandomFact;
import validation.WrapperResult;

public interface RandomFactQueryHandler extends UseCaseHandler<WrapperResult<RandomFact>, RandomFactQuery> {
}

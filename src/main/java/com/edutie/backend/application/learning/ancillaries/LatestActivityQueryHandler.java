package com.edutie.backend.application.learning.ancillaries;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.ancillaries.queries.LatestActivityQuery;
import com.edutie.backend.application.learning.ancillaries.queries.RandomFactQuery;
import com.edutie.backend.application.learning.ancillaries.viewmodels.LatestActivityView;
import validation.WrapperResult;

public interface LatestActivityQueryHandler extends UseCaseHandler<WrapperResult<LatestActivityView>, LatestActivityQuery> {
}

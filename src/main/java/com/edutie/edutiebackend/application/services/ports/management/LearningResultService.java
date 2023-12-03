package com.edutie.edutiebackend.application.services.ports.management;

import com.edutie.edutiebackend.application.services.ports.common.GenericCrudService;
import com.edutie.edutiebackend.domain.core.learningresult.LearningResult;
import com.edutie.edutiebackend.domain.core.learningresult.identities.LearningResultId;

public interface LearningResultService extends GenericCrudService<LearningResult, LearningResultId> {
}

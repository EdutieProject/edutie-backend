package com.edutie.edutiebackend.application.services.ports.agreggates;

import com.edutie.edutiebackend.application.services.ports.crud.GenericCrudService;
import com.edutie.edutiebackend.domain.core.learningresult.LearningResult;

import java.util.UUID;

public interface LearningResultService extends GenericCrudService<LearningResult, UUID> {
}

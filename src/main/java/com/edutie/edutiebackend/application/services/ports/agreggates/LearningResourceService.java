package com.edutie.edutiebackend.application.services.ports.agreggates;

import com.edutie.edutiebackend.application.services.ports.crud.GenericCrudService;
import com.edutie.edutiebackend.domain.core.learningresource.LearningResource;

import java.util.UUID;

public interface LearningResourceService extends GenericCrudService<LearningResource, UUID> {
}

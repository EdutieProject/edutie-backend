package com.edutie.edutiebackend.application.services.ports.agreggates;

import com.edutie.edutiebackend.application.services.ports.crud.GenericCrudService;
import com.edutie.edutiebackend.domain.core.learningresource.LearningResource;
import com.edutie.edutiebackend.domain.core.learningresource.identities.LearningResourceId;


public interface LearningResourceService extends GenericCrudService<LearningResource, LearningResourceId> {
}

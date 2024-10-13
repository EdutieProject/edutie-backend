package com.edutie.backend.domainservice.personalization.activity;

import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.details.ActivityPersonalizedDetails;
import com.edutie.backend.domainservice.personalization.PersonalizationService;

public interface ActivityPersonalizationService extends PersonalizationService<ActivityPersonalizedDetails, ActivityDetails> {
}

package com.edutie.infrastructure.persistence.implementation.learning;

import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.LearningExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class LearningExperiencePersistenceImplementation implements LearningExperiencePersistence {
    private final LearningExperienceRepository learningExperienceRepository;

    //TODO
}

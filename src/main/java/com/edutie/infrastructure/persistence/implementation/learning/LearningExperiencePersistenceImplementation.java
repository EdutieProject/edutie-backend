package com.edutie.infrastructure.persistence.implementation.learning;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.infrastructure.persistence.base.GenericMultipleRepositoryPersistence;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningexperience.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class LearningExperiencePersistenceImplementation
        extends GenericMultipleRepositoryPersistence<LearningExperience<?>, LearningExperienceId> implements LearningExperiencePersistence {
    private final LearningExperienceRepository learningExperienceRepository;
    private final SimpleProblemActivityLearningExperienceRepository simpleProblemActivityLearningExperienceRepository;
    private final ScenarioProblemSolvingActivityLearningExperienceRepository scenarioProblemSolvingActivityLearningExperienceRepository;
    private final StoryBasedActivityLearningExperienceRepository storyBasedActivityLearningExperienceRepository;
    private final OnlineDiscussionSimulationActivityLearningExperienceRepository onlineDiscussionSimulationActivityLearningExperienceRepository;


    @Override
    protected Class<? extends JpaRepository<?, ?>>[] getOmittedRepositoryClasses() {
        return new Class[]{LearningExperienceRepository.class};
    }

    @Override
    protected JpaRepository<LearningExperience<?>, LearningExperienceId> getPolymorphicRepository() {
        return learningExperienceRepository;
    }
}

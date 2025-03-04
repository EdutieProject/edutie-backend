package com.edutie.infrastructure.persistence.implementation.learning;

import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.identities.LearningResultId;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.infrastructure.persistence.base.GenericMultipleRepositoryPersistence;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningresult.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;


@Component
@RequiredArgsConstructor
public class LearningResultPersistenceImplementation
        extends GenericMultipleRepositoryPersistence<LearningResult<?>, LearningResultId> implements LearningResultPersistence {
    private final LearningResultRepository learningResultRepository;
    private final OnlineDiscussionSimulationActivityLearningResultRepository onlineDiscussionSimulationActivityLearningResultRepository;
    private final ScenarioProblemSolvingActivityLearningResultRepository scenarioProblemSolvingActivityLearningResultRepository;
    private final SimpleProblemActivityLearningResultRepository simpleProblemActivityLearningResultRepository;
    private final StoryBasedActivityLearningResultRepository storyBasedActivityLearningResultRepository;


    @Override
    protected Class<? extends JpaRepository<?, ?>>[] getOmittedRepositoryClasses() {
        return new Class[]{ LearningResultRepository.class };
    }

    @Override
    protected JpaRepository<LearningResult<?>, LearningResultId> getPolymorphicRepository() {
        return learningResultRepository;
    }
}

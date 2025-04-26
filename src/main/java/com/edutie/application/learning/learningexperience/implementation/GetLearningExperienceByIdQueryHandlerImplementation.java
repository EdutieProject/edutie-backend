package com.edutie.application.learning.learningexperience.implementation;

import com.edutie.application.learning.learningexperience.GetLearningExperienceByIdQueryHandler;
import com.edutie.application.learning.learningexperience.query.GetLearningExperienceByIdQuery;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class GetLearningExperienceByIdQueryHandlerImplementation implements GetLearningExperienceByIdQueryHandler {
    private final LearningExperiencePersistence learningExperiencePersistence;

    @Override
    public WrapperResult<LearningExperience<?>> handle(GetLearningExperienceByIdQuery query) {
        log.info("Retrieving learning experience by id {} by student user of id {}", query.learningExperienceId(), query.studentUserId());
        return learningExperiencePersistence.getById(query.learningExperienceId());
    }
}

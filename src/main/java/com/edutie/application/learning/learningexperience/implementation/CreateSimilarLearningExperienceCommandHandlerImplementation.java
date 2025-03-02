package com.edutie.application.learning.learningexperience.implementation;

import com.edutie.application.learning.learningexperience.CreateSimilarLearningExperienceCommandHandler;
import com.edutie.application.learning.learningexperience.command.CreateSimilarLearningExperienceCommand;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateSimilarLearningExperienceCommandHandlerImplementation implements CreateSimilarLearningExperienceCommandHandler {
    private final LearningExperiencePersistence learningExperiencePersistence;

    @Override
    public WrapperResult<LearningExperience<?>> handle(CreateSimilarLearningExperienceCommand command) {
        log.info("Creating similar learning experience to previous experience of id {} for user of id {}", command.learningExperienceId(), command.studentUserId());
        //TODO
        return null;
    }
}

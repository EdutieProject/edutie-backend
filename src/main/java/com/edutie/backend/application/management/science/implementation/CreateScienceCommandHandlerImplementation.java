package com.edutie.backend.application.management.science.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.science.CreateScienceCommandHandler;
import com.edutie.backend.application.management.science.commands.CreateScienceCommand;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.services.common.logging.ExternalFailureLog;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateScienceCommandHandlerImplementation extends HandlerBase implements CreateScienceCommandHandler {
    private final SciencePersistence sciencePersistence;

    @Override
    //TODO: change once the auth is resolved - #54
    public WrapperResult<Science> handle(CreateScienceCommand command) {
        LOGGER.info("Creating science");
        Science science = Science.create(command.adminUserId());
        science.setName(command.scienceName());
        science.setDescription(command.scienceDescription() != null ? command.scienceDescription() : "");
        Result result = sciencePersistence.save(science);
        if (result.isFailure())
            return ExternalFailureLog.persistenceFailure(result, LOGGER).map(() -> null);
        LOGGER.info("Science created successfully");
        return WrapperResult.successWrapper(science);
    }
}

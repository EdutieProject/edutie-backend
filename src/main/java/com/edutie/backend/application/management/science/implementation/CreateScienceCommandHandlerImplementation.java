package com.edutie.backend.application.management.science.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.science.CreateScienceCommandHandler;
import com.edutie.backend.application.management.science.commands.CreateScienceCommand;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;
@Component
@RequiredArgsConstructor
public class CreateScienceCommandHandlerImplementation extends HandlerBase implements CreateScienceCommandHandler {
    private final SciencePersistence sciencePersistence;
    @Override
    //TODO: change once the auth is resolved
    public WrapperResult<Science> handle(CreateScienceCommand command) {
        LOGGER.info("Creating science");
        Science science = Science.create(command.adminUserId());
        science.setName(command.scienceName());
        science.setDescription(command.scienceDescription() != null ? command.scienceDescription() : "");
        LOGGER.info("Science created successfully");
        return WrapperResult.successWrapper(science);
    }
}

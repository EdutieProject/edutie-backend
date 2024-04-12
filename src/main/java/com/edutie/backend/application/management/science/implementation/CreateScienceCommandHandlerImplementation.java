package com.edutie.backend.application.management.science.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.science.CreateScienceCommandHandler;
import com.edutie.backend.application.management.science.commands.CreateScienceCommand;
import com.edutie.backend.domain.studyprogram.science.Science;
import validation.WrapperResult;

public class CreateScienceCommandHandlerImplementation extends HandlerBase implements CreateScienceCommandHandler {
    @Override
    public WrapperResult<Science> handle(CreateScienceCommand createScienceCommand) {
        return null;
    }
}

package com.edutie.backend.application.management.science;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.management.science.commands.CreateScienceCommand;
import com.edutie.backend.domain.studyprogram.science.Science;
import validation.WrapperResult;

public interface CreateScienceCommandHandler extends UseCaseHandler<WrapperResult<Science>, CreateScienceCommand> {
}

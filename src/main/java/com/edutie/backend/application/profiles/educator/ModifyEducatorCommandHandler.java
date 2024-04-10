package com.edutie.backend.application.profiles.educator;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.profiles.educator.commands.ModifyEducatorCommand;
import validation.Result;

public interface ModifyEducatorCommandHandler extends UseCaseHandler<Result, ModifyEducatorCommand> {
}

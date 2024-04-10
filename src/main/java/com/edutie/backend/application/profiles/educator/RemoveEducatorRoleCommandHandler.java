package com.edutie.backend.application.profiles.educator;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.profiles.educator.commands.RemoveEducatorRoleCommand;
import validation.Result;

public interface RemoveEducatorRoleCommandHandler extends UseCaseHandler<Result, RemoveEducatorRoleCommand> {
}

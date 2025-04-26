package com.edutie.application.profiles.educator;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.profiles.educator.commands.RemoveEducatorRoleCommand;
import validation.Result;

public interface RemoveEducatorRoleCommandHandler extends UseCaseHandler<Result, RemoveEducatorRoleCommand> { }

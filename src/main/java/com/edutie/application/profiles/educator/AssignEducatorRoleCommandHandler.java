package com.edutie.application.profiles.educator;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.profiles.educator.commands.AssignEducatorRoleCommand;
import com.edutie.domain.core.education.educator.Educator;
import validation.WrapperResult;

public interface AssignEducatorRoleCommandHandler extends UseCaseHandler<WrapperResult<Educator>, AssignEducatorRoleCommand> { }

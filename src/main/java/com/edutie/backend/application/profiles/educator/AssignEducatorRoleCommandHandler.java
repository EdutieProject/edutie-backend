package com.edutie.backend.application.profiles.educator;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.profiles.educator.commands.AssignEducatorRoleCommand;
import com.edutie.backend.domain.education.educator.Educator;
import validation.WrapperResult;

public interface AssignEducatorRoleCommandHandler extends UseCaseHandler<WrapperResult<Educator>, AssignEducatorRoleCommand> {
}

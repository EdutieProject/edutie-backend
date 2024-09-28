package com.edutie.backend.application.profiles.student;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.profiles.student.commands.ModifyStudentPropertiesCommand;
import validation.Result;

public interface ModifyStudentPropertiesCommandHandler extends UseCaseHandler<Result, ModifyStudentPropertiesCommand> { }

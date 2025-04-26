package com.edutie.application.profiles.student;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.profiles.student.commands.ModifyStudentPropertiesCommand;
import validation.Result;

public interface ModifyStudentPropertiesCommandHandler extends UseCaseHandler<Result, ModifyStudentPropertiesCommand> { }

package com.edutie.application.profiles.student;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.profiles.student.commands.ResetStudentProfileCommand;
import validation.Result;

public interface ResetStudentProfileCommandHandler extends UseCaseHandler<Result, ResetStudentProfileCommand> { }

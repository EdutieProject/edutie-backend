package com.edutie.backend.application.profiles.student;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.profiles.student.commands.ResetStudentProfileCommand;
import validation.Result;

public interface ResetStudentProfileCommandHandler extends UseCaseHandler<Result, ResetStudentProfileCommand> { }

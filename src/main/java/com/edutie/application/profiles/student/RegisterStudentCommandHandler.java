package com.edutie.application.profiles.student;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.profiles.student.commands.RegisterStudentCommand;
import com.edutie.domain.core.learning.student.Student;
import validation.WrapperResult;

public interface RegisterStudentCommandHandler extends UseCaseHandler<WrapperResult<Student>, RegisterStudentCommand> { }

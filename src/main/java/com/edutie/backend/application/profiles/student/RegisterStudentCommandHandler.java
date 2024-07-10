package com.edutie.backend.application.profiles.student;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.profiles.student.commands.RegisterStudentCommand;
import com.edutie.backend.domain.personalization.student.Student;
import validation.WrapperResult;

public interface RegisterStudentCommandHandler extends UseCaseHandler<WrapperResult<Student>, RegisterStudentCommand> {
}

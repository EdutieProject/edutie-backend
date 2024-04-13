package com.edutie.backend.application.management.course;

import com.edutie.backend.application.management.course.commands.CreateCourseCommand;
import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.domain.studyprogram.course.Course;
import validation.WrapperResult;

public interface CreateCourseCommandHandler
        extends UseCaseHandler<WrapperResult<Course>, CreateCourseCommand> {
}

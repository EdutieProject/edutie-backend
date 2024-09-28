package com.edutie.backend.application.management.lesson;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.management.lesson.commands.CreateLessonCommand;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import validation.WrapperResult;

public interface CreateLessonCommandHandler extends UseCaseHandler<WrapperResult<Lesson>, CreateLessonCommand> { }

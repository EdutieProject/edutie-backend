package com.edutie.backend.application.creator.lesson;

import com.edutie.backend.application.creator.lesson.commands.*;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

public interface LessonService {
    List<Lesson> getAllLessonsOfCreator(EducatorId educatorId);
    WrapperResult<Lesson> createLessonAsNext(CreateLessonAsNextCommand command);
    WrapperResult<Lesson> createLessonInBetween(CreateLessonInBetweenCommand command);
    Result changeLessonProperties(ChangeLessonPropertiesCommand command);
    Result moveLesson(MoveLessonCommand command);
    Result removeLesson(RemoveLessonCommand command);
}

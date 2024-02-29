package com.edutie.backend.application.creator.lesson;

import com.edutie.backend.application.creator.lesson.commands.*;
import com.edutie.backend.domain.studyprogram.creator.identities.CreatorId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

public interface LessonService {
    List<Lesson> getAllLessonsOfCreator(CreatorId creatorId);
    WrapperResult<Lesson> createLessonAsNext(CreateLessonAsNextCommand command);
    WrapperResult<Lesson> createLessonInBetween(CreateLessonInBetweenCommand command);
    Result changeLessonProperties(ChangeLessonPropertiesCommand command);
    Result moveLesson(MoveLessonCommand command);
    Result removeLesson(RemoveLessonCommand command);
}

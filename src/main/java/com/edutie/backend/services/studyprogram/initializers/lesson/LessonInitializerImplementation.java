package com.edutie.backend.services.studyprogram.initializers.lesson;

import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.services.common.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class LessonInitializerImplementation extends ServiceBase implements LessonInitializer{
    private final LessonPersistence lessonPersistence;
    @Override
    public WrapperResult<Lesson> initializeLesson(LessonInitializationDetails initializationDetails) {
        Lesson lesson = Lesson.create(initializationDetails.educator(), initializationDetails.course());
        lesson.setName(initializationDetails.name());
        lesson.setDescription(initializationDetails.description());
        Result lessonSaveResult = lessonPersistence.save(lesson);
        if (lessonSaveResult.isFailure()) {
            LOGGER.info("Persistence failure while saving initial lesson. Error: " + lessonSaveResult.getError().toString());
            return lessonSaveResult.map(() -> null);
        }
        return WrapperResult.successWrapper(lesson);
    }
}

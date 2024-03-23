package com.edutie.backend.application.creation.lesson;

import com.edutie.backend.application.creation.lesson.commands.*;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

/**
 * Service interface for managing lessons in the educational system.
 */
public interface LessonService {

    /**
     * Retrieves a list of all lessons created by the specified educator.
     *
     * @param educatorId The identifier of the educator.
     * @return A list of lessons created by the educator.
     */
    List<Lesson> getAllLessonsOfCreator(EducatorId educatorId);

    /**
     * Creates a new lesson to be scheduled as the next lesson based on the provided command.
     *
     * @param command The command containing information for creating the next lesson.
     * @return A {@code WrapperResult} containing the created lesson or any associated errors.
     */
    WrapperResult<Lesson> createLessonAsNext(CreateLessonAsNextCommand command);

    /**
     * Creates a new lesson to be scheduled in between existing lessons based on the provided command.
     *
     * @param command The command containing information for creating the lesson in between.
     * @return A {@code WrapperResult} containing the created lesson or any associated errors.
     */
    WrapperResult<Lesson> createLessonInBetween(CreateLessonInBetweenCommand command);

    /**
     * Changes the properties of an existing lesson based on the provided command.
     *
     * @param command The command containing information for changing lesson properties.
     * @return A {@code Result} indicating the success or failure of the operation.
     */
    Result changeLessonProperties(ChangeLessonPropertiesCommand command);

    /**
     * Moves an existing lesson to a different time or date based on the provided command.
     *
     * @param command The command containing information for moving the lesson.
     * @return A {@code Result} indicating the success or failure of the operation.
     */
    Result moveLesson(MoveLessonCommand command);

    /**
     * Removes an existing lesson based on the provided command.
     *
     * @param command The command containing information for removing the lesson.
     * @return A {@code Result} indicating the success or failure of the operation.
     */
    Result removeLesson(RemoveLessonCommand command);
}

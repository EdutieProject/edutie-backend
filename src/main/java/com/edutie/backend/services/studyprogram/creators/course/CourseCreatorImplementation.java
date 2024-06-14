package com.edutie.backend.services.studyprogram.creators.course;

import com.edutie.backend.services.common.logging.ExternalFailureLog;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.services.common.ServiceBase;
import com.edutie.backend.services.studyprogram.initializers.lesson.RootLessonInitializationDetails;
import com.edutie.backend.services.studyprogram.initializers.lesson.RootLessonInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CourseCreatorImplementation extends ServiceBase implements CourseCreator {
    private final SciencePersistence sciencePersistence;
    private final CoursePersistence coursePersistence;
    private final RootLessonInitializer rootLessonInitializer;

    @Override
    public WrapperResult<Course> initializeCourse(CourseCreationDetails initializationDetails) {
        LOGGER.info("Initializing course using provided name:\n{}\n  and description:\n{}", initializationDetails.name(), initializationDetails.description());
        Course course = Course.create(initializationDetails.educator(), initializationDetails.science());
        course.setName(initializationDetails.name());
        course.setDescription(initializationDetails.description() != null ? initializationDetails.description() : "");
        Result courseSaveResult = coursePersistence.save(course);
        if (courseSaveResult.isFailure()) {
            return ExternalFailureLog.persistenceFailure(courseSaveResult, LOGGER).map(() -> null);
        }
        WrapperResult<Lesson> rootLessonInitializationResult = rootLessonInitializer.initializeLesson(
                new RootLessonInitializationDetails().course(course).educator(initializationDetails.educator())
                        .name("The first lesson")
                        .description("Sample description. Modify it as you like!")
        );
        if (rootLessonInitializationResult.isFailure())
            return rootLessonInitializationResult.map(o -> null);
        return WrapperResult.successWrapper(course);
    }
}

package com.edutie.backend.services.studyprogram.initializers.course;

import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.services.common.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CourseInitializerImplementation extends ServiceBase implements CourseInitializer {
    private final SciencePersistence sciencePersistence;
    private final CoursePersistence coursePersistence;
    @Override
    public WrapperResult<Course> initializeCourse(CourseInitializationDetails initializationDetails) {
        LOGGER.info("Initializing course using provided name:\n{}\n  and description:\n{}\n", initializationDetails.name(), initializationDetails.description());
        Course course = Course.create(initializationDetails.educator(), initializationDetails.science());
        course.setName(initializationDetails.name());
        course.setDescription(initializationDetails.description() != null ? initializationDetails.description() : "");
        Result courseSaveResult = coursePersistence.save(course);
        if (courseSaveResult.isFailure()) {
            LOGGER.info("Persistence failure while saving course. Error: " + courseSaveResult.getError().toString());
            return courseSaveResult.map(() -> null);
        }
        return WrapperResult.successWrapper(course);
    }
}

package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.course.ModifyCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.ModifyCourseCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class ModifyCourseCommandHandlerImplementation extends HandlerBase implements ModifyCourseCommandHandler {
    private final CoursePersistence coursePersistence;
    private final EducatorPersistence educatorPersistence;
    @Override
    public Result handle(ModifyCourseCommand command) {
        WrapperResult<Course> courseWrapperResult = coursePersistence.getById(command.courseId());
        if (courseWrapperResult.isFailure())
            return courseWrapperResult;
        Course course = courseWrapperResult.getValue();
        Educator educator = educatorPersistence.getByUserId(command.educatorUserId());
        if (!course.getEducator().equals(educator))
            return Result.failure(new Error("COURSE-1", "You might not modify a course which is not yours"));
        if (command.courseName() != null) course.setName(command.courseName());
        if (command.courseDescription() != null) course.setDescription(command.courseDescription());
        if (command.accessibility() != null) course.setAccessible(command.accessibility());
        course.update(command.educatorUserId());
        coursePersistence.save(course);
        return Result.success();
    }
}

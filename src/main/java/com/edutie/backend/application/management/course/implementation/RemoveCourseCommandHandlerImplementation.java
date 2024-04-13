package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.course.RemoveCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.RemoveCourseCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class RemoveCourseCommandHandlerImplementation extends HandlerBase implements RemoveCourseCommandHandler {
    private final CoursePersistence coursePersistence;
    private final EducatorPersistence educatorPersistence;
    @Override
    public Result handle(RemoveCourseCommand command) {
        Educator educator = educatorPersistence.getByUserId(command.educatorUserId());
        WrapperResult<Course> courseWrapperResult = coursePersistence.getById(command.courseId());
        if (courseWrapperResult.isFailure())
            return courseWrapperResult;
        Course course = courseWrapperResult.getValue();
        return coursePersistence.remove(course);
    }
}

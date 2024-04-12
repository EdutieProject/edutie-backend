package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.UseCaseHandlerBase;
import com.edutie.backend.application.management.course.CreateCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.CreateCourseCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateCourseCommandHandlerImplementation extends UseCaseHandlerBase implements CreateCourseCommandHandler {
    private final EducatorPersistence educatorPersistence;
    private final SciencePersistence sciencePersistence;
    private final CoursePersistence coursePersistence;
    @Override
    public WrapperResult<Course> handle(CreateCourseCommand createCourseCommand) {
        Educator educator = educatorPersistence.getById(createCourseCommand.educatorId()).getValue();
        Science science = sciencePersistence.getById(createCourseCommand.scienceId()).getValue();
        Course course = Course.create(educator, science);
        if (createCourseCommand.courseName() == null)
            //TODO: self-documenting errors
            return WrapperResult.failureWrapper(new Error("COURSE-2", "Course name must not be null"));
        course.setName(createCourseCommand.courseName());
        course.setDescription(createCourseCommand.courseDescription() != null ? createCourseCommand.courseDescription() : "");
        // IDK if this works, need testing. Additionally: should be moved somewhere into domain
        Lesson lesson = Lesson.create(educator, course);
        lesson.setName("First lesson");
        lesson.setDescription("This is the first lesson in this course with a placeholder description.");
        Segment segment = Segment.create(educator, lesson);
        segment.setName("First segment. Start designing it now!");
        lesson.getSegments().add(segment);
        course.getLessons().add(lesson);
        coursePersistence.deepSave(course);
        return WrapperResult.successWrapper(course);
    }
}

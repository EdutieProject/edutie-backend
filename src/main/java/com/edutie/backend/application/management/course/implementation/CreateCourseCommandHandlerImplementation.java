package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.course.CreateCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.CreateCourseCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateCourseCommandHandlerImplementation extends HandlerBase implements CreateCourseCommandHandler {
    private final EducatorPersistence educatorPersistence;
    private final SciencePersistence sciencePersistence;
    private final CoursePersistence coursePersistence;
    private final LessonPersistence lessonPersistence;
    private final SegmentPersistence segmentPersistence;
    @Override
    public WrapperResult<Course> handle(CreateCourseCommand command) {
        Educator educator = educatorPersistence.getByUserId(command.educatorUserId());
        Science science = sciencePersistence.getById(command.scienceId()).getValue();
        Course course = Course.create(educator, science);
        course.setName(command.courseName());
        course.setDescription(command.courseDescription() != null ? command.courseDescription() : "");
        Result courseSaveResult = coursePersistence.save(course);
        Lesson lesson = Lesson.create(educator, course);
        lesson.setName("First lesson");
        lesson.setDescription("This is the first lesson in this course with a placeholder description.");
        Result lessonSaveResult = lessonPersistence.save(lesson);
        Segment segment = Segment.create(educator, lesson);
        segment.setName("First segment. Start designing it now!");
        Result segmentSaveResult = segmentPersistence.save(segment);
        return WrapperResult.successWrapper(course);
    }
}

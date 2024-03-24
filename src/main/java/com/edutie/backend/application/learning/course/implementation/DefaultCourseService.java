package com.edutie.backend.application.learning.course.implementation;

import com.edutie.backend.application.learning.course.CourseService;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.infrastucture.persistence.contexts.learner.StudentPersistenceContext;
import com.edutie.backend.infrastucture.persistence.contexts.studyprogram.CoursePersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DefaultCourseService implements CourseService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final CoursePersistenceContext coursePersistenceContext;
    private final StudentPersistenceContext studentPersistenceContext;

    public DefaultCourseService(CoursePersistenceContext coursePersistenceContext, StudentPersistenceContext studentPersistenceContext) {
        this.coursePersistenceContext = coursePersistenceContext;
        this.studentPersistenceContext = studentPersistenceContext;
    }

    /**
     * Retrieves a list of courses associated with the specified science.
     *
     * @param scienceId The identifier of the science.
     * @return A list of courses related to the specified science.
     */
    @Override
    public List<Course> getCoursesOfScience(ScienceId scienceId) {
        LOGGER.info("Retrieving all courses of science of id {}", scienceId.identifierValue());
        return coursePersistenceContext.getAllOfScienceId(scienceId);
    }

    /**
     * Retrieves a list of courses in progress for the specified student.
     *
     * @param studentId The identifier of the student.
     * @return A list of courses in progress for the specified student.
     */
    @Override
    public List<Course> getCoursesInProgressOfStudent(StudentId studentId) {
        LOGGER.info("Retrieving all courses in progress for student of id {}", studentId.identifierValue());
        Student student = studentPersistenceContext.getById(studentId).getValue();
        Set<Course> coursesInProgress = student.getLearningHistory().stream().map(o -> o.getLessonSegment().getLesson().getCourse()).collect(Collectors.toSet());
        return coursesInProgress.stream().toList();
    }
}

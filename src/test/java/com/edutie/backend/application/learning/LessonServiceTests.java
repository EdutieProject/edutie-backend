package com.edutie.backend.application.learning;

import com.edutie.backend.domain.common.identities.AdminId;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.ScienceRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LessonServiceTests {

    private final UserId userId = new UserId();
    private final AdminId adminId = new AdminId();
    private CourseId courseId;
    @Autowired
    LessonService lessonService;
    @Autowired
    EducatorRepository educatorRepository;
    @Autowired
    ScienceRepository scienceRepository;
    @Autowired
    CoursePersistence coursePersistence;
    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    public void testSetup() {
        Educator educator = Educator.create(userId, adminId);
        educatorRepository.save(educator);
        Science science = Science.create(userId);
        scienceRepository.save(science);
        Course course = Course.create(educator, science);
        courseId = course.getId();
        coursePersistence.save(course);
    }

    //TODO: prepare convenience function for preparing a ready study program.
    @Test
    public void getLessonsFromCourseOfStudentTest() {
        Student student = Student.create(userId);
        studentRepository.save(student);
        var result = lessonService.getLessonsOfCourseForStudent(courseId, student.getId());
        assertFalse(result.isEmpty()); //TODO :: ??
    }
}

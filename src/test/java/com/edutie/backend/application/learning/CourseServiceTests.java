package com.edutie.backend.application.learning;

import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.CourseRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.ScienceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseServiceTests {
    private final UserId userId = new UserId();
    private final AdminId adminId = new AdminId();
    private ScienceId scienceId;

    @Autowired
    CourseService courseService;
    @Autowired
    EducatorRepository educatorRepository;
    @Autowired
    ScienceRepository scienceRepository;
    @Autowired
    CourseRepository courseRepository;

    @BeforeEach
    public void testSetup() {
        Educator educator = Educator.create(userId, adminId);
        educatorRepository.save(educator);
        Science science = Science.create(userId);
        scienceId = science.getId();
        scienceRepository.save(science);
        Course course = Course.create(educator, science);
        courseRepository.save(course);
    }

    @Test
    public void getCoursesOfScienceTest() {
        var result = courseService.getCoursesOfScience(scienceId);
        assertFalse(result.isEmpty());
    }

    @Test
    public void getCoursesInProgressOfStudentEmptyTest() {
        Student student = Student.create(userId);
        var result = courseService.getCoursesInProgressOfStudent(student.getId());
        assertTrue(result.isEmpty());
    }

    @Test
    public void getCoursesInProgressOfStudentPresentTest() {
        //TODO!
    }
}

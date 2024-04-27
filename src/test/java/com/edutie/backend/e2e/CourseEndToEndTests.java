package com.edutie.backend.e2e;

import com.edutie.backend.application.management.course.CreateCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.CreateCourseCommand;
import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import validation.WrapperResult;

import java.util.List;

@SpringBootTest
public class CourseEndToEndTests {
    @Autowired
    private CreateCourseCommandHandler createCourseCommandHandler;
    @Autowired
    private EducatorPersistence educatorPersistence;
    @Autowired
    private SciencePersistence sciencePersistence;
    @Autowired
    private CoursePersistence coursePersistence;
    private final UserId userId = new UserId();
    private final AdminId adminId = new AdminId();
    private ScienceId scienceId;
    @BeforeEach
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testSetup() {
        educatorPersistence.save(Educator.create(userId,adminId));
        sciencePersistence.save(Science.create(userId));
    }

    @Test
    @Transactional
    public void createCourseTest() {
        scienceId = sciencePersistence.getAll().getValue().stream().findFirst().get().getId();
        CreateCourseCommand command = new CreateCourseCommand(userId, "sample course", scienceId);
        WrapperResult<Course> courseWrapperResult = createCourseCommandHandler.handle(command);
        if (courseWrapperResult.isFailure())
            System.out.println(courseWrapperResult.getError());
        assert courseWrapperResult.isSuccess();

        List<Course> courses =  coursePersistence.getAllOfScienceId(scienceId).getValue();
        assert !courses.isEmpty();

        Course createdCourse = courses.getFirst();
        assert createdCourse.getName().equals("sample course");
        assert createdCourse.getScience().getId().equals(scienceId);
        assert !createdCourse.getLessons().isEmpty();
        assert !createdCourse.getLessons().stream().flatMap(o -> o.getSegments().stream()).toList().isEmpty();
    }

}

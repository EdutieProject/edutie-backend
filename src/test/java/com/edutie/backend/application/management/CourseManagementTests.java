package com.edutie.backend.application.management;

import com.edutie.backend.application.management.course.CreateCourseCommandHandler;
import com.edutie.backend.application.management.course.CreatedCoursesQueryHandler;
import com.edutie.backend.application.management.course.ModifyCourseCommandHandler;
import com.edutie.backend.application.management.course.RemoveCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.CreateCourseCommand;
import com.edutie.backend.application.management.course.commands.ModifyCourseCommand;
import com.edutie.backend.application.management.course.commands.RemoveCourseCommand;
import com.edutie.backend.application.management.course.queries.CreatedCoursesQuery;
import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

@SpringBootTest
public class CourseManagementTests {
    @Autowired
    private CreateCourseCommandHandler createCourseCommandHandler;
    @Autowired
    private ModifyCourseCommandHandler modifyCourseCommandHandler;
    @Autowired
    private CreatedCoursesQueryHandler createdCoursesQueryHandler;
    @Autowired
    private RemoveCourseCommandHandler removeCourseCommandHandler;
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
    public void testSetup() {
        educatorPersistence.save(Educator.create(userId, adminId));
        Science science = Science.create(userId);
        scienceId = science.getId();
        sciencePersistence.save(science);
    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS)
    public void createCourseTest() {
        CreateCourseCommand command = new CreateCourseCommand().courseName("sample course").educatorUserId(userId).scienceId(scienceId);
        WrapperResult<Course> courseWrapperResult = createCourseCommandHandler.handle(command);
        if (courseWrapperResult.isFailure())
            System.out.println(courseWrapperResult.getError());
        assert courseWrapperResult.isSuccess();

        List<Course> courses = coursePersistence.getAllOfScienceId(scienceId).getValue();
        assert !courses.isEmpty();

        Course createdCourse = courses.getFirst();
        assert createdCourse.getName().equals("sample course");
        assert !createdCourse.getLessons().isEmpty();
        assert !createdCourse.getLessons().stream().flatMap(o -> o.getSegments().stream()).toList().isEmpty();
    }

    @Test
    public void modifyCourseTest() {
        Educator educator = educatorPersistence.getByUserId(userId);
        Science science = sciencePersistence.getById(scienceId).getValue();
        Course course = Course.create(educator, science);
        coursePersistence.save(course);

        ModifyCourseCommand command = new ModifyCourseCommand()
                .educatorUserId(userId)
                .courseId(course.getId())
                .courseName("Altered name")
                .accessibility(true);

        Result result = modifyCourseCommandHandler.handle(command);
        assert result.isSuccess();

        Course modifiedCourse = coursePersistence.getById(course.getId()).getValue();
        assert modifiedCourse.getName().equals("Altered name");
        assert modifiedCourse.isAccessible();
    }

    @Test
    public void getCreatedCoursesTest() {
        Educator educator = educatorPersistence.getByUserId(userId);
        Science science = sciencePersistence.getById(scienceId).getValue();
        Course course1 = Course.create(educator, science);
        Course course2 = Course.create(educator, science);
        coursePersistence.save(course1);
        coursePersistence.save(course2);

        CreatedCoursesQuery query = new CreatedCoursesQuery()
                .educatorUserId(userId);
        WrapperResult<List<Course>> listWrapperResult = createdCoursesQueryHandler.handle(query);
        assert listWrapperResult.isSuccess();
        assert listWrapperResult.getValue().size() == 2;
        assert listWrapperResult.getValue().contains(course1);
        assert listWrapperResult.getValue().contains(course2);
    }

    @Test
    public void removeCourseTest() {
        Educator educator = educatorPersistence.getByUserId(userId);
        Science science = sciencePersistence.getById(scienceId).getValue();
        Course course = Course.create(educator, science);
        coursePersistence.save(course);

        RemoveCourseCommand command = new RemoveCourseCommand()
                .educatorUserId(userId)
                .courseId(course.getId());
        Result result = removeCourseCommandHandler.handle(command);
        assert result.isSuccess();

        WrapperResult<Course> courseWrapperResult = coursePersistence.getById(course.getId());
        assert courseWrapperResult.isFailure();
    }

}

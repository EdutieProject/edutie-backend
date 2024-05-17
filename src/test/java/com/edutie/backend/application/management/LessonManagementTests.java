package com.edutie.backend.application.management;

import com.edutie.backend.application.management.lesson.CreateLessonCommandHandler;
import com.edutie.backend.application.management.lesson.CreatedLessonsQueryHandler;
import com.edutie.backend.application.management.lesson.ModifyLessonCommandHandler;
import com.edutie.backend.application.management.lesson.RemoveLessonCommandHandler;
import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LessonManagementTests {
    @Autowired
    CoursePersistence coursePersistence;
    @Autowired
    SciencePersistence sciencePersistence;

    @Autowired
    CreateLessonCommandHandler createLessonCommandHandler;
    @Autowired
    CreatedLessonsQueryHandler createdLessonsQueryHandler;
    @Autowired
    ModifyLessonCommandHandler modifyLessonCommandHandler;
    @Autowired
    RemoveLessonCommandHandler removeLessonCommandHandler;
    private final UserId userId = new UserId();
    private final AdminId adminId = new AdminId();

    @BeforeEach
    public void testSetup() {

    }

    @Test
    public void createLessonTest() {

    }

    @Test
    public void getCreatedLessonsTest() {

    }

    @Test
    public void modifyLessonTest() {

    }

    @Test
    public void removeLessonTest() {

    }

}

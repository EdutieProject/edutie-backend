package com.edutie.infrastructure.persistence.implementation.studyprogram;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.infrastructure.persistence.jpa.repositories.EducatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.Result;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest

public class LessonPersistenceTests {
	private final UserId userId = new UserId();
	private final Educator educator = Educator.create(userId, Administrator.create(userId));
	private final Science science = Science.create(educator).getValue();
	private final Course course = Course.create(educator, science);
	@Autowired
	LessonPersistence lessonPersistence;
	@Autowired
	private EducatorRepository educatorRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private ScienceRepository scienceRepository;
	private Lesson lesson;

	@BeforeEach
	public void testSetup() {
		educatorRepository.save(educator);
		scienceRepository.save(science);
		courseRepository.save(course);
		lesson = Lesson.create(educator, course);
		saveAndAssert();
	}

	public void saveAndAssert() {
		Result res = lessonPersistence.save(lesson);
		if (res.isFailure()) {
			System.out.println(res.getError());
			throw new AssertionError();
		}
	}

	@Test
	public void getByCourse() {
		List<Lesson> lessons = lessonPersistence.getAllOfCourseId(course.getId()).getValue();
		assertTrue(lessons.contains(lesson));
	}

	@Test
	public void getByEducator() {
		List<Lesson> lessons = lessonPersistence.getAllOfEducatorId(educator.getId()).getValue();
		assertTrue(lessons.contains(lesson));
	}
}

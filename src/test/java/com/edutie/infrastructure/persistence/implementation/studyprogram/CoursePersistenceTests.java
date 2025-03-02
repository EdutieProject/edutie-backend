package com.edutie.infrastructure.persistence.implementation.studyprogram;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.EducatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.Result;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CoursePersistenceTests {
	private final UserId userId = new UserId();
	private final Educator educator = Educator.create(userId, Administrator.create(userId));
	@Autowired
	private CoursePersistence coursePersistence;
	@Autowired
	private EducatorRepository educatorRepository;
	@Autowired
	private ScienceRepository scienceRepository;
	private Science science;
	private Course course;

	@BeforeEach
	public void testSetup() {
		educatorRepository.save(educator);
		science = Science.create(educator).getValue();
		scienceRepository.save(science);
		course = Course.create(educator, science);
		saveAndAssert();
	}

	public void saveAndAssert() {
		Result res = coursePersistence.save(course);
		if (res.isFailure()) {
			System.out.println(res.getError());
			throw new AssertionError();
		}
	}

	@Test
	public void gndGetByScienceTest() {
		List<Course> courses = coursePersistence.getAllOfScienceId(science.getId()).getValue();
		assertTrue(courses.contains(course));
	}

	@Test
	public void getByEducatorTest() {
		List<Course> courses = coursePersistence.getAllOfEducatorId(educator.getId()).getValue();
		assertTrue(courses.contains(course));
	}

	@Test
	public void getAllAccessibleOfScienceId() {
		course.setAccessible(true);
		saveAndAssert();
		List<Course> courses = coursePersistence.getAllAccessibleOfScienceId(science.getId()).getValue();
		assertTrue(courses.contains(course));
	}

}

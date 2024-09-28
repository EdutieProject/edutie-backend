package com.edutie.backend.infrastructure.persistence.implementation.studyprogram;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.Result;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SegmentPersistenceTests {
	private final UserId userId = new UserId();
	private final Administrator administrator = Administrator.create(userId);
	private final Educator educator = Educator.create(userId, administrator);
	@Autowired
	private SegmentPersistence segmentPersistence;
	@Autowired
	private ScienceRepository scienceRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private EducatorRepository educatorRepository;
	private Lesson lesson;
	private Segment segment;

	@BeforeEach
	public void testSetup() {
		Science science = Science.create(educator).getValue();
		Course course = Course.create(educator, science);
		lesson = Lesson.create(educator, course);
		segment = Segment.create(educator, lesson);
		scienceRepository.save(science);
		educatorRepository.save(educator);
		courseRepository.save(course);
		lessonRepository.save(lesson);
		Result result = segmentPersistence.save(segment);
		if (result.isFailure())
			throw new AssertionError();
	}

	@Test
	@Transactional
	public void getAllOfLessonIdTest() {
		List<Segment> segments = segmentPersistence.getAllOfLessonId(lesson.getId()).getValue();
		assertTrue(segments.contains(segment));
	}

	@Test
	@Transactional
	public void getAllOfEducatorIdTest() {
		List<Segment> segments = segmentPersistence.getAllOfEducatorId(educator.getId()).getValue();
		assertTrue(segments.contains(segment));
	}
}

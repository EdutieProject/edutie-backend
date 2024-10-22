package com.edutie.backend.infrastructure.persistence.jpa;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.infrastructure.persistence.jpa.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;
import lombok.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@NoArgsConstructor
public class SegmentJpaTests {
	private final UserId testUserId = new UserId();
	private final Administrator administrator = Administrator.create(testUserId);
	private Lesson lesson;
	private Segment segment;
	private Educator educator;


	@Autowired
	private EducatorRepository educatorRepository;
	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private ScienceRepository scienceRepository;
	@Autowired
	private SegmentRepository segmentRepository;
	@Autowired
	private AdministratorRepository administratorRepository;

	@BeforeEach
	public void testSetup() {
		administratorRepository.save(administrator);
		educator = Educator.create(testUserId, administrator);
		educatorRepository.save(educator);

		Science science = Science.create(educator).getValue();
		scienceRepository.save(science);

		Course course = Course.create(educator, science);
		courseRepository.save(course);

		lesson = Lesson.create(educator, course);
		lessonRepository.save(lesson);

		segment = Segment.create(educator, lesson);
		segmentRepository.save(segment);
	}

	@Test
	@Transactional
	public void testCreate() {
		segment = Segment.create(educator, lesson);
		assertNotNull(segment.getId());
		segmentRepository.save(segment);

		assertEquals(segmentRepository.findById(segment.getId()).orElseThrow(), segment);
	}

	@Test
	public void testLessonSegmentNameAndDescription() {
		segment.setName("TestName");

		segmentRepository.save(segment);

		var fetched = segmentRepository.findById(segment.getId()).orElseThrow();
		assertEquals(fetched.getName(), "TestName");
		assertEquals("TestName", segment.getName());
	}

	@Test
	@Transactional
	public void testAddNextElement() {
		Segment segment1 = Segment.create(educator, lesson);
		Segment segment2 = Segment.create(educator, lesson);
		segmentRepository.save(segment1);
		segmentRepository.save(segment2);

		segment.addNextElement(segment1);
		segmentRepository.save(segment);

		var fetch1 = segmentRepository.findById(segment.getId()).orElseThrow();
		assertEquals(fetch1.getNextElements().stream().findFirst().orElseThrow(), segment1);

		segment.addNextElement(segment2);
		segmentRepository.save(segment);

		var fetch2 = segmentRepository.findById(segment.getId()).orElseThrow();
		assertEquals(fetch2.getNextElements().stream().skip(1).findFirst().orElseThrow(), segment2);
	}

	@Test
	@Transactional
	public void testSetPreviousElement() {
		Segment segment1 = Segment.create(educator, lesson);
		Segment segment2 = Segment.create(educator, lesson);
		segmentRepository.save(segment1);
		segmentRepository.save(segment2);

		segment.addNextElement(segment1);
		segmentRepository.save(segment);

		var fetch1 = segmentRepository.findById(segment.getId()).orElseThrow();
		assertEquals(fetch1.getNextElements().stream().findFirst().orElseThrow(), segment1);

		segment.addNextElement(segment2);
		segmentRepository.save(segment);

		var fetch2 = segmentRepository.findById(segment.getId()).orElseThrow();
		assertEquals(fetch2.getNextElements().stream().findFirst().orElseThrow(), segment2);

	}

}

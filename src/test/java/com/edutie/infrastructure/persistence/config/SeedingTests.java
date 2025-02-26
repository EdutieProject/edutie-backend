package com.edutie.infrastructure.persistence.config;

import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.infrastructure.persistence.config.initialization.Seeding;
import jakarta.persistence.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SeedingTests {
	@Autowired
	private Seeding seeding;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void shouldSeedStudyProgram() {
		seeding.seeding();

		Science science = entityManager.createQuery("SELECT s FROM Science s", Science.class).getSingleResult();
		assertNotNull(science);

		Course course = entityManager.createQuery("SELECT c FROM Course c", Course.class).getSingleResult();
		assertNotNull(course);

		Lesson lesson = entityManager.createQuery("SELECT l FROM Lesson l", Lesson.class).getSingleResult();
		assertNotNull(lesson);

		Segment segment = entityManager.createQuery("SELECT s FROM Segment s", Segment.class).getSingleResult();
		assertNotNull(segment);
	}
}

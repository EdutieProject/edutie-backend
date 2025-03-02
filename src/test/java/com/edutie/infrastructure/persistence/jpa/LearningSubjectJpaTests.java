package com.edutie.infrastructure.persistence.jpa;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.AdministratorRepository;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.EducatorRepository;
import com.edutie.infrastructure.persistence.implementation.education.repositories.LearningSubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LearningSubjectJpaTests {
	private final UserId testUserId = new UserId();
	private final Administrator administrator = Administrator.create(testUserId);
	private Educator creator;
	private LearningSubject learningSubject;


	@Autowired
	private EducatorRepository educatorRepository;
	@Autowired
	private LearningSubjectRepository learningSubjectRepository;
	@Autowired
	private ScienceRepository scienceRepository;
	@Autowired
	private AdministratorRepository administratorRepository;

	@BeforeEach
	public void testSetup() {
		creator = Educator.create(testUserId, administrator);
		educatorRepository.save(creator);
		Science science = Science.create(creator).getValue();
		scienceRepository.save(science);

		learningSubject = LearningSubject.createBlank(creator);
	}

	@Test
	public void testLearningRequirementCreation() {

		assertNotNull(learningSubject);
		assertEquals(creator, learningSubject.getAuthorEducator());

		learningSubjectRepository.save(learningSubject);

		var fetched = learningSubjectRepository.findById(learningSubject.getId()).orElseThrow();
		assertNull(learningSubject);
	}

	@Test
	public void testCourseNameAndDescription() {
		learningSubject.setName("TestName");

		assertEquals("TestName", learningSubject.getName());
	}

	@Test
	public void testOneToManyRelationship() {

		LearningSubject learningSubject1 = LearningSubject.createBlank(creator);
		LearningSubject learningSubject2 = LearningSubject.createBlank(creator);

		learningSubjectRepository.save(learningSubject);
		learningSubjectRepository.save(learningSubject1);
		learningSubjectRepository.save(learningSubject2);

		var fetched = learningSubjectRepository.findById(learningSubject.getId()).orElseThrow();
		var fetched1 = learningSubjectRepository.findById(learningSubject1.getId()).orElseThrow();
		var fetched2 = learningSubjectRepository.findById(learningSubject2.getId()).orElseThrow();

		assertNull(learningSubject);
		assertNull(learningSubject1);
		assertNull(learningSubject2);
	}
}

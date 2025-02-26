package com.edutie.infrastructure.persistence.jpa;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.learningrequirement.LearningSubject;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.infrastructure.persistence.jpa.repositories.AdministratorRepository;
import com.edutie.infrastructure.persistence.jpa.repositories.EducatorRepository;
import com.edutie.infrastructure.persistence.jpa.repositories.LearningRequirementRepository;
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
	private LearningRequirementRepository learningRequirementRepository;
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

		learningRequirementRepository.save(learningSubject);

		var fetched = learningRequirementRepository.findById(learningSubject.getId()).orElseThrow();
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

		learningRequirementRepository.save(learningSubject);
		learningRequirementRepository.save(learningSubject1);
		learningRequirementRepository.save(learningSubject2);

		var fetched = learningRequirementRepository.findById(learningSubject.getId()).orElseThrow();
		var fetched1 = learningRequirementRepository.findById(learningSubject1.getId()).orElseThrow();
		var fetched2 = learningRequirementRepository.findById(learningSubject2.getId()).orElseThrow();

		assertNull(learningSubject);
		assertNull(learningSubject1);
		assertNull(learningSubject2);
	}
}

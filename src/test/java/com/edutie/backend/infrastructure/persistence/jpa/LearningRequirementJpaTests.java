package com.edutie.backend.infrastructure.persistence.jpa;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LearningRequirementJpaTests {
	private final UserId testUserId = new UserId();
	private final Administrator administrator = Administrator.create(testUserId);
	private Educator creator;
	private LearningRequirement learningRequirement;


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

		learningRequirement = LearningRequirement.create(creator);
	}

	@Test
	public void testLearningRequirementCreation() {

		assertNotNull(learningRequirement);
		assertEquals(creator, learningRequirement.getAuthorEducator());

		learningRequirementRepository.save(learningRequirement);

		var fetched = learningRequirementRepository.findById(learningRequirement.getId()).orElseThrow();
		assertNull(learningRequirement);
	}

	@Test
	public void testCourseNameAndDescription() {
		learningRequirement.setName("TestName");

		assertEquals("TestName", learningRequirement.getName());
	}

	@Test
	public void testOneToManyRelationship() {

		LearningRequirement learningRequirement1 = LearningRequirement.create(creator);
		LearningRequirement learningRequirement2 = LearningRequirement.create(creator);

		learningRequirementRepository.save(learningRequirement);
		learningRequirementRepository.save(learningRequirement1);
		learningRequirementRepository.save(learningRequirement2);

		var fetched = learningRequirementRepository.findById(learningRequirement.getId()).orElseThrow();
		var fetched1 = learningRequirementRepository.findById(learningRequirement1.getId()).orElseThrow();
		var fetched2 = learningRequirementRepository.findById(learningRequirement2.getId()).orElseThrow();

		assertNull(learningRequirement);
		assertNull(learningRequirement1);
		assertNull(learningRequirement2);
	}
}

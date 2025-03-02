package com.edutie.infrastructure.persistence.jpa;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.AdministratorRepository;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.EducatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExerciseTypeJpaTests {
	private final UserId testUserId = new UserId();
	private final Administrator administrator = Administrator.create(testUserId);
	private final UserId testUserId_2 = new UserId();
	private ExerciseType exerciseType;
	private Educator educator;
	@Autowired
	private ExerciseTypeRepository exerciseTypeRepository;
	@Autowired
	private EducatorRepository educatorRepository;
	@Autowired
	private AdministratorRepository administratorRepository;

	@BeforeEach
	public void testSetup() {
		administratorRepository.save(administrator);
		educator = Educator.create(testUserId, administrator);
		educatorRepository.save(educator);
		exerciseType = ExerciseType.create(educator);
		exerciseTypeRepository.save(exerciseType);
	}

	@Test
	public void testCreate() {
		exerciseType = ExerciseType.create(educator);
		exerciseTypeRepository.save(exerciseType);

		assertEquals(exerciseTypeRepository.findById(exerciseType.getId()).orElseThrow(), exerciseType);
		assertEquals((exerciseTypeRepository.findById(exerciseType.getId()).orElseThrow()).getCreatedOn(), exerciseType.getCreatedOn());
	}

	@Test
	public void testUserUpdate() {
		var fetched = exerciseTypeRepository.findById(exerciseType.getId()).orElseThrow();
		assertEquals(fetched.getCreatedOn(), exerciseType.getCreatedOn()); //check if exerciseType time match DB
		assertEquals(fetched.getCreatedBy(), testUserId);//check if exerciseType was created by user1 (testUserId)

		exerciseType.update(testUserId_2); // update User1 to User2
		exerciseTypeRepository.save(exerciseType);// saving new exerciseType to DB

		fetched = exerciseTypeRepository.findById(exerciseType.getId()).orElseThrow();
		assertEquals(fetched.getUpdatedBy(), testUserId_2); // check if UserId has been updated
		assertEquals(fetched.getUpdatedOn(), exerciseType.getUpdatedOn());//check if exerciseType time has been updated in DB
	}


}

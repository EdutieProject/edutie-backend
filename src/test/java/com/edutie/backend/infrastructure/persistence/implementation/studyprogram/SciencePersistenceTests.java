package com.edutie.backend.infrastructure.persistence.implementation.studyprogram;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.Result;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SciencePersistenceTests {
	private final UserId userId = new UserId();
	private final Administrator administrator = Administrator.create(userId);
	private final Educator educator = Educator.create(userId, administrator);
	private final Science science = Science.create(educator).getValue();
	@Autowired
	private SciencePersistence sciencePersistence;

	@BeforeEach
	public void testSetup() {
		Result res = sciencePersistence.save(science);
		if (res.isFailure())
			throw new AssertionError();
	}

	@Test
	public void getAll() {
		List<Science> sciences = sciencePersistence.getAll().getValue();
		assertTrue(sciences.contains(science));
	}
}

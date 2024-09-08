package com.edutie.backend.infrastructure.persistence.jpa;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.AdministratorRepository;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.EducatorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import lombok.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor
public class EducatorJpaTests {
	private final UserId testUserId = new UserId();
	private final Administrator administrator = Administrator.create(testUserId);

	@Autowired
	private EducatorRepository educatorRepository;
	@Autowired
	private AdministratorRepository administratorRepository;

	@Test
	public void testCreate() {
		administratorRepository.save(administrator);
		Educator creator = Educator.create(testUserId, administrator);
		educatorRepository.save(creator);
		assertEquals(educatorRepository.findById(creator.getId()).orElseThrow(), creator);
	}

	@Test
	public void findByOwnerUserIdTest() {
		Educator creator = Educator.create(testUserId, administrator);
		educatorRepository.save(creator);

		assert educatorRepository.findByOwnerUserId(testUserId).isPresent();
	}
}

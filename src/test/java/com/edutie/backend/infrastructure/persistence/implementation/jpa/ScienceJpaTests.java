package com.edutie.backend.infrastructure.persistence.implementation.jpa;

import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.AdministratorRepository;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.ScienceRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor

public class ScienceJpaTests {
    private final UserId testUserId = new UserId();
    private final Administrator administrator = Administrator.create(testUserId);
    private Science science;
    private Educator educator;

    @Autowired
    private EducatorRepository educatorRepository;
    @Autowired
    private ScienceRepository scienceRepository;
    @Autowired
    private AdministratorRepository administratorRepository;

    @BeforeEach
    public void testSetup() {
        administratorRepository.save(administrator);
        educator = Educator.create(testUserId, administrator);
        educatorRepository.save(educator);
        science = Science.create(educator).getValue();
    }

    @Test
    public void testCreate() {
        science = Science.create(educator).getValue();
        scienceRepository.save(science);

        assertEquals(scienceRepository.findById(science.getId()).orElseThrow(), science);
    }

    @Test
    public void testScienceNameAndDescription() {
        science.setName("Math");
        science.setDescription("Math Science");

        assertEquals("Math", science.getName());
        assertEquals("Math Science", science.getDescription());
    }
}

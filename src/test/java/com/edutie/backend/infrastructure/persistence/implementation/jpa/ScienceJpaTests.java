package com.edutie.backend.infrastructure.persistence.implementation.jpa;

import com.edutie.backend.domain.administration.administrator.identities.AdministratorId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.ScienceRepository;
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
    private final AdministratorId administratorId = new AdministratorId();
    private Science science;

    @Autowired
    private EducatorRepository educatorRepository;
    @Autowired
    private ScienceRepository scienceRepository;

    @BeforeEach
    public void testSetup() {
        Educator educator = Educator.create(testUserId, administratorId);
        educatorRepository.save(educator);
        science = Science.create(testUserId);
    }

    @Test
    public void testCreate() {
        science = Science.create(testUserId);
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

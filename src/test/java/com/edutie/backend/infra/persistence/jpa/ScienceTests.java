package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.administration.AdminId;
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

public class ScienceTests {
    private final UserId testUserId = new UserId();
    private final AdminId adminId = new AdminId();
    private Science science;

    @Autowired
    private EducatorRepository educatorRepository;
    @Autowired
    private ScienceRepository scienceRepository;

    @BeforeEach
    public void testSetup() {
        Educator educator = Educator.create(testUserId, adminId);
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

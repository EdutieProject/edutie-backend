package com.edutie.backend.infrastructure.persistence.implementation.jpa;

import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor
public class EducatorJpaTests {
    private final UserId testUserId = new UserId();
    private final AdminId adminId = new AdminId();

    @Autowired
    private EducatorRepository educatorRepository;

    @Test
    public void testCreate() {
        Educator creator = Educator.create(testUserId, adminId);
        educatorRepository.save(creator);
        assertEquals(educatorRepository.findById(creator.getId()).orElseThrow(), creator);
    }

    @Test
    public void findByOwnerUserIdTest() {
        Educator creator = Educator.create(testUserId, adminId);
        educatorRepository.save(creator);

        assert educatorRepository.findByOwnerUserId(testUserId).isPresent();
    }
}

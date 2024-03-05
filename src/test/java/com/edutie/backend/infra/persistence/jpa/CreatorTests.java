package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.creator.Creator;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.CreatorRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor
public class CreatorTests {
    private final UserId testUserId = new UserId();

    @Autowired
    private CreatorRepository creatorRepository;
    @Test
    public void testCreate() {
        Creator creator = Creator.create(testUserId);
        creatorRepository.save(creator);
        assertEquals(creatorRepository.findById(creator.getId()).orElseThrow(), creator);
    }
}

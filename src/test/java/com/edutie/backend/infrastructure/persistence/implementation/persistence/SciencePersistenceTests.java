package com.edutie.backend.infrastructure.persistence.implementation.persistence;

import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.Result;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SciencePersistenceTests {
    @Autowired
    private SciencePersistence sciencePersistence;
    private final UserId userId = new UserId();
    private final AdminId adminId = new AdminId();
    private Science science;

    @BeforeEach
    public void testSetup() {
        science = Science.create(userId);
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

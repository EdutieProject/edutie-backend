package com.edutie.edutiebackend.domain;

import java.util.UUID;

import com.edutie.edutiebackend.domain.core.student.enums.SchoolType;
import com.edutie.edutiebackend.domain.core.student.valueobjects.SchoolStage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.edutie.edutiebackend.domain.core.learningresource.LearningResource;
import com.edutie.edutiebackend.domain.core.learningresource.identities.LearningResourceId;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class CommonClassesTests {

    @Test
    public void ValueObjectEqualsTest()
    {
        var stage1 = new SchoolStage(SchoolType.HighSchool, 2);
        var stage2 = new SchoolStage(SchoolType.HighSchool, 2);
        assertEquals(stage1, stage2);
    }

    @Test
    public void ValueObjectNotEqualsTest()
    {
        var stage1 = new SchoolStage(SchoolType.HighSchool, 2);
        var stage2 = new SchoolStage(SchoolType.TechnicalHighSchool, 5);
        assertNotEquals(stage1, stage2);
    }

    @Test
    public void EntityEqualsTest()
    {
        var identity = new LearningResourceId(UUID.randomUUID());
        LearningResource lr1 = new LearningResource();
        lr1.setId(identity);
        LearningResource lr2 = new LearningResource();
        lr2.setId(identity);
        assertEquals(lr1, lr2);
    }
    @Test
    public void EntityNotEqualsTest()
    {
        LearningResource lr1 = new LearningResource();
        lr1.setId(new LearningResourceId(UUID.randomUUID()));
        LearningResource lr2 = new LearningResource();
        lr2.setId(new LearningResourceId(UUID.randomUUID()));

        assertNotEquals(lr1, lr2);
    }
}

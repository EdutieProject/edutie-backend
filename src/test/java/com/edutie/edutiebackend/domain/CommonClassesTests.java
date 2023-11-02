package com.edutie.edutiebackend.domain;

import com.edutie.edutiebackend.domain.common.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.learningresource.LearningResource;
import com.edutie.edutiebackend.domain.learningresource.valueobjects.ResourceOverview;
import com.edutie.edutiebackend.domain.student.enums.SchoolType;
import com.edutie.edutiebackend.domain.student.valueobjects.SchoolStage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class CommonClassesTests {

    @Test
    public void ValueObjectEqualsTest1()
    {
        ResourceOverview overview1 = new ResourceOverview("Hello");
        ResourceOverview overview2 = new ResourceOverview("Hello");
        Assertions.assertEquals(overview1, overview2);
    }

    @Test
    public void ValueObjectEqualsTest2()
    {
        SchoolStage schoolStage1 = new SchoolStage(SchoolType.HighSchool, 2);
        SchoolStage schoolStage2 = new SchoolStage(SchoolType.HighSchool, 2);
        Assertions.assertEquals(schoolStage1, schoolStage2);
    }

    @Test
    public void ValueObjectNotEqualsTest()
    {
        ResourceOverview overview1 = new ResourceOverview("Hello");
        ResourceOverview overview2 = new ResourceOverview("World!");
        Assertions.assertNotEquals(overview1, overview2);
    }

    @Test
    public void EntityEqualsTest()
    {
        var identity = new LearningResourceId(UUID.randomUUID());
        LearningResource lr1 = new LearningResource();
        lr1.setId(identity);
        LearningResource lr2 = new LearningResource();
        lr2.setId(identity);
        Assertions.assertEquals(lr1, lr2);
    }
    @Test
    public void EntityNotEqualsTest()
    {
        LearningResource lr1 = new LearningResource();
        lr1.setId(new LearningResourceId(UUID.randomUUID()));
        LearningResource lr2 = new LearningResource();
        lr2.setId(new LearningResourceId(UUID.randomUUID()));

        Assertions.assertNotEquals(lr1, lr2);
    }
}

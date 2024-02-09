package com.edutie.backend.domain.learner;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.learner.student.enums.SchoolType;
import com.edutie.backend.domain.learner.student.valueobjects.SchoolStage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@SpringBootTest
public class CommonClassesTests {

    @Test
    public void ValueObjectEqualsTest()
    {
        var stage1 = new SchoolStage(SchoolType.HIGH_SCHOOL, 2);
        var stage2 = new SchoolStage(SchoolType.HIGH_SCHOOL, 2);
        assertEquals(stage1, stage2);
    }

    @Test
    public void ValueObjectNotEqualsTest()
    {
        var stage1 = new SchoolStage(SchoolType.HIGH_SCHOOL, 2);
        var stage2 = new SchoolStage(SchoolType.TECHNICAL_HIGH_SCHOOL, 5);
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

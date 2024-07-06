package com.edutie.backend.domain.education;

import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.identities.AdministratorId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.enums.EducatorType;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.studyprogram.science.Science;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LearningRequirementTests {
    private final UserId userId = new UserId();
    private final Administrator administrator = Administrator.create(new UserId());
    private Science science;
    private final Educator educator = Educator.create(userId, administrator);

    @BeforeEach
    public void testSetup() {
        educator.setType(EducatorType.ADMINISTRATOR);
        science = Science.create(educator).getValue();
    }

    @Test
    public void subRequirementsAppendTest() {
        LearningRequirement learningRequirement = LearningRequirement.create(educator, science);
        learningRequirement.appendSubRequirement("R1", "");
        learningRequirement.appendSubRequirement("R2", "");
        learningRequirement.appendSubRequirement("R3", "");

        assert learningRequirement.getSubRequirements().get(0).getName().equals("R1");
        assert learningRequirement.getSubRequirements().get(2).getName().equals("R3");
    }

    @Test
    public void subRequirementsInsertTest() {
        LearningRequirement learningRequirement = LearningRequirement.create(educator, science);
        learningRequirement.appendSubRequirement("R1", "");
        learningRequirement.appendSubRequirement("R2", "");
        learningRequirement.appendSubRequirement("R3", "");
        assert learningRequirement.insertSubRequirement("Hello!", "", 1).isSuccess();

        assert learningRequirement.getSubRequirements().get(1).getName().equals("Hello!");
    }

    @Test
    public void subRequirementDeleteTest() {
        LearningRequirement learningRequirement = LearningRequirement.create(educator, science);
        learningRequirement.appendSubRequirement("R1", "");
        learningRequirement.appendSubRequirement("R2", "");
        learningRequirement.appendSubRequirement("R3", "");
        assert learningRequirement.getSubRequirements().size() == 3;

        assert learningRequirement.removeSubRequirement(0).isSuccess();
        assert learningRequirement.getSubRequirements().get(0).getName().equals("R2");
    }

}

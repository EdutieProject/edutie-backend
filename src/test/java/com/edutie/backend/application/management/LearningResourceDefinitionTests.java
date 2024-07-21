package com.edutie.backend.application.management;

import com.edutie.backend.application.management.learningresourcedefinition.CreateLearningResourceDefinitionCommandHandler;
import com.edutie.backend.application.management.learningresourcedefinition.commands.CreateLearningResourceDefinitionCommand;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

@SpringBootTest
public class LearningResourceDefinitionTests {
    @Autowired
    private EducatorPersistence educatorPersistence;
    @Autowired
    private AdministratorPersistence administratorPersistence;
    @Autowired
    private CreateLearningResourceDefinitionCommandHandler createLearningResourceDefinitionCommandHandler;
    private final UserId userId = new UserId();
    private final Administrator administrator = Administrator.create(userId);
    private final Educator educator = Educator.create(userId, administrator);

    @BeforeEach
    public void testSetup() {
        administratorPersistence.save(administrator).throwIfFailure();
        educatorPersistence.save(educator).throwIfFailure();
    }

    @Test
    public void createLearningResourceDefinitionTest() {
        CreateLearningResourceDefinitionCommand command = new CreateLearningResourceDefinitionCommand()
                .theoryDescription("LRD theory descriptor")
                .exerciseDescription("LRD exercise descriptor")
                .educatorUserId(userId);

        WrapperResult<LearningResourceDefinition> wrapperResult = createLearningResourceDefinitionCommandHandler.handle(command);

        assert wrapperResult.isSuccess();
        LearningResourceDefinition learningResourceDefinition = wrapperResult.getValue();
        assert learningResourceDefinition.getLearningRequirements().isEmpty();
        assert learningResourceDefinition.getTheoryDescription().text().equals("LRD theory descriptor");
    }
}

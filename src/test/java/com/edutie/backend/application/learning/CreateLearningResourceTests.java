package com.edutie.backend.application.learning;

import com.edutie.backend.application.learning.learningresource.CreateLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.Set;

@SpringBootTest
public class CreateLearningResourceTests {
    private final UserId userId = new UserId();
    private final Administrator administrator = Administrator.create(userId);
    private final Educator educator = Educator.create(userId, administrator);
    @Autowired
    LearningRequirementPersistence learningRequirementPersistence;
    @Autowired
    AdministratorPersistence administratorPersistence;
    @Autowired
    EducatorPersistence educatorPersistence;
    @Autowired
    StudentPersistence studentPersistence;
    @Autowired
    LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    @Autowired
    CreateLearningResourceCommandHandler createLearningResourceCommandHandler;

    @BeforeEach
    public void testSetup() {
        administratorPersistence.save(administrator);
        educatorPersistence.save(educator);
    }


    @Test
    public void createLearningResourceForEmptyLearningHistory() {
        Student student = Student.create(userId);
        studentPersistence.save(student).throwIfFailure();

        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setName("Integration by parts");
        learningRequirement.setDescription(PromptFragment.of("Here would go the description of integration by parts"));
        learningRequirement.appendSubRequirement("Calculating derivatives and antiderivatives of ingredient functions");
        learningRequirement.appendSubRequirement("Proper formula usage");
        learningRequirement.appendSubRequirement("3rd sub req nfgoiufguoeoeaofsoefe");
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();

        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                PromptFragment.of("Theory description"),
                PromptFragment.of("Exercise description"),
                Set.of(learningRequirement)
        );
        learningResourceDefinition.setTheorySummaryAdditionalDescription(PromptFragment.of("Theory summary additional desc"));
        learningResourceDefinition.setHintsAdditionalDescription(PromptFragment.of("Hints additional desc"));
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();

        CreateLearningResourceCommand command = new CreateLearningResourceCommand()
                .learningResourceDefinitionId(learningResourceDefinition.getId())
                .studentUserId(userId);
        WrapperResult<LearningResource> learningResourceWrapperResult = createLearningResourceCommandHandler.handle(command).throwIfFailure();
    }
}

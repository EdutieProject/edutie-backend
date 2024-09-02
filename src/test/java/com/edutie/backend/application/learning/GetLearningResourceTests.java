package com.edutie.backend.application.learning;

import com.edutie.backend.application.learning.learningresource.GetLearningResourceByIdQueryHandler;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class GetLearningResourceTests {
    @Autowired
    private GetLearningResourceByIdQueryHandler getLearningResourceByIdQueryHandler;
    @Autowired
    private LearningResourcePersistence learningResourcePersistence;
    @Autowired
    private LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    @Autowired
    private StudentPersistence studentPersistence;
    private final UserId userId = new UserId();
    private final Student student = Student.create(userId);

    @BeforeEach
    public void testSetup() {
        studentPersistence.save(student).throwIfFailure();
    }

    @Test
    public void getLearningResourceByIdTest() {
        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                PromptFragment.of(""),
                PromptFragment.of(""),
                Set.of()
        );
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();

        LearningResource learningResource = LearningResource.create(
                LearningResourceGenerationSchema.create(learningResourceDefinition, student),
                Activity.create("", Set.of()),
                Theory.create("",""),
                Set.of()
        );
        learningResourcePersistence.save(learningResource).throwIfFailure();

        assert learningResourcePersistence.getById(learningResource.getId()).isSuccess();
        assert learningResourcePersistence.getById(learningResource.getId()).getValue().equals(learningResource);
    }
}

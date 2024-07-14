package com.edutie.backend.domainservice.personalization.learningresource;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.mocks.LearningMocks;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class LearningResourceGenerationServiceTests {
    LearningResourceGenerationService learningResourceGenerationService = new LearningResourceGenerationServiceImplementation(
            LearningMocks.knowledgeMapServiceMock(), LearningMocks.largeLanguageModelServiceMock()
    );
    private final UserId userId = new UserId();
    @Test
    public void learningResourceGenerationServiceTest() {
        Student student = Student.create(userId);
        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                PromptFragment.of("Hello!"),
                PromptFragment.of("World!"),
                Set.of()
        );
        learningResourceGenerationService.generateLearningResource(learningResourceDefinition, student).throwIfFailure();
    }
}

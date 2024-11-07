package com.edutie.backend.application.learning;

import com.edutie.backend.application.learning.learningresource.GetLearningResourceByIdQueryHandler;
import com.edutie.backend.application.learning.learningresource.GetLearningResourcesByDefinitionIdQueryHandler;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourceByIdQuery;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourcesByDefinitionIdQuery;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.backend.mocks.MockUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class LearningResourceQueryHandlersTests {
    @Autowired
    MockUser mockUser;
    // Persistence
    @Autowired
    private LearningResourcePersistence learningResourcePersistence;
    @Autowired
    private LearningResultPersistence learningResultPersistence;
    @Autowired
    private LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    // Handlers
    @Autowired
    private GetLearningResourceByIdQueryHandler getLearningResourceByIdQueryHandler;
    @Autowired
    private GetLearningResourcesByDefinitionIdQueryHandler getLearningResourcesByDefinitionIdQueryHandler;

    @BeforeEach
    public void testSetup() {
        mockUser.saveToPersistence();
    }

    private LearningResourceDefinition createAndSaveLearningResourceDefinition() {
        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                TheoryDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                ActivityDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                Set.of()
        );
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();
        return learningResourceDefinition;
    }

    private LearningResource createAndSaveLearningResource(LearningResourceDefinition learningResourceDefinition) {
        LearningResource learningResource = LearningResource.create(
                LearningResourceGenerationSchema.create(mockUser.getStudentProfile(), learningResultPersistence, learningResourceDefinition, Set.of()),
                Activity.create("", Set.of()),
                Theory.create("", "")
        );
        learningResourcePersistence.save(learningResource).throwIfFailure();
        return learningResource;
    }

    @Test
    public void getLearningResourceByIdTest() {
        LearningResourceDefinition learningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningResource learningResource = createAndSaveLearningResource(learningResourceDefinition);

        GetLearningResourceByIdQuery query = new GetLearningResourceByIdQuery()
                .studentUserId(mockUser.getUserId())
                .learningResourceId(learningResource.getId());

        WrapperResult<LearningResource> queryResult = getLearningResourceByIdQueryHandler.handle(query);

        assertTrue(queryResult.isSuccess());
        assertEquals(queryResult.getValue(), learningResource);
    }

    @Test
    public void getLearningResourcesByDefinitionIdTest() {
        LearningResourceDefinition learningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningResource learningResource = createAndSaveLearningResource(learningResourceDefinition);

        GetLearningResourcesByDefinitionIdQuery query = new GetLearningResourcesByDefinitionIdQuery()
                .learningResourceDefinitionId(learningResourceDefinition.getId())
                .studentUserId(mockUser.getUserId());
        WrapperResult<List<LearningResource>> learningResourcesWrapper = getLearningResourcesByDefinitionIdQueryHandler.handle(query);

        Assertions.assertTrue(learningResourcesWrapper.isSuccess());
        Assertions.assertTrue(learningResourcesWrapper.getValue().contains(learningResource));
    }
}

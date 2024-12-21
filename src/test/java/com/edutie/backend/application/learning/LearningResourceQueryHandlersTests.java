package com.edutie.backend.application.learning;

import com.edutie.backend.application.learning.learningresource.GetLearningResourceByIdQueryHandler;
import com.edutie.backend.application.learning.learningresource.GetLearningResourcesByDefinitionIdQueryHandler;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourceByIdQuery;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourcesByDefinitionIdQuery;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresource.entities.TheoryCard;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresource.valueobjects.Visualisation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.mocks.MockUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private StaticLearningResourceDefinition createAndSaveLearningResourceDefinition() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = StaticLearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                TheoryDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                ActivityDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                Set.of()
        );
        learningResourceDefinitionPersistence.save(staticLearningResourceDefinition).throwIfFailure();
        return staticLearningResourceDefinition;
    }

    private LearningResource createAndSaveLearningResource(StaticLearningResourceDefinition staticLearningResourceDefinition) {
        LearningResource learningResource = LearningResource.create(
                mockUser.getStudentProfile(),
                staticLearningResourceDefinition,
                staticLearningResourceDefinition.getLearningRequirements().stream()
                        .flatMap(o -> o.getElementalRequirements().stream()).filter(o -> o.getOrdinal() < 1).collect(Collectors.toSet()),
                Activity.create("Activity text", Set.of(Hint.create("aaa"))),
                Set.of(TheoryCard.create(new LearningRequirementId(), "dsadas")),
                new Visualisation("")
        );
        learningResourcePersistence.save(learningResource).throwIfFailure();
        return learningResource;
    }

    @Test
    public void getLearningResourceByIdTest() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningResource learningResource = createAndSaveLearningResource(staticLearningResourceDefinition);

        GetLearningResourceByIdQuery query = new GetLearningResourceByIdQuery()
                .studentUserId(mockUser.getUserId())
                .learningResourceId(learningResource.getId());

        WrapperResult<LearningResource> queryResult = getLearningResourceByIdQueryHandler.handle(query);

        assertTrue(queryResult.isSuccess());
        assertEquals(queryResult.getValue(), learningResource);
    }

    @Test
    public void getLearningResourcesByDefinitionIdTest() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningResource learningResource = createAndSaveLearningResource(staticLearningResourceDefinition);

        GetLearningResourcesByDefinitionIdQuery query = new GetLearningResourcesByDefinitionIdQuery()
                .learningResourceDefinitionId(staticLearningResourceDefinition.getId())
                .studentUserId(mockUser.getUserId());
        WrapperResult<List<LearningResource>> learningResourcesWrapper = getLearningResourcesByDefinitionIdQueryHandler.handle(query);

        Assertions.assertTrue(learningResourcesWrapper.isSuccess());
        Assertions.assertTrue(learningResourcesWrapper.getValue().contains(learningResource));
    }
}

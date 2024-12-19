package com.edutie.backend.application.learning;

import com.edutie.backend.application.learning.learningresult.GetLearningResultByIdQueryHandler;
import com.edutie.backend.application.learning.learningresult.queries.GetLearningResultByIdQuery;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresource.entities.TheoryCard;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresource.valueobjects.Visualisation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.mocks.EducationMocks;
import com.edutie.backend.mocks.MockUser;
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
public class LearningResultQueryHandlersTests {
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
    private GetLearningResultByIdQueryHandler getLearningResultByIdQueryHandler;

    @BeforeEach
    public void testSetup() {
        mockUser.saveToPersistence();
    }

    private LearningResourceDefinition createAndSaveLearningResourceDefinition() {
        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                TheoryDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                ActivityDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                Set.of(EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile()))
        );
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();
        return learningResourceDefinition;
    }

    private LearningResource createAndSaveLearningResource(LearningResourceDefinition learningResourceDefinition) {
        LearningResource learningResource = LearningResource.create(
                mockUser.getStudentProfile(),
                learningResourceDefinition,
                learningResourceDefinition.getLearningRequirements().stream()
                        .flatMap(o -> o.getElementalRequirements().stream()).filter(o -> o.getOrdinal() < 1).collect(Collectors.toSet()),
                Activity.create("Activity text", Set.of(Hint.create("aaa"))),
                Set.of(TheoryCard.create(new LearningRequirementId(), "dsadas")),
                new Visualisation("")
        );
        learningResourcePersistence.save(learningResource).throwIfFailure();
        return learningResource;
    }

    @Test
    public void getLearningResultByIdTest() {
        LearningResourceDefinition learningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningResource learningResource = createAndSaveLearningResource(learningResourceDefinition);

        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), learningResource.getId(), learningResource.getDefinitionType(), "Report", 0),
                new Feedback("Feedback text"),
                learningResourceDefinition.getLearningRequirements().stream().map(o -> Assessment.create(o.getId(), new Grade(2), Feedback.of(""), List.of())).collect(Collectors.toSet())
        );
        learningResultPersistence.save(learningResult).throwIfFailure();

        GetLearningResultByIdQuery query = new GetLearningResultByIdQuery()
                .studentUserId(mockUser.getUserId())
                .learningResultId(learningResult.getId());

        WrapperResult<LearningResult> queryResult = getLearningResultByIdQueryHandler.handle(query);

        assertTrue(queryResult.isSuccess());
        assertEquals(queryResult.getValue(), learningResult);
    }

}

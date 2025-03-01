package com.edutie.application.learning;

import com.edutie.application.learning.learningresult.GetLearningResultByIdQueryHandler;
import com.edutie.application.learning.learningresult.queries.GetLearningResultByIdQuery;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.activity.base.ActivityBase;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.domain.core.learning.learningexperience.valueobjects.Visualisation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.Assessment;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmissionBase;
import com.edutie.mocks.EducationMocks;
import com.edutie.mocks.MockUser;
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
    private LearningExperiencePersistence learningExperiencePersistence;
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

    private StaticLearningResourceDefinition createAndSaveLearningResourceDefinition() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = StaticLearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                TheoryDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                ActivityDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                Set.of(EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile()))
        );
        learningResourceDefinitionPersistence.save(staticLearningResourceDefinition).throwIfFailure();
        return staticLearningResourceDefinition;
    }

    private LearningExperience createAndSaveLearningResource(StaticLearningResourceDefinition staticLearningResourceDefinition) {
        LearningExperience learningExperience = LearningExperience.create(
                mockUser.getStudentProfile(),
                staticLearningResourceDefinition,
                staticLearningResourceDefinition.getLearningRequirements().stream()
                        .flatMap(o -> o.getElementalRequirements().stream()).filter(o -> o.getOrdinal() < 1).collect(Collectors.toSet()),
                ActivityBase.create("Activity text", Set.of(Hint.create("aaa"))),
                Set.of(TheoryCard.create(new LearningSubjectId(), "dsadas")),
                new Visualisation("")
        );
        learningExperiencePersistence.save(learningExperience).throwIfFailure();
        return learningExperience;
    }

    @Test
    public void getLearningResultByIdTest() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningExperience learningExperience = createAndSaveLearningResource(staticLearningResourceDefinition);

        LearningResult learningResult = LearningResult.create(
                SolutionSubmissionBase.create(mockUser.getStudentProfile(), learningExperience.getId(), learningExperience.getDefinitionType(), "Report", 0),
                new Feedback("Feedback text"),
                staticLearningResourceDefinition.getLearningRequirements().stream().map(o -> Assessment.create(o.getId(), new Grade(2), Feedback.of(""), List.of())).collect(Collectors.toSet())
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

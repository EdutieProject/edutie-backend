package com.edutie.backend.application.learning;

import com.edutie.backend.application.learning.ancillaries.LatestActivityQueryHandler;
import com.edutie.backend.application.learning.ancillaries.implementation.LatestActivityQueryHandlerImplementation;
import com.edutie.backend.application.learning.ancillaries.queries.LatestActivityQuery;
import com.edutie.backend.application.learning.ancillaries.viewmodels.LatestActivityView;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.backend.mocks.EducationMocks;
import com.edutie.backend.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class LatestActivityQueryHandlerTests {
    @Autowired
    MockUser mockUser;
    // Handlers
    @Autowired
    private LatestActivityQueryHandler latestActivityQueryHandler;
    @Autowired
    private LearningRequirementPersistence learningRequirementPersistence;
    @Autowired
    private LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    @Autowired
    private LearningResultPersistence learningResultPersistence;
    @Autowired
    private LearningResourcePersistence learningResourcePersistence;
    @Autowired
    private SegmentPersistence segmentPersistence;
    @Autowired
    private LessonPersistence lessonPersistence;
    @Autowired
    private CoursePersistence coursePersistence;
    @Autowired
    private SciencePersistence sciencePersistence;

    private static Course course;
    private static LearningResult learningResult;


    @BeforeEach
    public void testSetup() {
        mockUser.saveToPersistence();
        LearningRequirement learningRequirement = EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile());
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                PromptFragment.empty(), PromptFragment.empty(),
                Set.of(learningRequirement)
        );
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();

        Science science = Science.create(mockUser.getEducatorProfile()).getValue();
        sciencePersistence.save(science).throwIfFailure();
        course = Course.create(mockUser.getEducatorProfile(), science);
        coursePersistence.save(course).throwIfFailure();
        Lesson lesson = Lesson.create(mockUser.getEducatorProfile(), course);
        lessonPersistence.save(lesson).throwIfFailure();
        Segment segment = Segment.create(mockUser.getEducatorProfile(), lesson);
        segment.setLearningResourceDefinitionId(learningResourceDefinition.getId());
        segmentPersistence.save(segment).throwIfFailure();

        LearningResource learningResource = LearningResource.create(LearningResourceGenerationSchema.create(
                mockUser.getStudentProfile(), learningResultPersistence, learningResourceDefinition, Set.of()
        ), "", Activity.create("", Set.of()), Set.of());
        learningResourcePersistence.save(learningResource).throwIfFailure();
        learningResult = LearningResult.create(SolutionSubmission.create(
                mockUser.getStudentProfile(), learningResource, "", 0
                ), Feedback.of(""), Set.of());
        learningResultPersistence.save(learningResult).throwIfFailure();
    }


    @Test
    public void getLatestActivitySuccessTest() {
        LatestActivityQuery query = new LatestActivityQuery().studentUserId(mockUser.getUserId());

        WrapperResult<LatestActivityView> queryResult = latestActivityQueryHandler.handle(query);

        assertTrue(queryResult.isSuccess());
        assertEquals(queryResult.getValue().latestCourseView().course(), course);
        assertEquals(queryResult.getValue().latestLearningResult(), learningResult);
    }

}

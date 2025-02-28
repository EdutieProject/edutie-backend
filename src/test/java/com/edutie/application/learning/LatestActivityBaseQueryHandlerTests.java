package com.edutie.application.learning;

import com.edutie.application.learning.ancillaries.LatestActivityQueryHandler;
import com.edutie.application.learning.ancillaries.queries.LatestActivityQuery;
import com.edutie.application.learning.ancillaries.viewmodels.LatestActivityView;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.domain.core.learning.learningresult.entities.submission.common.SolutionSubmissionBase;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.mocks.EducationMocks;
import com.edutie.mocks.LearningResourceMocks;
import com.edutie.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class LatestActivityBaseQueryHandlerTests {
    @Autowired
    MockUser mockUser;
    // Handlers
    @Autowired
    private LatestActivityQueryHandler latestActivityQueryHandler;
    @Autowired
    private LearningSubjectPersistence learningSubjectPersistence;
    @Autowired
    private LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    @Autowired
    private LearningResultPersistence learningResultPersistence;
    @Autowired
    private LearningExperiencePersistence learningExperiencePersistence;
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
        LearningSubject learningSubject = EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile());
        learningSubjectPersistence.save(learningSubject).throwIfFailure();
        StaticLearningResourceDefinition staticLearningResourceDefinition = StaticLearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                PromptFragment.empty(), PromptFragment.empty(),
                Set.of(learningSubject)
        );
        learningResourceDefinitionPersistence.save(staticLearningResourceDefinition).throwIfFailure();

        Science science = Science.create(mockUser.getEducatorProfile()).getValue();
        sciencePersistence.save(science).throwIfFailure();
        course = Course.create(mockUser.getEducatorProfile(), science);
        coursePersistence.save(course).throwIfFailure();
        Lesson lesson = Lesson.create(mockUser.getEducatorProfile(), course);
        lessonPersistence.save(lesson).throwIfFailure();
        Segment segment = Segment.create(mockUser.getEducatorProfile(), lesson);
        segment.setLearningResourceDefinitionId(staticLearningResourceDefinition.getId());
        segmentPersistence.save(segment).throwIfFailure();

        LearningExperience learningExperience = LearningResourceMocks.sampleLearningResource(mockUser.getStudentProfile(), mockUser.getEducatorProfile());
        learningExperience.getRequirements().stream().map(ElementalRequirement::getLearningRequirement).collect(Collectors.toSet()).forEach(
                o -> learningSubjectPersistence.save(o).throwIfFailure()
        );
        learningExperiencePersistence.save(learningExperience).throwIfFailure();
        learningResult = LearningResult.create(SolutionSubmissionBase.create(
                mockUser.getStudentProfile(), learningExperience.getId(), learningExperience.getDefinitionType() , "", 0
                ), Feedback.of(""), Set.of());
        learningResultPersistence.save(learningResult).throwIfFailure();
    }

    @Test
    public void getLatestActivityDynamicDefinitionSuccessTest() {
        LatestActivityQuery query = new LatestActivityQuery().studentUserId(mockUser.getUserId());

        WrapperResult<LatestActivityView> queryResult = latestActivityQueryHandler.handle(query);

        assertTrue(queryResult.isSuccess());
        assertEquals(queryResult.getValue().latestLearningResult(), learningResult);
    }

    @Test
    public void getLatestActivityStaticDefinitionSuccessTest() {
        // TODO
        // Consider not doing this as LRD is a question mark of the future development
    }

}

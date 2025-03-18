package com.edutie.application.learning.learningsubject.implementation;

import com.edutie.TestUtils;
import com.edutie.application.learning.learningsubject.GetLearningSubjectLearningViewByIdQueryHandler;
import com.edutie.application.learning.learningsubject.query.GetLearningSubjectStudentViewByIdQuery;
import com.edutie.application.learning.learningsubject.view.LearningSubjectLearningView;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

@SpringBootTest
class GetLearningSubjectLearningViewByIdQueryHandlerImplementationTest {
    @Autowired
    private LearningSubjectPersistence learningSubjectPersistence;

    private GetLearningSubjectLearningViewByIdQueryHandler getLearningSubjectLearningViewByIdQueryHandler;

    private static LearningSubject learningSubject;

    @BeforeEach
    public void setUp() throws Throwable {
        learningSubject = new LearningSubject();
        TestUtils.setPrivateField(learningSubject, "id", new LearningSubjectId());
        learningSubjectPersistence.save(learningSubject).throwIfFailure();
    }

    @Test
    void handle() {
        getLearningSubjectLearningViewByIdQueryHandler = new GetLearningSubjectLearningViewByIdQueryHandlerImplementation(
                learningSubjectPersistence
        );

        GetLearningSubjectStudentViewByIdQuery query = new GetLearningSubjectStudentViewByIdQuery()
                .studentUserId(new UserId())
                .learningSubjectId(learningSubject.getId());
        WrapperResult<LearningSubjectLearningView> result = getLearningSubjectLearningViewByIdQueryHandler.handle(query);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(learningSubject.getId(), result.getValue().learningSubject().getId());
    }
}
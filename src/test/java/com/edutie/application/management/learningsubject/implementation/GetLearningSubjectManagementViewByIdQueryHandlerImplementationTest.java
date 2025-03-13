package com.edutie.application.management.learningsubject.implementation;

import com.edutie.TestUtils;
import com.edutie.application.management.learningsubject.GetLearningSubjectManagementViewByIdQueryHandler;
import com.edutie.application.management.learningsubject.query.GetLearningSubjectByIdQuery;
import com.edutie.application.management.learningsubject.view.LearningSubjectManagementView;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.infrastructure.persistence.implementation.education.repositories.LearningSubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetLearningSubjectManagementViewByIdQueryHandlerImplementationTest {
    @Autowired
    private GetLearningSubjectManagementViewByIdQueryHandler getLearningSubjectManagementViewByIdQueryHandler;
    @Autowired
    private LearningSubjectRepository learningSubjectRepository;

    @Test
    public void handle() throws Throwable {
        LearningSubject learningSubject = new LearningSubject();
        TestUtils.setPrivateField(learningSubject, "id", new LearningSubjectId());
        learningSubjectRepository.save(learningSubject);

        GetLearningSubjectByIdQuery query = new GetLearningSubjectByIdQuery()
                .educatorUserId(new UserId()) // user id doesnt matter
                .learningSubjectId(learningSubject.getId());

        WrapperResult<LearningSubjectManagementView> result = getLearningSubjectManagementViewByIdQueryHandler.handle(query);

        assertTrue(result.isSuccess());
        assertEquals(learningSubject, result.getValue().learningSubject());
    }
}
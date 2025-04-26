package com.edutie.application.management.learningsubject.implementation;

import com.edutie.TestUtils;
import com.edutie.application.management.learningsubject.GetCreatedLearningSubjectsQueryHandler;
import com.edutie.application.management.learningsubject.query.GetCreatedLearningSubjectsQuery;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.infrastructure.persistence.implementation.education.repositories.LearningSubjectRepository;
import com.edutie.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GetCreatedLearningSubjectsQueryHandlerImplementationTest {
    @Autowired
    private GetCreatedLearningSubjectsQueryHandler getCreatedLearningSubjectsQueryHandler;
    @Autowired
    private LearningSubjectRepository learningSubjectRepository;
    @Autowired
    MockUser mockUser;

    @BeforeEach
    public void setUp() {
        mockUser.saveToPersistence();
    }


    @Test
    void handle() throws Throwable {
        LearningSubject learningSubject = new LearningSubject();
        TestUtils.setPrivateField(learningSubject, "id", new LearningSubjectId());
        TestUtils.setPrivateField(learningSubject, "authorEducator", mockUser.getEducatorProfile());
        learningSubjectRepository.save(learningSubject);

        GetCreatedLearningSubjectsQuery query = new GetCreatedLearningSubjectsQuery()
                .educatorUserId(mockUser.getUserId());

        WrapperResult<List<LearningSubject>> result = getCreatedLearningSubjectsQueryHandler.handle(query);

        assertTrue(result.isSuccess());
        assertEquals(1, result.getValue().size());
        assertEquals(learningSubject, result.getValue().getFirst());
    }
}
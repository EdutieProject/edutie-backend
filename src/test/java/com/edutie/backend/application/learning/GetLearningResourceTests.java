package com.edutie.backend.application.learning;

import com.edutie.backend.application.learning.learningresource.GetLearningResourceByIdQueryHandler;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.student.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class GetLearningResourceTests {
    @Autowired
    private GetLearningResourceByIdQueryHandler getLearningResourceByIdQueryHandler;
    private LearningResourcePersistence learningResourcePersistence;
    private final UserId userId = new UserId();

    @Test
    public void getLearningResourceByIdTest() {
        LearningResource learningResource = LearningResource.create(null, Activity.create("", Set.of()), Theory.create("",""), Set.of());
        learningResourcePersistence.save(learningResource).throwIfFailure();

        assert learningResourcePersistence.getById(learningResource.getId()).isSuccess();
        assert learningResourcePersistence.getById(learningResource.getId()).getValue().equals(learningResource);
    }
}

package com.edutie.backend.infrastructure.persistence.implementation.education;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.backend.domain.education.exercisetype.persistence.ExerciseTypePersistence;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.EducatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExerciseTypePersistenceTests {
    @Autowired
    private ExerciseTypePersistence exerciseTypePersistence;
    @Autowired
    private EducatorRepository educatorRepository;
    private final Educator educator = Educator.create(new UserId(), Administrator.create(new UserId()));

    @BeforeEach
    public void testSetup() {
        educatorRepository.save(educator);
    }

    @Test
    public void onlySaveTest() {
        ExerciseType exerciseType = ExerciseType.create(educator);
        exerciseType.setName("Dupa");
        assert exerciseTypePersistence.save(exerciseType).isSuccess();

        ExerciseType fetched = exerciseTypePersistence.getById(exerciseType.getId()).getValue();

        assertEquals("Dupa", fetched.getName());
    }

    @Test
    public void reportTemplateSaveTest() {
        ExerciseType exerciseType = ExerciseType.create(educator);
        exerciseType.appendReportTemplateParagraph("T1", "hello");
        exerciseType.appendReportTemplateParagraph("T2", "world");
        exerciseType.appendReportTemplateParagraph("T3", "universe");
        assert exerciseTypePersistence.save(exerciseType).isSuccess();

        ExerciseType fetched = exerciseTypePersistence.getById(exerciseType.getId()).getValue();
        assertEquals("T1", fetched.getReportTemplate().get(0).getTitle());
        assertEquals("T3", fetched.getReportTemplate().get(2).getTitle());
    }

    @Test
    public void reportTemplateRemoveTest() {
        ExerciseType exerciseType = ExerciseType.create(educator);
        exerciseType.appendReportTemplateParagraph("T1", "hello");
        exerciseType.appendReportTemplateParagraph("T2", "world");
        exerciseType.appendReportTemplateParagraph("T3", "universe");
        assert exerciseTypePersistence.save(exerciseType).isSuccess();

        assert exerciseType.removeReportTemplateParagraph(1).isSuccess();
        assert exerciseTypePersistence.save(exerciseType).isSuccess();

        ExerciseType fetched = exerciseTypePersistence.getById(exerciseType.getId()).getValue();
        assertEquals(2, fetched.getReportTemplate().size());
        assertEquals("T3", fetched.getReportTemplate().get(1).getTitle());
    }
}

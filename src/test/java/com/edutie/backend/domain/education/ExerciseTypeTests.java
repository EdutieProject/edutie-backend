package com.edutie.backend.domain.education;

import com.edutie.backend.domain.administration.administrator.Administrator;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.edutie.backend.domain.administration.administrator.identities.AdministratorId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ExerciseTypeTests {
    private final UserId userId = new UserId();
    private final Administrator administrator = Administrator.create(new UserId());
    private final Educator educator = Educator.create(userId, administrator);

    @Test
    public void paragraphAppendTest() {
        ExerciseType exerciseType = ExerciseType.create(educator);
        exerciseType.appendReportTemplateParagraph("Title1", "Description1");
        exerciseType.appendReportTemplateParagraph("Title2", "Description2");

        assert exerciseType.getReportTemplate().size() == 2;
    }

    @Test
    public void paragraphMoveTest() {
        ExerciseType exerciseType = ExerciseType.create(educator);
        exerciseType.appendReportTemplateParagraph("Title1", "Dupa1");
        exerciseType.appendReportTemplateParagraph("Title2", "Dupa2");
        exerciseType.appendReportTemplateParagraph("Title3", "Dupa3");
        assert exerciseType.moveReportTemplateParagraph(0, 2).isSuccess();

        assertEquals(exerciseType.getReportTemplate().get(0).getTitle(), "Title2");
        assertEquals(exerciseType.getReportTemplate().get(2).getTitle(), "Title1");
    }

    @Test
    public void paragraphInsertTest() {
        ExerciseType exerciseType = ExerciseType.create(educator);
        exerciseType.appendReportTemplateParagraph("T0", "nfoiu3fofwenwef");
        exerciseType.appendReportTemplateParagraph("T1", "nfoiu3fofwenwef");
        exerciseType.appendReportTemplateParagraph("T2", "nfoiu3fofwenwef");
        exerciseType.appendReportTemplateParagraph("T3", "nfoiu3fofwenwef");

        assert exerciseType.insertReportTemplateParagraph(2, "Dupa", "rwnfoi3rwnfneoifwnf").isSuccess();

        assertEquals(exerciseType.getReportTemplate().get(2).getTitle(), "Dupa");
        assertEquals(exerciseType.getReportTemplate().get(4).getTitle(), "T3");
    }

    @Test
    public void paragraphRemoveTest() {
        ExerciseType exerciseType = ExerciseType.create(educator);
        exerciseType.appendReportTemplateParagraph("T0", "nfoiu3fofwenwef");
        exerciseType.appendReportTemplateParagraph("T1", "nfoiu3fofwenwef");
        exerciseType.appendReportTemplateParagraph("T2", "nfoiu3fofwenwef");

        assert exerciseType.removeReportTemplateParagraph(0).isSuccess();

        assertEquals(exerciseType.getReportTemplate().get(0).getTitle(), "T1");
    }
}

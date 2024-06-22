package com.edutie.backend.domain.education;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;

@SpringBootTest
public class ExerciseTypeTests {

    private final UserId userId = new UserId();
    private final AdminId adminId = new AdminId();
    private Educator educator = Educator.create(userId, adminId);

    @Test
    public void paragraphTests() {
        ExerciseType exerciseType = ExerciseType.create(educator);
        exerciseType.appendReportTemplateParagraph("Title1", "Description1");
        exerciseType.appendReportTemplateParagraph("Title2", "Description2");

        assert exerciseType.getReportTemplate().size() == 2;
    }
}

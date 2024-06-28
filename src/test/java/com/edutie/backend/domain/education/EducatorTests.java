package com.edutie.backend.domain.education;

import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.enums.EducatorType;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EducatorTests {
    private final UserId userId = new UserId();
    private final AdminId adminId = new AdminId();

    @Test
    public void hasPermissionsOfTest() {
        Educator educator = Educator.create(userId, adminId);
        educator.setType(EducatorType.TUTOR);
        assert educator.hasPermissionsOf(EducatorType.CONTRIBUTOR);
        assert educator.hasPermissionsOf(EducatorType.TUTOR);
        assert !educator.hasPermissionsOf(EducatorType.PEDAGOGUE);
    }

    @Test
    public void isAuthorOfTest() {
        Educator educator = Educator.create(userId, adminId);
        ExerciseType exerciseType = ExerciseType.create(educator);
        ExerciseType exerciseType2 = ExerciseType.create(Educator.create(userId, adminId));
        
        assert educator.isAuthorOf(exerciseType);
        assert !educator.isAuthorOf(exerciseType2);
    }
}

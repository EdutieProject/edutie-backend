package com.edutie.backend.domain.education;

import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.identities.AdministratorId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.enums.EducatorType;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EducatorTests {
    private final UserId userId = new UserId();
    private final Administrator administrator = Administrator.create(new UserId());

    @Test
    public void administratorEducatorCreationTest() {
        Educator educator = Educator.create(userId, Administrator.create(userId));
        assert educator.getType().equals(EducatorType.ADMINISTRATOR);
    }

    @Test
    public void hasPermissionsOfTest() {
        Educator educator = Educator.create(userId, administrator);
        educator.setType(EducatorType.TUTOR);
        assert educator.hasPermissionsOf(EducatorType.CONTRIBUTOR);
        assert educator.hasPermissionsOf(EducatorType.TUTOR);
        assert !educator.hasPermissionsOf(EducatorType.PEDAGOGUE);
    }

    @Test
    public void isAuthorOfTest() {
        Educator educator = Educator.create(userId, administrator);
        ExerciseType exerciseType = ExerciseType.create(educator);
        ExerciseType exerciseType2 = ExerciseType.create(Educator.create(userId, administrator));
        
        assert educator.isAuthorOf(exerciseType);
        assert !educator.isAuthorOf(exerciseType2);
    }
}

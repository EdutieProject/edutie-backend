package com.edutie.domain.core.education.educator;

import com.edutie.TestUtils;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.common.base.EducatorCreated;
import com.edutie.domain.core.education.educator.enums.EducatorType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

@SpringBootTest
class EducatorTest {
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
        EducatorCreated createdEntity = () -> educator;
        EducatorCreated createdEntity2 = () -> Educator.create(new UserId(), administrator);

        assert educator.isAuthorOf(createdEntity).isSuccess();
        assert educator.isAuthorOf(createdEntity2).isFailure();
    }

    @Test
    void testEquals() throws Throwable {
        Educator educator1 = Educator.create(userId, administrator);
        Educator educator2 = Educator.create(userId, administrator);
        Educator educator3 = Educator.create(userId, administrator);

        TestUtils.setPrivateField(educator2, "id", educator1.getId());

        assert educator1.equals(educator2);
        assert !educator2.equals(educator3);
    }

}
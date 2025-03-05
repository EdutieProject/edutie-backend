package com.edutie.domain.core.education.educator;

import com.edutie.TestUtils;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.common.base.EducatorCreated;
import com.edutie.domain.core.education.educator.enums.EducatorType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EducatorTest {
    private final UserId userId = new UserId();

    @Test
    public void hasPermissionsOfTest() {
        Educator educator = Educator.create(userId);
        educator.setType(EducatorType.VERIFIED);
        assert educator.hasPermissionsOf(EducatorType.COMMUNITY);
        assert educator.hasPermissionsOf(EducatorType.VERIFIED);
        assert !educator.hasPermissionsOf(EducatorType.ADMINISTRATOR);
    }

    @Test
    public void isAuthorOfTest() {
        Educator educator = Educator.create(userId);
        EducatorCreated createdEntity = () -> educator;
        EducatorCreated createdEntity2 = () -> Educator.create(new UserId());

        assert educator.isAuthorOf(createdEntity).isSuccess();
        assert educator.isAuthorOf(createdEntity2).isFailure();
    }

    @Test
    void testEquals() throws Throwable {
        Educator educator1 = Educator.create(userId);
        Educator educator2 = Educator.create(userId);
        Educator educator3 = Educator.create(userId);

        TestUtils.setPrivateField(educator2, "id", educator1.getId());

        assert educator1.equals(educator2);
        assert !educator2.equals(educator3);
    }

}
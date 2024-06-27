package com.edutie.backend.domain.education;

import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.enums.EducatorType;
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
}

package com.edutie.domain.core.learning.student;

import com.edutie.TestUtils;
import com.edutie.domain.core.administration.UserId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentTest {
    private final UserId userId = new UserId();

    @Test
    void create() {
        Student student = Student.create(userId);

        assertEquals(student.getOwnerUserId(), userId);
    }

    @Test
    void testEquals() throws Throwable {
        Student student1 = Student.create(userId);
        Student student2 = Student.create(userId);
        Student student3 = Student.create(userId);

        TestUtils.setPrivateField(student2, "id", student1.getId());

        assert student1.equals(student2);
        assert !student2.equals(student3);
    }

}
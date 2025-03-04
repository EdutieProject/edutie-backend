package com.edutie.infrastructure.persistence.implementation.profiles;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentPersistenceImplementationTest {
    private final UserId userId = new UserId();
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private StudentPersistenceImplementation studentPersistenceImplementation;

    @Test
    void getByAuthorizedUserId() {
        Student student1 = Student.create(userId);
        studentRepository.save(student1);

        Student student = studentPersistenceImplementation.getByAuthorizedUserId(userId);
        assertNotNull(student);
    }

    @Test
    void removeForUser() {
        Student student = Student.create(userId);
        studentRepository.save(student);

        studentPersistenceImplementation.removeForUser(userId).throwIfFailure();

        assertFalse(studentRepository.findByOwnerUserId(userId).isPresent());
    }
}
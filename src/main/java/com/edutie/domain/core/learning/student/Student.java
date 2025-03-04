package com.edutie.domain.core.learning.student;

import com.edutie.domain.core.administration.Role;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.learning.student.identities.StudentId;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Student class conceals all the student characteristics of the user.
 * Note that the relationship with the user is maintained by createdBy field in the base class.
 */
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class Student extends Role<StudentId> {

    public static Student create(UserId userId) {
        Student student = new Student();
        student.setOwnerUserId(userId);
        student.setId(new StudentId());
        return student;
    }

    private static LocalDateTime getLatestResultsDateThreshold() {
        return LocalDateTime.now().minusDays(7);
    }

}

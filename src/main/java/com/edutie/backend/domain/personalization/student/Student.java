package com.edutie.backend.domain.personalization.student;

import com.edutie.backend.domain.administration.Role;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.student.enums.SchoolType;
import com.edutie.backend.domain.personalization.student.errors.StudentErrors;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import com.edutie.backend.domain.personalization.student.valueobjects.SchoolStage;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.Result;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Student class conceals all the student characteristics of the user.
 * Note that the relationship with the user is maintained by createdBy field in the base class.
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Student extends Role<StudentId> {
    @Embedded
    private SchoolStage schoolStage = new SchoolStage();
    private LocalDate birthdate = null;
    @OneToMany
    private final Set<LearningResult> learningHistory = new HashSet<>();

    /**
     * Default constructor used for associating student's account with a user.
     * Initializes all fields to default values. This constructor should be used
     * by the application services instead of no-args constructor because there
     * is no possibility to bond student entity to a user later.
     *
     * @param userId user's identity
     */
    public static Student create(UserId userId) {
        Student student = new Student();
        student.setOwnerUserId(userId);
        student.setId(new StudentId());
        return student;
    }

    /**
     * Progresses the school stage based on current school stage. Common
     * use case is to advance a grade up.
     *
     * @param progressValue progress value
     * @return Result object
     */
    public Result changeSchoolStage(int progressValue) {
        if (schoolStage == null) return Result.failure(
                StudentErrors.schoolStageAlternationError()
        );
        var newSchoolStage = new SchoolStage(
                schoolStage.schoolType(),
                schoolStage.gradeNumber() + progressValue
        );
        return setSchoolStage(newSchoolStage);
    }

    //TODO: Implement validation after #31 VALIDATION FRAMEWORK REFACTORING
    public Result setSchoolStage(SchoolStage providedSchoolStage) {
        schoolStage = providedSchoolStage;
        return Result.success();
    }

    /**
     * Sets student school stage based on verbose School Type and grade Number parameters
     *
     * @param schoolType  type of student's school
     * @param gradeNumber number of the grade
     */
    public Result setSchoolStage(SchoolType schoolType, int gradeNumber) {
        SchoolStage schoolStage = new SchoolStage(schoolType, gradeNumber);
        return setSchoolStage(schoolStage);
    }

    /**
     * Sets student birthdate
     *
     * @param providedBirthdate a birthdate to be set
     * @return Rule validation result
     */
    public Result setBirthdate(LocalDate providedBirthdate) {
        birthdate = providedBirthdate;
        return Result.success();
    }
}

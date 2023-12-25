package com.edutie.edutiebackend.domain.core.student;

import java.time.LocalDate;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.identities.UserId;
import com.edutie.edutiebackend.domain.core.common.rule.Result;
import com.edutie.edutiebackend.domain.core.common.rule.Rule;
import com.edutie.edutiebackend.domain.core.student.entites.LearningParameters;
import com.edutie.edutiebackend.domain.core.student.enums.SchoolType;
import com.edutie.edutiebackend.domain.core.student.errors.StudentErrors;
import com.edutie.edutiebackend.domain.core.student.exceptions.TraitTrackerNotFoundException;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;
import com.edutie.edutiebackend.domain.core.student.rules.StudentAgeBoundsRule;
import com.edutie.edutiebackend.domain.core.student.rules.SchoolGradeNumberRule;
import com.edutie.edutiebackend.domain.core.student.valueobjects.SchoolStage;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Student class conceals all the student characteristics of the user.
 * This is an aggregate root of the student.
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Student extends AuditableEntityBase<StudentId> {
    @Nullable
    @Getter
    private SchoolStage schoolStage = null;
    @Nullable
    @Getter
    private LocalDate birthdate = null;
    // one-to-one relationship
    private final LearningParameters learningParameters = new LearningParameters();
    @Getter
    // one-to-one relationship with user
    private UserId userId;

    /**
     * Default constructor used for associating student's account with a user.
     * Initializes all fields to default values. This constructor should be used
     * by the application services instead of no-args constructor because there
     * is no possibility to bond student entity to a user later.
     * @param userId user's identity
     */
    public Student(UserId userId)
    {
        this.userId = userId;
    }

    /**
     * Adapts learning parameters based on provided progress value. May throw a runtime
     * exception if the provided trait class is not valid - there is no tracker
     * associated with it.
     * @param traitClass class of trait
     * @param trait concrete trait
     * @param progressValue progress measured as double. May be negative
     * @param <T> type of trait
     * @see LearningParameters
     */
    public <T extends Enum<T>> void adaptLearningParameters(Class<T> traitClass, T trait, double progressValue)
    {
        learningParameters.adapt(traitClass, trait, progressValue);
    }

    /**
     *  Retrieves value of tracked learning parameter. May throw a runtime
     *  exception if the provided trait class is not valid - there is no
     *  tracker associated with it.
     * @param traitClass trait class
     * @param trait concrete trait
     * @return Value of given trait parameter. 0.0 if it's not tracked
     * @param <T> type of trait
     * @see LearningParameters
     */
    public <T extends Enum<T>> double getLearningParameter(Class<T> traitClass, T trait)
    {
        var param =  learningParameters.getParameter(traitClass, trait);
        return param.orElse(0.0);
    }


    public Result<SchoolStage> changeSchoolStage(int progressValue){
        if (schoolStage == null) return Result.failure(
                StudentErrors.schoolStageAlternationError()
        );

        var newSchoolStage = new SchoolStage(
                schoolStage.schoolType(),
                schoolStage.gradeNumber() + progressValue
        );

        return setSchoolStage(newSchoolStage);
    }


    public Result<SchoolStage> setSchoolStage(SchoolStage providedSchoolStage){
        var validationResult = Rule.validate(SchoolGradeNumberRule.class, providedSchoolStage);
        if (validationResult.isSuccess())
            schoolStage = validationResult.getValue();
        return validationResult;
    }

    /**
     * Sets student school stage based on verbose School Type and grade Number parameters
     * @param schoolType type of student's school
     * @param gradeNumber number of the grade
     */
    public void setSchoolStage(SchoolType schoolType, int gradeNumber){
        SchoolStage schoolStage = new SchoolStage(schoolType, gradeNumber);
        this.setSchoolStage(schoolStage);
    }


    /**
     * Sets student birthdate
     * @param providedBirthdate a birthdate to be set
     * @return Rule validation result
     */
    public Result<LocalDate> setBirthdate(LocalDate providedBirthdate){
        var validationResult = Rule.validate(StudentAgeBoundsRule.class, providedBirthdate);
        if(validationResult.isSuccess())
            birthdate = validationResult.getValue();
        return validationResult;
    }

}

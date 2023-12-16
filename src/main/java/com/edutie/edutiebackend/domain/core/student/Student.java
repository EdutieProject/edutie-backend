package com.edutie.edutiebackend.domain.core.student;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.student.exceptions.InvalidBirthDateException;
import com.edutie.edutiebackend.domain.core.common.identities.UserId;
import com.edutie.edutiebackend.domain.core.student.validation.StudentBirthdateValidator;
import com.edutie.edutiebackend.domain.core.student.valueobjects.SchoolStage;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;
import com.edutie.edutiebackend.domain.core.student.entites.LearningParameters;
import com.edutie.edutiebackend.domain.core.student.exceptions.InvalidSchoolStageException;
import com.edutie.edutiebackend.domain.core.student.exceptions.TraitTrackerNotFoundException;
import com.edutie.edutiebackend.domain.core.student.validation.SchoolStageValidator;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
     * exception if the provided trait class is not valid - there is no
     * associated with it.
     * @param traitClass class of trait
     * @param trait concrete trait
     * @param progressValue progress measured as double. May be negative
     * @param <T> type of trait
     * @see LearningParameters
     */
    public <T extends Enum<T>> void adaptLearningParameters(Class<T> traitClass, T trait, double progressValue)
    {
        try {
            learningParameters.adapt(traitClass, trait, progressValue);
        } catch (TraitTrackerNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        try {
            var param =  learningParameters.getParameter(traitClass, trait);
            return param.orElse(0.0);
        } catch (TraitTrackerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Changes the student grade promoting him given amount of grades if it is possible.
     * Throws an exception otherwise. Does nothing if there is no school stage set.
     * @param progressValue numbers of grades to shift forward. Goes backwards if negative.
     * @throws InvalidSchoolStageException exception thrown if the school value would be invalid
     * after using this method.
     */
    public void changeSchoolStage(int progressValue) throws InvalidSchoolStageException {
        if (schoolStage == null) return;

        var newSchoolStage = new SchoolStage(
                schoolStage.schoolType(),
                schoolStage.gradeNumber() + progressValue
        );

        if (SchoolStageValidator.isValid(newSchoolStage)) {
            schoolStage = newSchoolStage;
        }
    }

    /**
     * Sets student school stage
     * @throws InvalidSchoolStageException exception thrown when schoolStage is invalid
     * @see SchoolStageValidator School stage validation rules
     */
    public void setSchoolStage(SchoolStage schoolStage) throws InvalidSchoolStageException {
        if(SchoolStageValidator.isValid(schoolStage))
            this.schoolStage = schoolStage;
    }


    /**
     * Sets student's birthdate
     * @throws InvalidBirthDateException exception thrown when given birthdate is invalid
     * @see StudentBirthdateValidator Rules for validating student's birthdate
     */
    public void setBirthdate(LocalDate birthdate) throws InvalidBirthDateException {
        if(StudentBirthdateValidator.isValid(birthdate))
            this.birthdate = birthdate;
    }

}

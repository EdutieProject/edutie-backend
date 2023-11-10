package com.edutie.edutiebackend.domain.student;

import com.edutie.edutiebackend.domain.common.identities.StudentId;
import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.student.entites.LearningParameters;
import com.edutie.edutiebackend.domain.student.exceptions.InvalidSchoolStageException;
import com.edutie.edutiebackend.domain.student.exceptions.TraitTrackerNotFoundException;
import com.edutie.edutiebackend.domain.student.validation.SchoolStageValidator;
import com.edutie.edutiebackend.domain.student.valueobjects.SchoolStage;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Student class conceals all the student characteristics of the user.
 * This is an aggregate root of the student.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Student extends EntityBase<StudentId> {
    private SchoolStage schoolStage;
    private LearningParameters learningParameters;

    /**
     * Adapts learning parameters based on provided progress value
     * @param traitClass class of trait
     * @param trait concrete trait
     * @param progressValue progress measured as double
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
     *  Retrieves value of tracked learning parameter.
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
     * Progresses the student assigning them to the next grade
     * @param progressValue numbers of grades to shift forward. May be negative
     * @throws InvalidSchoolStageException - thrown when student cannot progress bcs
     * the next grade is out of school type bonds.
     */
    public void progressSchoolStage(int progressValue) throws InvalidSchoolStageException {
        var newSchoolStage = new SchoolStage(
                schoolStage.schoolType(),
                schoolStage.gradeNumber() + progressValue);
        if (SchoolStageValidator.isValid(schoolStage)) {
            schoolStage = newSchoolStage;
        }
        else {
            throw new InvalidSchoolStageException("School stage is invalid");
        }
    }

}

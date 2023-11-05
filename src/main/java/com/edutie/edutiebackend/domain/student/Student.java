package com.edutie.edutiebackend.domain.student;

import com.edutie.edutiebackend.domain.common.identities.StudentId;
import com.edutie.edutiebackend.domain.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.common.studenttraits.Skill;
import com.edutie.edutiebackend.domain.student.entites.IntelligenceProfile;
import com.edutie.edutiebackend.domain.student.entites.SkillsProfile;
import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.student.entites.base.StudentTraitProfile;
import com.edutie.edutiebackend.domain.student.validation.exceptions.InvalidSchoolStageException;
import com.edutie.edutiebackend.domain.student.validation.SchoolStageValidator;
import com.edutie.edutiebackend.domain.student.valueobjects.SchoolStage;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Student class conceals all the student characteristics of the user.
 * This is an aggregate root of the student.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Student extends EntityBase<StudentId> {
    private SchoolStage schoolStage;
    private IntelligenceProfile intelligenceProfile;
    private SkillsProfile skillsProfile;

    /**
     * Adjusts intelligence profile with given amount of points
     * @param intelligence intelligence to target
     * @param points points amount
     */
    public void adjustProfile(Intelligence intelligence, double points)
    {
        intelligenceProfile.adjust(intelligence, points);
    }

    /**
     * Adjusts skill profile with given amount of points
     * @param skill skill to target
     * @param points points amount
     */
    public void adjustProfile(Skill skill, double points)
    {
        skillsProfile.adjust(skill, points);
    }

    /**
     * Generic method to adjust given TraitProfile chosen by specifying Trait Type
     * @param traitPoints points to adjust
     * @param <TTrait> type of trait
     */
    public <TTrait extends Enum<TTrait>> void adjustProfile(HashMap<TTrait, Double> traitPoints)
    {
        //TODO!
    }

    /**
     * Progresses the student assigning them to the next grade
     * @throws InvalidSchoolStageException - thrown when student cannot progress bcs
     * the next grade may not exist.
     */
    public void progressSchoolStage() throws InvalidSchoolStageException {
        var newSchoolStage = new SchoolStage(
                schoolStage.schoolType(),
                schoolStage.gradeNumber() + 1);
        if (SchoolStageValidator.isValid(schoolStage))
        {
            schoolStage = newSchoolStage;
        }
        else
        {
            throw new InvalidSchoolStageException("School stage is invalid");
        }
    }

}

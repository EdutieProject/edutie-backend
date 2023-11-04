package com.edutie.edutiebackend.domain.student;

import com.edutie.edutiebackend.domain.common.identities.StudentId;
import com.edutie.edutiebackend.domain.student.entites.IntelligenceProfile;
import com.edutie.edutiebackend.domain.student.entites.SkillsProfile;
import com.edutie.edutiebackend.domain.common.base.EntityBase;
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
    private IntelligenceProfile intelligenceProfile;
    private SkillsProfile skillsProfile;
}

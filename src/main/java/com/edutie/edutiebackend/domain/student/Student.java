package com.edutie.edutiebackend.domain.student;

import com.edutie.edutiebackend.domain.common.identities.StudentId;
import com.edutie.edutiebackend.domain.student.entites.IntelligenceProfile;
import com.edutie.edutiebackend.domain.student.entites.LearningHistory;
import com.edutie.edutiebackend.domain.student.entites.SkillsProfile;
import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.student.valueobjects.SchoolStage;
import jakarta.persistence.Entity;

/**
 * Student class concealing all the student characteristics of the user.
 * This is an aggregate root of the student.
 */
@Entity
public class Student extends EntityBase<StudentId> {
    public SchoolStage schoolStage;
    public LearningHistory learningHistory;
    public IntelligenceProfile intelligenceProfile;
    public SkillsProfile skillsProfile;
}

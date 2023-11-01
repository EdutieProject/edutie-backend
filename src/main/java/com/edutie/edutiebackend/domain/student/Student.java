package com.edutie.edutiebackend.domain.student;

import com.edutie.edutiebackend.domain.student.entites.IntelligenceProfile;
import com.edutie.edutiebackend.domain.student.entites.LearningHistoryProfile;
import com.edutie.edutiebackend.domain.student.entites.SkillsProfile;
import com.edutie.edutiebackend.domain.common.EntityBase;
import jakarta.persistence.Entity;

@Entity
public class Student extends EntityBase<Student> {
    public LearningHistoryProfile learningHistory;
    public IntelligenceProfile intelligenceProfile;
    public SkillsProfile skillsProfile;
}

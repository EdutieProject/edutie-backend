package com.edutie.edutiebackend.domain.student;

import com.edutie.edutiebackend.domain.student.studentProfiles.IntelligenceProfile;
import com.edutie.edutiebackend.domain.student.studentProfiles.LearningHistoryProfile;
import com.edutie.edutiebackend.domain.student.studentProfiles.SkillsProfile;
import com.edutie.edutiebackend.domain.common.EntityBase;
import jakarta.persistence.Entity;

@Entity
public class Student extends EntityBase<Student> {
    public LearningHistoryProfile learningHistory;
    public IntelligenceProfile intelligenceProfile;
    public SkillsProfile skillsProfile;
}

package com.edutie.edutiebackend.domain.core.student;

import com.edutie.edutiebackend.domain.core.student.studentProfiles.IntelligenceProfile;
import com.edutie.edutiebackend.domain.core.student.studentProfiles.LearningHistoryProfile;
import com.edutie.edutiebackend.domain.core.student.studentProfiles.SkillsProfile;
import com.edutie.edutiebackend.domain.core.common.EntityBase;
import jakarta.persistence.Entity;

@Entity
public class Student extends EntityBase<Student> {
    public LearningHistoryProfile learningHistory;
    public IntelligenceProfile intelligenceProfile;
    public SkillsProfile skillsProfile;
}

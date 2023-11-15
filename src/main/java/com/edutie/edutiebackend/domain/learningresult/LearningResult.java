package com.edutie.edutiebackend.domain.learningresult;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.lessonsegment.identities.CommonSkillId;
import com.edutie.edutiebackend.domain.learningresource.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.learningresult.identities.LearningResultId;

import com.edutie.edutiebackend.domain.student.identities.StudentId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningResult extends EntityBase<LearningResultId> {
    private LearningResourceId learningResourceId;
    private StudentId studentId;
    private HashMap<CommonSkillId, Integer> skillPoints;
}

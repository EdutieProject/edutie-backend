package com.edutie.edutiebackend.domain.learningresult;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.CommonSkillId;
import com.edutie.edutiebackend.domain.common.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.common.identities.LearningResultId;

import com.edutie.edutiebackend.domain.common.identities.StudentId;
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
    // points mapped to common skills
    private HashMap<CommonSkillId, Integer> skillPoints;
}

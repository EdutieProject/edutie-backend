package com.edutie.edutiebackend.domain.student.entites;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.LearningResultId;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class LearningHistory extends EntityBase<LearningHistory> {
    List<LearningResultId> learningHistory = new ArrayList<>();
}


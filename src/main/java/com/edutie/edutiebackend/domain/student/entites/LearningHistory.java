package com.edutie.edutiebackend.domain.student.entites;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.LearningResultId;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningHistory extends EntityBase<LearningHistory> {
    private List<LearningResultId> learningHistory = new ArrayList<>();
}


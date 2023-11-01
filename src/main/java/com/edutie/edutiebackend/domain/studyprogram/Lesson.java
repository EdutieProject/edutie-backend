package com.edutie.edutiebackend.domain.studyprogram;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.LessonId;
import jakarta.persistence.Entity;

@Entity
public class Lesson extends EntityBase<LessonId> {
    private String name;
}

package com.edutie.edutiebackend.domain.core.studyProgram;

import com.edutie.edutiebackend.domain.core.common.EntityBase;
import jakarta.persistence.Entity;

@Entity
public class Lesson extends EntityBase<Lesson> {
    private String name;
}

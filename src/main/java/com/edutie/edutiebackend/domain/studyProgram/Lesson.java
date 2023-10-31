package com.edutie.edutiebackend.domain.studyProgram;

import com.edutie.edutiebackend.domain.common.EntityBase;
import jakarta.persistence.Entity;

@Entity
public class Lesson extends EntityBase<Lesson> {
    private String name;
}

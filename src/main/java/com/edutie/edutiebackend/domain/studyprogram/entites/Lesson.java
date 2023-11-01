package com.edutie.edutiebackend.domain.studyprogram.entites;

import com.edutie.edutiebackend.domain.common.EntityBase;
import jakarta.persistence.Entity;

@Entity
public class Lesson extends EntityBase<Lesson> {
    private String name;
}

package com.edutie.edutiebackend.domain.studyprogram;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.CourseId;
import com.edutie.edutiebackend.domain.common.identities.LessonId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Lesson extends EntityBase<LessonId> {
    private CourseId courseId;
    private String name;
}

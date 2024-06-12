package com.edutie.backend.services.studyprogram.initializers.lesson;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.Course;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class LessonInitializationDetails {
    private Educator educator;
    private Course course;
    private String name;
    private String description;
}

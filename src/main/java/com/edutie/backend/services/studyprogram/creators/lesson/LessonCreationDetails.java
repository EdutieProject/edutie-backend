package com.edutie.backend.services.studyprogram.creators.lesson;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(fluent = true)
public class LessonCreationDetails {
    private Educator educator;
    private Lesson previousLesson;
    private Lesson nextLesson;
    private String name;
    private String description;
}

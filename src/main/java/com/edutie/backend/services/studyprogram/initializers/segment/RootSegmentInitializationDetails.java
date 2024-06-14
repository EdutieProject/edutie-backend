package com.edutie.backend.services.studyprogram.initializers.segment;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class RootSegmentInitializationDetails {
    private Educator educator;
    private Lesson lesson;
    private String name;
}

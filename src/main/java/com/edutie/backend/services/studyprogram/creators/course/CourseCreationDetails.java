package com.edutie.backend.services.studyprogram.creators.course;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.science.Science;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class CourseCreationDetails {
    private Educator educator;
    private Science science;
    private String name;
    private String description;
}

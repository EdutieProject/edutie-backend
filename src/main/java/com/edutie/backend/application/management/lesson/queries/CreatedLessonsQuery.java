package com.edutie.backend.application.management.lesson.queries;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CreatedLessonsQuery extends EducatorAction<CreatedLessonsQuery> {
    @Override
    protected CreatedLessonsQuery getThis() {
        return this;
    }
}

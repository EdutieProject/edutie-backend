package com.edutie.backend.application.management.science.commands;

import com.edutie.backend.application.common.actions.AdminAction;
import com.edutie.backend.application.common.actions.EducatorAction;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CreateScienceCommand extends EducatorAction<CreateScienceCommand> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull String scienceName;
    private String scienceDescription;

    @Override
    protected CreateScienceCommand getThis() {
        return this;
    }
}

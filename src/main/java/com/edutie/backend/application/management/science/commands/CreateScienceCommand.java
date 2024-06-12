package com.edutie.backend.application.management.science.commands;

import com.edutie.backend.application.common.actions.AdminAction;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
public class CreateScienceCommand extends AdminAction<CreateScienceCommand> {
    private @NonNull String scienceName;
    private String scienceDescription;

    @Override
    protected CreateScienceCommand getThis() {
        return this;
    }
}

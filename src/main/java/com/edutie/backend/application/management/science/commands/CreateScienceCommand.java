package com.edutie.backend.application.management.science.commands;

import com.edutie.backend.domain.administration.UserId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@NoArgsConstructor
public class CreateScienceCommand {
    private @NonNull UserId adminUserId;
    private @NonNull String scienceName;
    private String scienceDescription;
}

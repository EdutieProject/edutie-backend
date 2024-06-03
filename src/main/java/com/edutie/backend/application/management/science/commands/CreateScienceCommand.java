package com.edutie.backend.application.management.science.commands;

import com.edutie.backend.domain.administration.UserId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CreateScienceCommand {
    @JsonIgnore
    private @NonNull UserId adminUserId;
    private @NonNull String scienceName;
    private String scienceDescription;
}

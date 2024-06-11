package com.edutie.backend.application.common.actions;

import com.edutie.backend.domain.administration.UserId;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(fluent = true)
public abstract class StudentAction {
    @NonNull UserId studentUserId;
}

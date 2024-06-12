package com.edutie.backend.application.common.actions;

import com.edutie.backend.domain.administration.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(fluent = true)
public abstract class StudentAction<T extends StudentAction<T>> {
    @NonNull UserId studentUserId;

    public T studentUserId(UserId userId) {
        this.studentUserId = userId;
        return getThis();
    }

    protected abstract T getThis();
}

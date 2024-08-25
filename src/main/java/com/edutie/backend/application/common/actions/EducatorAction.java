package com.edutie.backend.application.common.actions;

import com.edutie.backend.domain.administration.UserId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * Action to be extended when a flow should be invoked by an educator
 *
 * @param <T>
 */
@Getter
@Accessors(fluent = true)
public abstract class EducatorAction<T extends EducatorAction<T>> {
    @JsonIgnore
    private @NonNull UserId educatorUserId;

    public T educatorUserId(UserId userId) {
        this.educatorUserId = userId;
        return getThis();
    }

    protected abstract T getThis();
}

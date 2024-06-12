package com.edutie.backend.application.common.actions;

import com.edutie.backend.domain.administration.UserId;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public abstract class AdminAction<T extends AdminAction<T>> {
    @NonNull UserId adminUserId;

    public T adminUserId(UserId userId) {
        this.adminUserId = userId;
        return getThis();
    }

    protected abstract T getThis();
}

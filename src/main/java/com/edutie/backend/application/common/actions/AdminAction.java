package com.edutie.backend.application.common.actions;

import com.edutie.backend.domain.administration.UserId;
import lombok.*;
import lombok.experimental.*;

/**
 * Action to be extended when a flow should be invoked by an administrator
 *
 * @param <T>
 */
@Getter
@Accessors(fluent = true)
public abstract class AdminAction<T extends AdminAction<T>> {

	public T adminUserId(UserId userId) {
		return getThis();
	}

	protected abstract T getThis();
}

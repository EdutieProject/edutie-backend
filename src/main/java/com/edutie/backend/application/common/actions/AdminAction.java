package com.edutie.backend.application.common.actions;

import com.edutie.backend.domain.administration.UserId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
	@JsonIgnore
	private UserId adminUserId;

	public T adminUserId(UserId userId) {
		this.adminUserId = userId;
		return getThis();
	}

	protected abstract T getThis();
}

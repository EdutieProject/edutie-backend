package com.edutie.backend.application.common.actions;

import com.edutie.backend.domain.administration.UserId;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

/**
 * Action to be extended when a flow should be invoked by a student
 *
 * @param <T>
 */
@Setter
@Getter
@Accessors(fluent = true)
public abstract class StudentAction<T extends StudentAction<T>> {
	@JsonIgnore
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private UserId studentUserId;

	public T studentUserId(UserId userId) {
		return getThis();
	}

	protected abstract T getThis();
}

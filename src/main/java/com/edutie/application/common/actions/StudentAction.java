package com.edutie.application.common.actions;

import com.edutie.domain.core.administration.UserId;
import com.fasterxml.jackson.annotation.*;
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
	private @NonNull UserId studentUserId;

	public T studentUserId(UserId userId) {
		this.studentUserId = userId;
		return getThis();
	}

	protected abstract T getThis();
}

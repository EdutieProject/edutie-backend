package com.edutie.application.common.actions;

import com.edutie.domain.core.administration.UserId;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.*;

/**
 * Action to be extended when a flow should be invoked by an educator
 *
 * @param <T>
 */
@Getter
@Accessors(fluent = true)
public abstract class EducatorAction<T extends EducatorAction<T>> {
	@JsonIgnore
	private UserId educatorUserId;

	public T educatorUserId(UserId userId) {
		this.educatorUserId = userId;
		return getThis();
	}

	protected abstract T getThis();
}

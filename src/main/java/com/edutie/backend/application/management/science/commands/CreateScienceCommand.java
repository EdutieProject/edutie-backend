package com.edutie.backend.application.management.science.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CreateScienceCommand extends EducatorAction<CreateScienceCommand> {
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull String scienceName;
	private String scienceDescription;

	@Override
	protected CreateScienceCommand getThis() {
		return this;
	}
}

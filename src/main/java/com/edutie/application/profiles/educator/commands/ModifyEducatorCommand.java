package com.edutie.application.profiles.educator.commands;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.education.educator.enums.EducatorType;
import com.edutie.domain.core.education.educator.identities.EducatorId;
import lombok.*;

public record ModifyEducatorCommand(
		@NonNull UserId adminUserId,
		@NonNull EducatorId educatorId,
		EducatorType educatorType) { }

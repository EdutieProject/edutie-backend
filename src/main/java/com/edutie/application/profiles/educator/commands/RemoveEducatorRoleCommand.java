package com.edutie.application.profiles.educator.commands;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.education.educator.identities.EducatorId;
import lombok.*;

public record RemoveEducatorRoleCommand(
		@NonNull UserId adminUserId,
		@NonNull EducatorId educatorId) { }

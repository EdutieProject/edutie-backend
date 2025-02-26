package com.edutie.application.profiles.educator.commands;

import com.edutie.domain.core.administration.UserId;
import lombok.*;

public record AssignEducatorRoleCommand(
		@NonNull UserId adminUserId,
		@NonNull UserId userToBeEducatorId) { }

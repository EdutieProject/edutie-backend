package com.edutie.backend.application.profiles.educator.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.profiles.educator.RemoveEducatorRoleCommandHandler;
import com.edutie.backend.application.profiles.educator.commands.RemoveEducatorRoleCommand;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import validation.Result;
import org.springframework.stereotype.*;
import lombok.*;

@Component
@RequiredArgsConstructor
public class RemoveEducatorRoleCommandHandlerImplementation extends HandlerBase implements RemoveEducatorRoleCommandHandler {
	private final EducatorPersistence educatorPersistence;

	@Override
	public Result handle(RemoveEducatorRoleCommand command) {
		return educatorPersistence.removeById(command.educatorId());
	}
}

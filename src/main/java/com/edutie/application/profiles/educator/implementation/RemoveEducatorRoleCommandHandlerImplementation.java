package com.edutie.application.profiles.educator.implementation;

import com.edutie.application.common.HandlerBase;
import com.edutie.application.profiles.educator.RemoveEducatorRoleCommandHandler;
import com.edutie.application.profiles.educator.commands.RemoveEducatorRoleCommand;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
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

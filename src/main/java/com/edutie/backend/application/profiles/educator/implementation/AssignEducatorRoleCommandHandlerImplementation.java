package com.edutie.backend.application.profiles.educator.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.profiles.educator.AssignEducatorRoleCommandHandler;
import com.edutie.backend.application.profiles.educator.commands.AssignEducatorRoleCommand;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;

@Component
@RequiredArgsConstructor
public class AssignEducatorRoleCommandHandlerImplementation extends HandlerBase implements AssignEducatorRoleCommandHandler {
	private final EducatorPersistence educatorPersistence;
	private final AdministratorPersistence administratorPersistence;

	@Override
	public WrapperResult<Educator> handle(AssignEducatorRoleCommand command) {
		Administrator administrator = administratorPersistence.getByAuthorizedUserId(command.adminUserId());
		Educator educator = Educator.create(command.userToBeEducatorId(), administrator);
		educatorPersistence.save(educator);
		return WrapperResult.successWrapper(educator);
	}
}

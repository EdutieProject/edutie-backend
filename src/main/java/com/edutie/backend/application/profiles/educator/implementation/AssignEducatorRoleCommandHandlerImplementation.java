package com.edutie.backend.application.profiles.educator.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.profiles.educator.AssignEducatorRoleCommandHandler;
import com.edutie.backend.application.profiles.educator.commands.AssignEducatorRoleCommand;
import com.edutie.backend.domain.administration.administrator.identities.AdministratorId;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;
@Component
@RequiredArgsConstructor
public class AssignEducatorRoleCommandHandlerImplementation extends HandlerBase implements AssignEducatorRoleCommandHandler {
    private final EducatorPersistence educatorPersistence;
    private final AdministratorPersistence administratorPersistence;
    @Override
    public WrapperResult<Educator> handle(AssignEducatorRoleCommand command) {
        AdministratorId administratorId = administratorPersistence.getAdminId(command.adminUserId());
        Educator educator = Educator.create(command.educatorUserId(), administratorId);
        educatorPersistence.save(educator);
        return WrapperResult.successWrapper(educator);
    }
}

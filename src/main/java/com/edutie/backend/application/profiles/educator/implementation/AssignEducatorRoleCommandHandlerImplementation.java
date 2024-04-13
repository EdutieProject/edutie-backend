package com.edutie.backend.application.profiles.educator.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.profiles.educator.AssignEducatorRoleCommandHandler;
import com.edutie.backend.application.profiles.educator.commands.AssignEducatorRoleCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;
@Component
@RequiredArgsConstructor
public class AssignEducatorRoleCommandHandlerImplementation extends HandlerBase implements AssignEducatorRoleCommandHandler {
    private final EducatorPersistence educatorPersistence;
    @Override
    public WrapperResult<Educator> handle(AssignEducatorRoleCommand command) {
        Educator educator = Educator.create(command.userId(), command.adminId());
        educatorPersistence.save(educator);
        return WrapperResult.successWrapper(educator);
    }
}

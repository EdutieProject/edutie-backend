package com.edutie.backend.application.profiles.educator.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.profiles.educator.ModifyEducatorCommandHandler;
import com.edutie.backend.application.profiles.educator.commands.ModifyEducatorCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class ModifyEducatorCommandHandlerImplementation extends HandlerBase implements ModifyEducatorCommandHandler {
    private final EducatorPersistence educatorPersistence;
    @Override
    public Result handle(ModifyEducatorCommand command) {
        WrapperResult<Educator> educatorWrapperResult = educatorPersistence.getById(command.educatorId());
        if (educatorWrapperResult.isFailure())
            return educatorWrapperResult;
        Educator educator = educatorWrapperResult.getValue();
        if (command.educatorType() != null)
            educator.setType(command.educatorType());
        return Result.success();
    }
}

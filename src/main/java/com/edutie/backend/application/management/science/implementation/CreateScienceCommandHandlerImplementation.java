package com.edutie.backend.application.management.science.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.science.CreateScienceCommandHandler;
import com.edutie.backend.application.management.science.commands.CreateScienceCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.domainservice.common.logging.ExternalFailureLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateScienceCommandHandlerImplementation extends HandlerBase implements CreateScienceCommandHandler {
    private final SciencePersistence sciencePersistence;
    private final EducatorPersistence educatorPersistence;

    @Override
    public WrapperResult<Science> handle(CreateScienceCommand command) {
        LOGGER.info("Creating science");
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        WrapperResult<Science> scienceCreationResult = Science.create(educator);
        if (scienceCreationResult.isFailure())
            return scienceCreationResult;
        Science science = scienceCreationResult.getValue();
        science.setName(command.scienceName());
        science.setDescription(command.scienceDescription() != null ? command.scienceDescription() : "");
        Result result = sciencePersistence.save(science);
        if (result.isFailure())
            return ExternalFailureLog.persistenceFailure(result, LOGGER).into(Science.class);
        LOGGER.info("Science created successfully");
        return WrapperResult.successWrapper(science);
    }
}

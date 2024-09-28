package com.edutie.backend.application.management.science.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.science.CreateScienceCommandHandler;
import com.edutie.backend.application.management.science.commands.CreateScienceCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateScienceCommandHandlerImplementation extends HandlerBase implements CreateScienceCommandHandler {
	private final SciencePersistence sciencePersistence;
	private final EducatorPersistence educatorPersistence;

	@Override
	public WrapperResult<Science> handle(CreateScienceCommand command) {
		log.info("Creating science");
		Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
		Science science = Science.create(educator).getValue();
		science.setName(command.scienceName());
		science.setDescription(command.scienceDescription() != null ? command.scienceDescription() : "");
		sciencePersistence.save(science).throwIfFailure();
		log.info("Science created successfully");
		return WrapperResult.successWrapper(science);
	}
}

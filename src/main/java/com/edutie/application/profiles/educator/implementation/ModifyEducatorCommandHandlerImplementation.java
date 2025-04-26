package com.edutie.application.profiles.educator.implementation;

import com.edutie.application.common.HandlerBase;
import com.edutie.application.profiles.educator.ModifyEducatorCommandHandler;
import com.edutie.application.profiles.educator.commands.ModifyEducatorCommand;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import validation.Result;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;

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

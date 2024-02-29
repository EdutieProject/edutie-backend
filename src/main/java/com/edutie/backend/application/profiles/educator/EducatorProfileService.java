package com.edutie.backend.application.profiles.educator;

import com.edutie.backend.application.profiles.educator.commands.ChangeEducatorTypeCommand;
import com.edutie.backend.domain.common.identities.UserId;
import validation.Result;

public interface EducatorProfileService {
    Result addEducatorProfile(UserId userId);
    Result changeEducatorType(ChangeEducatorTypeCommand command);
    Result degradeEducator(UserId userId);
}

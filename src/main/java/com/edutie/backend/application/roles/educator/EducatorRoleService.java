package com.edutie.backend.application.roles.educator;

import com.edutie.backend.domain.common.identities.UserId;
import validation.Result;

//TODO: expand
public interface EducatorRoleService {
    Result assign(UserId userId);
    Result degrade(UserId userId);
}

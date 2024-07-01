package com.edutie.backend.domain.administration.administrator.persistence;

import com.edutie.backend.domain.administration.administrator.identities.AdministratorId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.common.persistence.RolePersistence;

public interface AdministratorPersistence extends RolePersistence<Administrator, AdministratorId> {
    AdministratorId getAdminId(UserId userId);
}

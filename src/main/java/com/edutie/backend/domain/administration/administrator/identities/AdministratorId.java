package com.edutie.backend.domain.administration.administrator.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class AdministratorId extends UuidIdentifier {
    public AdministratorId() {
        super();
    }

    public AdministratorId(UUID uuid) {
        super(uuid);
    }
}

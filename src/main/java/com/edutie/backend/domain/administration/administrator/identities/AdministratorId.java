package com.edutie.backend.domain.administration.administrator.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class AdministratorId extends UuidIdentifier {
    public AdministratorId() {
        super();
    }

    @JsonCreator
    public AdministratorId(UUID uuid) {
        super(uuid);
    }
}

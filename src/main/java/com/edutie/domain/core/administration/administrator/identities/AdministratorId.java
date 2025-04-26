package com.edutie.domain.core.administration.administrator.identities;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
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

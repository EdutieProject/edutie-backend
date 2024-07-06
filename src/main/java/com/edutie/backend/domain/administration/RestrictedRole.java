package com.edutie.backend.domain.administration;

import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.identities.AdministratorId;
import com.edutie.backend.domain.common.base.identity.Identifier;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public abstract class RestrictedRole<TId extends Identifier<?>> extends Role<TId> {
    @ManyToOne(targetEntity = Administrator.class, fetch = FetchType.EAGER)
    protected Administrator assignedBy;
}

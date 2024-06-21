package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.common;

import com.edutie.backend.domain.administration.Role;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.common.base.identity.Identifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository<T extends Role<TId>, TId extends Identifier<?>> extends JpaRepository<T, TId> {
    Optional<T> findByOwnerUserId(UserId userId);
}

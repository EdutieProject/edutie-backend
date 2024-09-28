package com.edutie.backend.infrastucture.persistence.jpa.repositories.common;

import com.edutie.backend.domain.administration.Role;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.common.base.identity.Identifier;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;

import java.util.Optional;

@NoRepositoryBean
public interface RoleRepository<T extends Role<TId>, TId extends Identifier<?>> extends JpaRepository<T, TId> {
	Optional<T> findByOwnerUserId(UserId userId);
}

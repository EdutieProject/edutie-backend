package com.edutie.domain.core.administration.administrator;

import com.edutie.domain.core.administration.Role;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.identities.AdministratorId;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class Administrator extends Role<AdministratorId> {
    public static Administrator create(UserId userId) {
        Administrator administrator = new Administrator();
        administrator.setId(new AdministratorId());
        administrator.setOwnerUserId(userId);
        return administrator;
    }
}

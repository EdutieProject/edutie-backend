package com.edutie.backend.domain.administration.administrator;

import com.edutie.backend.domain.administration.Role;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.identities.AdministratorId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Administrator extends Role<AdministratorId> {
    public static Administrator create(UserId userId) {
        Administrator administrator = new Administrator();
        administrator.setId(new AdministratorId());
        administrator.setOwnerUserId(userId);
        return administrator;
    }
}

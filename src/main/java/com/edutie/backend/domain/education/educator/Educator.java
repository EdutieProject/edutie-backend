package com.edutie.backend.domain.education.educator;

import com.edutie.backend.domain.administration.RestrictedRole;
import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.enums.EducatorType;
import com.edutie.backend.domain.education.educator.identities.EducatorId;

import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//TODO: rework privileges & educator types according to docs.
public class Educator extends RestrictedRole<EducatorId> {
    @Convert(converter = EducatorType.Converter.class)
    EducatorType type = EducatorType.CREATOR;
    public static Educator create(UserId userId, AdminId adminId) {
        Educator educator = new Educator();
        educator.setId(new EducatorId());
        educator.setOwnerUserId(userId);
        educator.setAssignedBy(adminId);
        return educator;
    }
}

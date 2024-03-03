package com.edutie.backend.domain.education.educator;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.identities.AdminId;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.enums.EducatorType;
import com.edutie.backend.domain.education.educator.identities.EducatorId;

import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Setter;

@Entity
@Setter
public class Educator extends AuditableEntityBase<EducatorId> {
    @Convert(converter = EducatorType.Converter.class)
    EducatorType type = EducatorType.CREATOR;
    @Embedded
    @Setter(AccessLevel.PRIVATE)
    AdminId adminId;
    public static Educator create(UserId userId, AdminId adminId) {
        Educator educator = new Educator();
        educator.setId(new EducatorId());
        educator.setCreatedBy(userId);
        educator.setAdminId(adminId);
        return educator;
    }
}

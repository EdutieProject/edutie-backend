package com.edutie.backend.domain.education.educator;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import jakarta.persistence.Entity;

@Entity
public class Educator extends AuditableEntityBase<EducatorId> {
    public static Educator create(UserId userId) {
        Educator educator = new Educator();
        educator.setId(new EducatorId());
        educator.setCreatedBy(userId);
        return educator;
    }
}

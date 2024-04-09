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
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//TODO: rework privileges & educator types according to docs.
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

    /**
     * Indicates whether the educator has creator privileges. Creator privileges give access
     * to course creation and management.
     * @return true/false
     */
    public boolean hasCreatorPrivileges() {
        return this.type.equals(EducatorType.CREATOR) || this.type.equals(EducatorType.PEDAGOGUE);
    }

    /**
     * Indicates whether the educator has psychologist privileges Psychologist privileges mean
     * access to the learning personalization framework.
     * @return
     */
    public boolean hasPsychologistPrivileges() {
        return this.type.equals(EducatorType.PSYCHOLOGIST) || this.type.equals(EducatorType.PEDAGOGUE);
    }
}

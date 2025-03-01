package com.edutie.domain.core.education.educator;

import com.edutie.domain.core.administration.RestrictedRole;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.common.base.EducatorCreatedAuditableEntity;
import com.edutie.domain.core.education.EducationError;
import com.edutie.domain.core.education.educator.enums.EducatorType;
import com.edutie.domain.core.education.educator.identities.EducatorId;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import validation.Result;

/**
 * Educator profile
 */
@Entity
@Getter
@Setter
public class Educator extends RestrictedRole<EducatorId> {
    @Convert(converter = EducatorType.Converter.class)
    EducatorType type = EducatorType.CONTRIBUTOR;

    public static Educator create(UserId userId, Administrator administrator) {
        Educator educator = new Educator();
        educator.setId(new EducatorId());
        educator.setOwnerUserId(userId);
        educator.setAssignedBy(administrator);
        if (userId == administrator.getOwnerUserId())
            educator.setType(EducatorType.ADMINISTRATOR);
        return educator;
    }

    /**
     * Returns whether this educator has permissions as high as provided educator type
     * or higher.
     *
     * @param educatorType educator type to compare
     */
    public boolean hasPermissionsOf(EducatorType educatorType) {
        return this.type.ordinal() >= educatorType.ordinal();
    }

    public Result isAuthorOf(EducatorCreatedAuditableEntity<?> entity) {
        return entity.getAuthorEducator().equals(this) ? Result.success() : Result.failure(EducationError.unprivilegedEducator(this));
    }
}

package com.edutie.domain.core.education.educator;

import com.edutie.domain.core.administration.RestrictedRole;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.common.base.EducatorCreated;
import com.edutie.domain.core.education.EducationError;
import com.edutie.domain.core.education.educator.enums.EducatorType;
import com.edutie.domain.core.education.educator.identities.EducatorId;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import validation.Result;

/**
 * Educator profile
 */
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
public class Educator extends RestrictedRole<EducatorId> {
    @Convert(converter = EducatorType.Converter.class)
    private EducatorType type = EducatorType.COMMUNITY;

    public static Educator create(UserId userId) {
        Educator educator = new Educator();
        educator.setId(new EducatorId());
        educator.setOwnerUserId(userId);
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

    public Result isAuthorOf(EducatorCreated entity) {
        return entity.getAuthorEducator().equals(this) ? Result.success() : Result.failure(EducationError.unprivilegedEducator(this));
    }
}

package com.edutie.backend.domain.education.educator;

import com.edutie.backend.domain.administration.RestrictedRole;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.common.base.EducatorCreatedAuditableEntity;
import com.edutie.backend.domain.education.educator.enums.EducatorType;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import jakarta.persistence.*;
import lombok.*;

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

	public boolean isAuthorOf(EducatorCreatedAuditableEntity<?> entity) {
		return entity.getAuthorEducator().equals(this);
	}
}

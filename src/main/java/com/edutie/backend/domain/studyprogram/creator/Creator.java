package com.edutie.backend.domain.studyprogram.creator;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.creator.identities.CreatorId;
import jakarta.persistence.Entity;

@Entity
public class Creator extends AuditableEntityBase<CreatorId> {
    public static Creator create(UserId userId) {
        Creator creator = new Creator();
        creator.setId(new CreatorId());
        creator.setCreatedBy(userId);
        return creator;
    }
}

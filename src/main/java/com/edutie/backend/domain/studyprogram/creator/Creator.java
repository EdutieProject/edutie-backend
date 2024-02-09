package com.edutie.backend.domain.studyprogram.creator;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.studyprogram.creator.identities.CreatorId;
import jakarta.persistence.Entity;

@Entity
public class Creator extends AuditableEntityBase<CreatorId> {
}

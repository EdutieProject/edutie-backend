package com.edutie.backend.domain.psychology.psychologist;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.psychology.psychologist.identities.PsychologistId;
import jakarta.persistence.Entity;

@Entity
public class Psychologist extends AuditableEntityBase<PsychologistId> {
}

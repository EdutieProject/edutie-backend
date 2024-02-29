package com.edutie.backend.domain.education.psychologist;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.psychologist.identities.PsychologistId;
import jakarta.persistence.Entity;

@Entity
public class Psychologist extends AuditableEntityBase<PsychologistId> {
    public static Psychologist create(UserId userId) {
        Psychologist psychologist = new Psychologist();
        psychologist.setId(new PsychologistId());
        psychologist.setCreatedBy(userId);
        return psychologist;
    }
}

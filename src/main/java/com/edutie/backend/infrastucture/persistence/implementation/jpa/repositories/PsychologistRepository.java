
package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.psychology.psychologist.Psychologist;
import com.edutie.backend.domain.psychology.psychologist.identities.PsychologistId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsychologistRepository extends JpaRepository< Psychologist, PsychologistId> {
}

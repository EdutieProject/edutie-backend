package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.psychology.skill.Skill;
import com.edutie.backend.domain.psychology.skill.identities.SkillId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, SkillId> {
}

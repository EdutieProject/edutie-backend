package com.edutie.backend.infrastucture.persistence.contexts.psychology;

import com.edutie.backend.domain.education.psychologist.identities.PsychologistId;
import com.edutie.backend.domain.education.skill.Skill;
import com.edutie.backend.domain.education.skill.identities.SkillId;
import com.edutie.backend.infrastucture.persistence.contexts.base.PersistenceContext;

import java.util.List;

public interface SkillPersistenceContext extends PersistenceContext<Skill, SkillId> {
    /**
     * Retrieve all skills associated with given psychologist
     * @param psychologistId psychologist id
     * @return SKill list
     */
    List<Skill> getAllOfPsychologistId(PsychologistId psychologistId);
}

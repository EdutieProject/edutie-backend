package com.edutie.edutiebackend.application.services.ports.agreggates;

import com.edutie.edutiebackend.application.services.ports.crud.GenericCrudService;
import com.edutie.edutiebackend.domain.core.skill.Skill;

import java.util.UUID;

public interface SkillService extends GenericCrudService<Skill, UUID> {
}

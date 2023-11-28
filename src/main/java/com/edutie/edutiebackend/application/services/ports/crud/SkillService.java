package com.edutie.edutiebackend.application.services.ports.crud;

import com.edutie.edutiebackend.application.services.ports.crud.base.BaseService;
import com.edutie.edutiebackend.domain.core.skill.Skill;

import java.util.UUID;

public interface SkillService extends BaseService<Skill, UUID> {
}

package com.edutie.edutiebackend.application.services.ports.management;

import com.edutie.edutiebackend.application.services.ports.common.GenericCrudService;
import com.edutie.edutiebackend.domain.core.skill.Skill;
import com.edutie.edutiebackend.domain.core.skill.identities.SkillId;

public interface SkillService extends GenericCrudService<Skill, SkillId> {
}

package com.edutie.backend.domain.core;

import com.edutie.backend.domain.core.common.studenttraits.Ability;
import com.edutie.backend.domain.core.common.studenttraits.Intelligence;
import com.edutie.backend.domain.core.skill.Skill;
import com.edutie.backend.domain.core.skill.entities.AbilityIndicator;
import com.edutie.backend.domain.core.skill.entities.IntelligenceIndicator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SkillTests {
    @Test
    public void skillInitializationTest() {
        Skill skill = new Skill();
        skill.setName("skill");
        assertEquals(
                skill.getName(),
                "skill"
        );
    }

    @Test
    public void skillIndicatorReassignmentTest() {
        Skill skill = new Skill();
        skill.assignTraitIndicator(Intelligence.VISUAL, 1, IntelligenceIndicator.class);

        Assertions.assertEquals(1, skill.getTraitIndicator(Intelligence.VISUAL, IntelligenceIndicator.class).get().getValue());

        skill.assignTraitIndicator(Intelligence.VISUAL, 2, IntelligenceIndicator.class);

        Assertions.assertEquals(2, skill.getTraitIndicator(Intelligence.VISUAL, IntelligenceIndicator.class).get().getValue());
    }

    @Test
    public void indicatorAssignmentTest() {
        Skill skill = new Skill();
        skill.assignTraitIndicator(Ability.ADAPTABILITY, 1, AbilityIndicator.class);
        skill.assignTraitIndicator(Intelligence.LOGICAL, 2, IntelligenceIndicator.class);

        assertFalse(skill.getIndicators().isEmpty());
        assertEquals(skill.getIndicators().size(), 2);
    }


    @Test
    public void multiplierRemovalTest() {
        Skill skill = new Skill();
        skill.assignTraitIndicator(Ability.GOAL_SETTING, 1, AbilityIndicator.class);
        assertFalse(skill.getIndicators().isEmpty());

        skill.removeIndicator(Ability.GOAL_SETTING, AbilityIndicator.class);
        assertTrue(skill.getIndicators().isEmpty());
    }

}

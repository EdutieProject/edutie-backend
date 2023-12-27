package com.edutie.edutiebackend.domain.core;

import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.skill.Skill;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SkillTests {
    @Test
    public void skillInitializationTest()
    {
        Skill skill = new Skill("skill");
        assertEquals(
                skill.getName(),
                "skill"
        );
    }

    @Test
    public void multiplierAssignmentTest()
    {
        Skill skill = new Skill();
        skill.assignIntelligenceMultiplier(Intelligence.LOGICAL, 2.0);
        skill.assignAbilityMultiplier(Ability.ADAPTABILITY, 2.5);
        var logicalMultiplier = skill.getMultiplierValue(Intelligence.LOGICAL);
        var adaptabilityMultiplier = skill.getMultiplierValue(Ability.ADAPTABILITY);
        assertEquals(
                logicalMultiplier,
                2.0
        );
        assertEquals(
                adaptabilityMultiplier,
                2.5
        );
    }

    @Test
    public void multiplierAssignmentGenericTest()
    {
        Skill skill = new Skill();
        assertTrue(skill.assignMultiplier(Intelligence.VISUAL, 2.0).isSuccess());
    }

    @Test
    public void multiplierRemovalTest()
    {
        Skill skill = new Skill();
        skill.assignAbilityMultiplier(Ability.CREATIVITY, 2.0);
        skill.removeAbilityMultiplier(Ability.CREATIVITY);
        Double value = skill.getMultiplierValue(Ability.CREATIVITY);
        assertNull(value);
    }

    @Test
    public void multiplierRemovalGenericTest()
    {
        Skill skill = new Skill();
        Ability ability = Ability.CREATIVITY;
        skill.assignMultiplier(ability, 1.0);
        assert skill.getMultiplierValue(ability) != null;
        skill.removeMultiplier(ability);
        assertNull(skill.getMultiplierValue(ability));
    }

    @Test
    public void unsupportedTraitTest()
    {
        Skill skill = new Skill();
        enum Hello { WORLD, UNIVERSE }
        assertFalse(skill.assignMultiplier(Hello.WORLD, 1.0).isSuccess());
    }

    @Test
    public void outOfBoundsMultiplierValueTest()
    {
        Skill skill = new Skill();
        assertFalse(skill.assignMultiplier(Intelligence.LOGICAL, 55.99).isSuccess());
    }

}

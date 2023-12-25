package com.edutie.edutiebackend.domain.core;

import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.skill.Skill;
import com.edutie.edutiebackend.domain.core.skill.exceptions.InvalidTraitMultiplierValueException;
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
        skill.assignAbilityMultiplier(Ability.ADAPTABILITY, 3.0);
        var logicalMultiplier = skill.getMultiplierValue(Intelligence.LOGICAL);
        var adaptabilityMultiplier = skill.getMultiplierValue(Ability.ADAPTABILITY);
        assertEquals(
                logicalMultiplier,
                2.0
        );
        assertEquals(
                adaptabilityMultiplier,
                3.0
        );
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
    public void multiplierValueValidationThrowsTest()
    {
        Skill skill = new Skill();
        assertThrows(
                InvalidTraitMultiplierValueException.class,
                ()->skill.assignAbilityMultiplier(Ability.CREATIVITY, 99.99)
        );
        assertThrows(
                InvalidTraitMultiplierValueException.class,
                ()->skill.assignMultiplier(Ability.CREATIVITY, 80.0)
        );
        assertThrows(
                InvalidTraitMultiplierValueException.class,
                ()->skill.assignMultiplier(Intelligence.LOGICAL, 127.0)
        );
    }

}

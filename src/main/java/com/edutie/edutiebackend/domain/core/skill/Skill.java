package com.edutie.edutiebackend.domain.core.skill;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.skill.exceptions.InvalidTraitMultiplierValueException;
import com.edutie.edutiebackend.domain.core.skill.identities.SkillId;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.skill.validation.TraitMultiplierValueValidator;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

/**
 * Skill is a high-level indicator of Learning Resource's knowledge requirements.
 * It contains necessary mappings for  abilities and intelligences utilized in
 * the task with given multiplier being an indicator of the importance of
 * given trait.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Skill extends AuditableEntityBase<SkillId> {
    private String name;
    HashMap<Ability, Double> abilityMultipliers = new HashMap<>();
    HashMap<Intelligence, Double> intelligenceMultipliers = new HashMap<>();

    /**
     * Assigns a multiplier to given ability, where multiplier indicates the importance of given trait. Throws
     * exception if value is invalid.
     * @param ability ability to assign to.
     * @param multiplier multiplier value
     * @throws InvalidTraitMultiplierValueException exception thrown when value is improper
     * @see TraitMultiplierValueValidator Rules of multiplier validation
     */
    public void assignAbilityMultiplier(Ability ability, double multiplier) throws InvalidTraitMultiplierValueException {
        if(TraitMultiplierValueValidator.isValid(multiplier))
            abilityMultipliers.put(ability, multiplier);
    }

    /**
     * Removes given ability from the multiplier mapping
     * @param ability ability to remove
     */
    public void removeAbilityMultiplier(Ability ability)
    {
        abilityMultipliers.remove(ability);
    }


    /**
     * Assigns a multiplier to given intelligence, where multiplier indicates the importance of given trait. Throws
     * exception if value is invalid.
     * @param intelligence intelligence to assign to
     * @param multiplier multiplier value
     * @throws InvalidTraitMultiplierValueException exception thrown when multiplier value is invalid
     * @see TraitMultiplierValueValidator Rules of multiplier validation
     */
    public void assignIntelligenceMultiplier(Intelligence intelligence, double multiplier) throws InvalidTraitMultiplierValueException {
        if(TraitMultiplierValueValidator.isValid(multiplier))
            intelligenceMultipliers.put(intelligence, multiplier);
    }

    /**
     * Removes intelligence multiplier from the mapping
     * @param intelligence intelligence to remove
     */
    public void removeIntelligenceMultiplier(Intelligence intelligence)
    {
        intelligenceMultipliers.remove(intelligence);
    }
}

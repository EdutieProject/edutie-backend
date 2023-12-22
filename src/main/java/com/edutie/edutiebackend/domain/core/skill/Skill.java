package com.edutie.edutiebackend.domain.core.skill;

import java.util.HashMap;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.skill.exceptions.InvalidTraitMultiplierValueException;
import com.edutie.edutiebackend.domain.core.skill.identities.SkillId;
import com.edutie.edutiebackend.domain.core.skill.validation.TraitMultiplierValueValidator;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Skill is a high-level indicator of Learning Resource's knowledge requirements.
 * It contains necessary mappings for  abilities and intelligences utilized in
 * the task with given multiplier being an indicator of the importance of
 * given trait.
 */
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Skill extends AuditableEntityBase<SkillId> {
    private String name;
    // many-to-many with additional column - multiplierValue
    private final HashMap<Ability, Double> abilityMultipliers = new HashMap<>();
    // many-to-many with additional column - multiplierValue
    private final HashMap<Intelligence, Double> intelligenceMultipliers = new HashMap<>();

    public Skill(String name)
    {
        this.name = name;
    }

    /**
     * Generic function returning the multiplier value for given trait.
     * Returns null if there is no mapping associated.
     * @param trait trait
     * @return value of multiplier or null.
     * @param <T> type of trait
     */
    public <T> Double getMultiplierValue(T trait)
    {
        if(trait instanceof Ability) return abilityMultipliers.get(trait);
        if(trait instanceof Intelligence) return intelligenceMultipliers.get(trait);
        return null;
    }

    /**
     * Generic function assigning the multiplier value for given trait
     * @param trait trait
     * @param value value of multiplier
     * @param <T> trait ty[e
     * @throws InvalidTraitMultiplierValueException exception thrown when rules are omitted
     * @see TraitMultiplierValueValidator
     */
    public <T> void assignMultiplier(T trait, double value)
    {
        if(!TraitMultiplierValueValidator.isValid(value)) throw new InvalidTraitMultiplierValueException();
        if(trait instanceof Ability) abilityMultipliers.put((Ability) trait, value);
        if(trait instanceof Intelligence) intelligenceMultipliers.put((Intelligence) trait, value);
    }

    /**
     * Generic function removing multiplier from the skill
     * @param trait trait of the multiplier
     * @param <T> type of trait
     */
    public <T> void removeMultiplier(T trait)
    {
        if(trait instanceof Ability) abilityMultipliers.remove(trait);
        if(trait instanceof Intelligence) intelligenceMultipliers.remove(trait);
    }


    /**
     * Retrieves multiplier value of given trait.
     * @param ability ability trait
     * @return value if exists, null otherwise
     */
    public Double getMultiplierValue(Ability ability)
    {
        return abilityMultipliers.get(ability);
    }

    /**
     * Retrieves multiplier value of given trait
     * @param intelligence intelligence trait
     * @return value if exists, null otherwise
     */
    public Double getMultiplierValue(Intelligence intelligence)
    {
        return intelligenceMultipliers.get(intelligence);
    }

    /**
     * Assigns a multiplier to given ability, where multiplier indicates the importance of given trait. Throws
     * exception if value is invalid.
     * @param ability ability to assign to.
     * @param multiplier multiplier value
     * @throws InvalidTraitMultiplierValueException runtime exception thrown when value is improper
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
    public void removeAbilityMultiplier(Ability ability){
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
    public void removeIntelligenceMultiplier(Intelligence intelligence){
        intelligenceMultipliers.remove(intelligence);
    }
}

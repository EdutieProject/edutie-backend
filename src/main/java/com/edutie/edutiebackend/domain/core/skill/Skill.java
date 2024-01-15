package com.edutie.edutiebackend.domain.core.skill;

import java.util.Arrays;
import java.util.HashMap;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.skill.errors.SkillErrors;
import com.edutie.edutiebackend.domain.core.skill.identities.SkillId;
import com.edutie.edutiebackend.domain.core.skill.rules.TraitMultiplierValueBoundsRule;

import com.edutie.edutiebackend.domain.rule.Result;
import com.edutie.edutiebackend.domain.rule.Rule;
import com.edutie.edutiebackend.domain.rule.RuleError;
import com.edutie.edutiebackend.domain.rule.Validation;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Skill is a high-level indicator of Learning Resource's knowledge requirements.
 * It contains necessary mappings for  abilities and intelligences utilized in
 * the task with given multiplier being an indicator of the importance of
 * given trait.
 */
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Skill extends AuditableEntityBase<SkillId> {
    @Setter
    private String name;
    // many-to-many with additional column - multiplierValue
    @Transient
    private final HashMap<Ability, Double> abilityMultipliers = new HashMap<>();
    // many-to-many with additional column - multiplierValue
    @Transient
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
     * @param <T> trait type
     */
    public <T> Result assignMultiplier(T trait, double value)
    {
        var validationResult = Rule.validate(TraitMultiplierValueBoundsRule.class, value);
        if (validationResult.isFailure()) return validationResult;

        switch (trait) {
            case Ability a -> abilityMultipliers.put(a, value);
            case Intelligence i -> intelligenceMultipliers.put(i, value);
            default -> { return Result.failure(SkillErrors.unhandledTraitError()); }
        }
        return Result.success();
    }

    /**
     * Generic function removing multiplier from the skill
     * @param trait trait of the multiplier
     * @param <T> type of trait
     */
    public <T> void removeMultiplier(T trait)
    {
        switch (trait) {
            case Ability a -> abilityMultipliers.remove(a);
            case Intelligence i -> intelligenceMultipliers.remove(i);
            default -> {}
        }
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
     */
    public Result assignAbilityMultiplier(Ability ability, double multiplier){
        var validationResult = Rule.validate(TraitMultiplierValueBoundsRule.class, multiplier);
        if(validationResult.isSuccess())
            abilityMultipliers.put(ability, multiplier);
        return validationResult;
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
     */
    public Result assignIntelligenceMultiplier(Intelligence intelligence, double multiplier) {
        var validationResult = Rule.validate(TraitMultiplierValueBoundsRule.class, multiplier);
        if(validationResult.isSuccess())
            intelligenceMultipliers.put(intelligence, multiplier);
        return validationResult;
    }

    /**
     * Removes intelligence multiplier from the mapping
     * @param intelligence intelligence to remove
     */
    public void removeIntelligenceMultiplier(Intelligence intelligence){
        intelligenceMultipliers.remove(intelligence);
    }
}

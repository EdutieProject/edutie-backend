package com.edutie.edutiebackend.domain.core.skill;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.edutie.edutiebackend.domain.core.common.Utilities;
import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.skill.entities.AbilityIndicator;
import com.edutie.edutiebackend.domain.core.skill.entities.IntelligenceIndicator;
import com.edutie.edutiebackend.domain.core.skill.entities.base.TraitIndicator;
import com.edutie.edutiebackend.domain.core.skill.identities.IndicatorId;
import com.edutie.edutiebackend.domain.core.skill.identities.SkillId;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.*;

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
    @Setter
    private String name;

    @OneToMany(targetEntity = AbilityIndicator.class, fetch = FetchType.EAGER)
    private final Set<AbilityIndicator> abilityIndicators = new HashSet<>();

    @OneToMany(targetEntity = IntelligenceIndicator.class, fetch = FetchType.EAGER)
    private final Set<IntelligenceIndicator> intelligenceIndicators = new HashSet<>();

    /**
     * Recommended constructor.
     * @param name desired skill name
     */
    public Skill(String name)
    {
        this.name = name;
    }

    //TODO: signature improvement: return Set<? extends TraitIndicator<T>>
    public <T extends Enum<T>> Set<? extends TraitIndicator<?>> getIndicators() {
        Set<TraitIndicator<?>> traitIndicators = new HashSet<>();
        traitIndicators.addAll(intelligenceIndicators);
        traitIndicators.addAll(abilityIndicators);
        return traitIndicators;
    }

    /**
     * Generic function returning the multiplier value for given trait.
     * Returns null if there is no mapping associated.
     * @param trait trait
     * @return value of multiplier or null.
     * @param <T> type of trait
     */
    public <T extends Enum<T>, U extends TraitIndicator<T>> Optional<? extends TraitIndicator<T>> getTraitIndicator(T trait, Class<U> indicatorClass)
    {
        var indicators = Utilities.findSetOf(indicatorClass, this).orElseThrow();
        var searchedIndicator = indicators.stream().filter(o->o.getTrait() == trait).findFirst();
        return searchedIndicator.isPresent() ? searchedIndicator : Optional.empty();
    }

    /**
     * Generic function assigning the multiplier value for given trait
     * @param trait trait
     * @param value value of multiplier
     * @param <T> trait type
     */
    @SneakyThrows
    public <T extends Enum<T>, U extends TraitIndicator<T>> void assignTraitIndicator(T trait, int value, Class<U> indicatorClass)
    {
        var indicators = Utilities.findSetOf(indicatorClass, this).orElseThrow(RuntimeException::new);
        var foundIndicator = indicators.stream().filter(o->o.getTrait() == trait).findFirst();
        if (foundIndicator.isPresent())
            foundIndicator.get().setValue(value);
        else {
            var newTraitIndicator = indicatorClass.getConstructor().newInstance();
            newTraitIndicator.setId(new IndicatorId());
            newTraitIndicator.setTrait(trait);
            newTraitIndicator.setValue(value);
            indicators.add(newTraitIndicator);
        }
    }

    /**
     * Generic function removing multiplier from the skill
     * @param trait trait of the multiplier
     * @param <T> type of trait
     */
    public <T extends Enum<T>, U extends TraitIndicator<T>> void removeIndicator(T trait, Class<U> indicatorClass)
    {
        var indicatorSet = Utilities.findSetOf(indicatorClass, this).orElseThrow();
        var searchedIndicator = indicatorSet.stream().filter(o -> o.getTrait() == trait).findFirst();
        searchedIndicator.ifPresent(indicatorSet::remove);
    }

}

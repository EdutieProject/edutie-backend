package com.edutie.backend.domain.education.skill;

import com.edutie.backend.domain.common.Utilities;
import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.skill.entities.AbilityIndicator;
import com.edutie.backend.domain.education.skill.entities.IntelligenceIndicator;
import com.edutie.backend.domain.education.skill.entities.base.TraitIndicator;
import com.edutie.backend.domain.education.skill.identities.IndicatorId;
import com.edutie.backend.domain.education.skill.identities.SkillId;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Skill is a high-level indicator of Learning Resource's knowledge requirements.
 * It contains necessary mappings for  abilities and intelligences utilized in
 * the task with given multiplier being an indicator of the importance of
 * given trait.
 * @deprecated
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Skill extends AuditableEntityBase<SkillId> {
    private String name;

    @ManyToOne(targetEntity = Educator.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "educator_id")
    @Setter(AccessLevel.PRIVATE)
    private Educator educator;

    @OneToMany(targetEntity = AbilityIndicator.class, fetch = FetchType.EAGER)
    private final Set<AbilityIndicator> abilityIndicators = new HashSet<>();

    @OneToMany(targetEntity = IntelligenceIndicator.class, fetch = FetchType.EAGER)
    private final Set<IntelligenceIndicator> intelligenceIndicators = new HashSet<>();

    /**
     * Recommended constructor associating skill with a psychologist.
     * @param educator educator profile reference
     * @return Skill
     */
    public static Skill create(Educator educator) {
        Skill skill = new Skill();
        skill.setCreatedBy(educator.getCreatedBy());
        skill.setId(new SkillId());
        skill.setEducator(educator);
        return skill;
    }

    public Set<? extends TraitIndicator<?>> getIndicators() {
        Set<TraitIndicator<?>> traitIndicators = new HashSet<>();
        traitIndicators.addAll(intelligenceIndicators);
        traitIndicators.addAll(abilityIndicators);
        return traitIndicators;
    }

    /**
     * Generic function returning the multiplier value for given trait.
     * Returns null if there is no mapping associated.
     *
     * @param trait trait
     * @param <T>   type of trait
     * @return value of multiplier or null.
     */
    public <T extends Enum<T>, U extends TraitIndicator<T>> Optional<? extends TraitIndicator<T>> getTraitIndicator(T trait, Class<U> indicatorClass) {
        var indicators = Utilities.findSetOf(indicatorClass, this).orElseThrow();
        var searchedIndicator = indicators.stream().filter(o -> o.getTrait() == trait).findFirst();
        return searchedIndicator.isPresent() ?
                searchedIndicator :
                Optional.empty();
    }

    /**
     * Generic function assigning the multiplier value for given trait
     *
     * @param trait trait
     * @param value value of multiplier
     * @param <T>   trait type
     */
    @SneakyThrows
    public <T extends Enum<T>, U extends TraitIndicator<T>> void assignTraitIndicator(T trait, int value, Class<U> indicatorClass) {
        var indicators = Utilities.findSetOf(indicatorClass, this).orElseThrow(RuntimeException::new);
        var foundIndicator = indicators.stream().filter(o -> o.getTrait() == trait).findFirst();
        if (foundIndicator.isPresent()) {
            foundIndicator.get().setValue(value);
        } else {
            var newTraitIndicator = indicatorClass.getConstructor().newInstance();
            newTraitIndicator.setId(new IndicatorId());
            newTraitIndicator.setTrait(trait);
            newTraitIndicator.setValue(value);
            indicators.add(newTraitIndicator);
        }
    }

    /**
     * Generic function removing multiplier from the skill
     *
     * @param trait trait of the multiplier
     * @param <T>   type of trait
     */
    public <T extends Enum<T>, U extends TraitIndicator<T>> void removeIndicator(T trait, Class<U> indicatorClass) {
        var indicatorSet = Utilities.findSetOf(indicatorClass, this).orElseThrow();
        var searchedIndicator = indicatorSet.stream().filter(o -> o.getTrait() == trait).findFirst();
        searchedIndicator.ifPresent(indicatorSet::remove);
    }

}

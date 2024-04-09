package com.edutie.backend.domain.personalization.learningresource;

import com.edutie.backend.domain.common.Utilities;
import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.optimizationstrategies.AbilityOptimizationStrategy;
import com.edutie.backend.domain.personalization.optimizationstrategies.IntelligenceOptimizationStrategy;
import com.edutie.backend.domain.personalization.optimizationstrategies.base.OptimizationStrategy;
import com.edutie.backend.domain.studyprogram.lessonsegment.LessonSegment;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * A singular form of learning in the application.
 * This is the resource which is used by the learner to exercise
 * his knowledge. It may be exclusively generated for a given student.
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
//TODO: (DOMAIN) add hints
//TODO: rework according to docs.
public class LearningResource extends AuditableEntityBase<LearningResourceId> {
    private String overviewText;
    private String exerciseText;
    @ManyToMany
    private final Set<IntelligenceOptimizationStrategy> intelligenceOptimizationStrategies = new HashSet<>();
    @ManyToMany
    private final Set<AbilityOptimizationStrategy> abilityOptimizationStrategies = new HashSet<>();
    @ManyToOne(targetEntity = LessonSegment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_segment_id")
    @Setter(AccessLevel.PRIVATE)
    private LessonSegment lessonSegment;
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @Setter(AccessLevel.PRIVATE)
    private Student student;

    /**
     * Recommended constructor associating learning resource with a student (creation invoker) and a lesson segment.
     * @param student student profile reference
     * @param lessonSegment lesson segment reference
     * @return Learning Resource
     */
    public static LearningResource create(Student student, LessonSegment lessonSegment) {
        LearningResource learningResource = new LearningResource();
        learningResource.setId(new LearningResourceId());
        learningResource.setCreatedBy(student.getCreatedBy());
        learningResource.setStudent(student);
        learningResource.setLessonSegment(lessonSegment);
        return learningResource;
    }

    public Set<OptimizationStrategy<?>> getAllOptimizationStrategies() {
        Set<OptimizationStrategy<?>> optimizationStrategies = new HashSet<>();
        optimizationStrategies.addAll(abilityOptimizationStrategies);
        optimizationStrategies.addAll(intelligenceOptimizationStrategies);
        return optimizationStrategies;
    }

    public <T extends OptimizationStrategy<?>> void addOptimizationStrategy(T optimizationStrategy, Class<T> strategyClass) {
        var optimizationStrategySet = Utilities.findSetOf(strategyClass, this).orElseThrow();
        optimizationStrategySet.add(optimizationStrategy);
    }

    public <T extends OptimizationStrategy<?>> void removeOptimizationStrategy(T optimizationStrategy, Class<T> strategyClass) {
        var optimizationStrategySet = Utilities.findSetOf(strategyClass, this).orElseThrow();
        optimizationStrategySet.remove(optimizationStrategy);
    }

    public <T extends OptimizationStrategy<?>, U> void removeOptimizationStrategyById(U optimizationStrategyId, Class<T> strategyClass) {
        var optimizationStrategySet = Utilities.findSetOf(strategyClass, this).orElseThrow();
        optimizationStrategySet.stream().filter(o -> o.getId() == optimizationStrategyId).findFirst()
                .ifPresent(optimizationStrategySet::remove);
    }

}

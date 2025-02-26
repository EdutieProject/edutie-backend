package com.edutie.domain.service.personalization.learningrequirement.implementation;

import com.edutie.domain.core.education.learningrequirement.LearningRequirement;
import com.edutie.domain.core.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.service.personalization.learningrequirement.DynamicLearningRequirementSelectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DynamicLearningRequirementSelectionServiceImplementation implements DynamicLearningRequirementSelectionService {
    private final LearningRequirementPersistence learningRequirementPersistence;
    private final LearningResultPersistence learningResultPersistence;

    @Override
    public WrapperResult<Set<LearningRequirement>> selectRequirementsForStudent(Student student) {
        List<LearningRequirement> latestWeakPerformanceRequirements = student.getLatestAssessmentsByMaxGrade(learningResultPersistence, new Grade(3))
                .stream().map(o -> learningRequirementPersistence.getById(o.getLearningRequirementId()).getValue()).toList();
        // Now let us choose random L-Reqs. In the future, we will replace them with correlated ones.
        List<LearningRequirement> randomLearningRequirements = learningRequirementPersistence.getAny(2).getValue();

        if (latestWeakPerformanceRequirements.isEmpty())
            return WrapperResult.successWrapper(new HashSet<>(randomLearningRequirements));
        // Randomize - either return random req, random + one of the latest, or just one of the latest
        int randomNumber = (int) Math.ceil(Math.random() * 3);
        return WrapperResult.successWrapper(
                randomNumber == 1 ? Set.of(latestWeakPerformanceRequirements.getFirst()) :
                        randomNumber == 2 ? Set.of(randomLearningRequirements.getFirst()) :
                                Set.of(randomLearningRequirements.getFirst(), latestWeakPerformanceRequirements.getFirst())
        );
    }
}

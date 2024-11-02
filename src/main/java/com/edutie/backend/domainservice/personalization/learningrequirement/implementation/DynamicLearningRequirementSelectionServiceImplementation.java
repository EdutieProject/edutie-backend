package com.edutie.backend.domainservice.personalization.learningrequirement.implementation;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domainservice.personalization.learningrequirement.DynamicLearningRequirementSelectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DynamicLearningRequirementSelectionServiceImplementation implements DynamicLearningRequirementSelectionService {
    private final LearningRequirementPersistence learningRequirementPersistence;
    private final LearningResultPersistence learningResultPersistence;

    @Override
    public WrapperResult<Set<LearningRequirement>> selectRequirementsForStudent(Student student) {
        Set<LearningRequirement> learningRequirements = student.getLatestAssessmentsByMaxGrade(learningResultPersistence, new Grade(3))
                .stream().map(o -> learningRequirementPersistence.getById(o.getLearningRequirementId()).getValue()).collect(Collectors.toSet());

        return WrapperResult.successWrapper(!learningRequirements.isEmpty() ? learningRequirements : new HashSet<>(learningRequirementPersistence.getAny(2).getValue()));
    }
}

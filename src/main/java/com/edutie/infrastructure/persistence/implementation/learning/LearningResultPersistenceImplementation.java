package com.edutie.infrastructure.persistence.implementation.learning;

import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.LearningResultRepository;
import com.edutie.infrastructure.persistence.implementation.education.repositories.LearningSubjectRepository;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class LearningResultPersistenceImplementation implements LearningResultPersistence {
    private final LearningResultRepository learningResultRepository;
    private final StudentRepository studentRepository;
    private final LearningSubjectRepository learningSubjectRepository;

    //TODO
}

package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.studyprogram.exercisetype.ExerciseType;
import com.edutie.backend.domain.studyprogram.exercisetype.identities.ExerciseTypeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseTypeRepository extends JpaRepository<ExerciseType, ExerciseTypeId> {
}

package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.core.lesson.Lesson;
import com.edutie.backend.domain.core.lesson.identities.LessonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, LessonId> {
}

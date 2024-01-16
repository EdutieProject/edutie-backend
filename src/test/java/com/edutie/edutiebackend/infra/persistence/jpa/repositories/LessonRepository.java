package com.edutie.edutiebackend.infra.persistence.jpa.repositories;

import com.edutie.edutiebackend.domain.core.lesson.Lesson;
import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, LessonId> {
}

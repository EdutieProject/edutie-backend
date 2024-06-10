package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.studyprogram.course.entities.CourseTag;
import com.edutie.backend.domain.studyprogram.course.identities.CourseTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseTagRepository extends JpaRepository<CourseTag, CourseTagId> { }

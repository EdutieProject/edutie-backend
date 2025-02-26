package com.edutie.infrastructure.persistence.implementation.studyprogram.course;

import com.edutie.backend.domain.studyprogram.course.tag.CourseTag;
import com.edutie.backend.domain.studyprogram.course.tag.indentities.CourseTagId;
import com.edutie.backend.domain.studyprogram.course.tag.persistence.CourseTagPersistence;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import lombok.*;

@Component
@RequiredArgsConstructor
public class CourseTagPersistenceImplementation implements CourseTagPersistence {
	private final CourseTagRepository courseTagRepository;

	/**
	 * Override this to provide repository for default methods
	 *
	 * @return crud jpa repository
	 */
	@Override
	public JpaRepository<CourseTag, CourseTagId> getRepository() {
		return courseTagRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<CourseTag> entityClass() {
		return CourseTag.class;
	}
}

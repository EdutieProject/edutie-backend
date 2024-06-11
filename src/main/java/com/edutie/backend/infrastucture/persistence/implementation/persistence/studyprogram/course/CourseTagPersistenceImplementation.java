package com.edutie.backend.infrastucture.persistence.implementation.persistence.studyprogram.course;

import com.edutie.backend.domain.studyprogram.course.tag.CourseTag;
import com.edutie.backend.domain.studyprogram.course.tag.indentities.CourseTagId;
import com.edutie.backend.domain.studyprogram.course.tag.persistence.CourseTagPersistence;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.CourseTagRepository;
import com.edutie.backend.infrastucture.persistence.implementation.persistence.common.PersistenceError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CourseTagPersistenceImplementation implements CourseTagPersistence {
	private final CourseTagRepository courseTagRepository;

	/**
	 * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
	 * it might not be present based on the identifier presence in the database.
	 *
	 * @param courseTagId entity id
	 * @return Optional entity
	 */
	@Override
	public WrapperResult<CourseTag> getById(CourseTagId courseTagId) {
		try {
			Optional<CourseTag> optionalCourseTag = courseTagRepository.findById(courseTagId);
			return optionalCourseTag.map(Result::successWrapper).orElseGet(() -> Result.failureWrapper(PersistenceError.notFound(CourseTag.class)));
		} catch (Exception e) {
			return Result.failureWrapper(PersistenceError.exceptionEncountered(e));
		}
	}

	/**
	 * Persists the provided entity into the database. If it is already present,
	 * updates its state to the provided object's state. Returns result indicating whether
	 * the operation was successful or not
	 *
	 * @param entity
	 * @return Result object
	 */
	@Override
	public Result save(CourseTag entity) {
		try {
			courseTagRepository.save(entity);
			return Result.success();
		} catch (Exception e) {
			return Result.failureWrapper(PersistenceError.exceptionEncountered(e));
		}
	}

	/**
	 * Removes the entity of the provided id from the database. If the entity is not preset or could not be
	 * deleted, does nothing and returns failure result.
	 *
	 * @param courseTagId entity id
	 * @return Result object
	 */
	@Override
	public Result removeById(CourseTagId courseTagId) {
		try {
			courseTagRepository.deleteById(courseTagId);
			return Result.success();
		} catch (Exception e) {
			return Result.failure(PersistenceError.exceptionEncountered(e));
		}
	}
}

package com.edutie.backend.infrastucture.persistence.implementation.studyprogram;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.infrastucture.persistence.PersistenceError;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.*;
import validation.Result;
import validation.WrapperResult;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LessonPersistenceImplementation implements LessonPersistence {
	private final LessonRepository lessonRepository;
	private final SegmentRepository segmentRepository;
	private final CourseRepository courseRepository;
	private final EducatorRepository educatorRepository;


	/**
	 * Override this to provide repository for default methods
	 *
	 * @return crud jpa repository
	 */
	@Override
	public JpaRepository<Lesson, LessonId> getRepository() {
		return lessonRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<Lesson> entityClass() {
		return Lesson.class;
	}

	/**
	 * Persists the provided lesson into the database. If it is already present,
	 * updates its state to the provided object's state. Returns result indicating whether
	 * the operation was successful or not
	 *
	 * @param lesson lesson to be saved
	 * @return Result object
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Result save(Lesson lesson) {
		try {
			lessonRepository.saveAndFlush(lesson);
//            for (Lesson nextLesson : lesson.getNextElements()) {
//                nextLesson.setPreviousElement(lesson);
//                lessonRepository.save(nextLesson);
//            }/TODO:#98
			return Result.success();
		} catch (Exception exception) {
			System.out.println("Exception occurred in persistence!");
			return Result.failure(PersistenceError.exceptionEncountered(exception));
		}
	}

	/**
	 * Retrieve all lessons associated with given course
	 *
	 * @param courseId course id
	 * @return Lesson list
	 */
	@Override
	public WrapperResult<List<Lesson>> getAllOfCourseId(CourseId courseId) {
		try {
			Optional<Course> course = courseRepository.findById(courseId);
			if (course.isEmpty())
				return Result.failureWrapper(PersistenceError.notFound(Course.class));
			List<Lesson> lessons = lessonRepository.getLessonsByCourse(course.get());
			return WrapperResult.successWrapper(lessons);
		} catch (Exception exception) {
			return Result.failureWrapper(PersistenceError.exceptionEncountered(exception));
		}
	}

	/**
	 * Retrieve all lessons associated with given creator
	 *
	 * @param educatorId educator id
	 * @return Lesson list
	 */
	@Override
	public WrapperResult<List<Lesson>> getAllOfEducatorId(EducatorId educatorId) {
		try {
			Optional<Educator> educator = educatorRepository.findById(educatorId);
			if (educator.isEmpty())
				return Result.failureWrapper(PersistenceError.notFound(Course.class));
			List<Lesson> lessons = lessonRepository.getLessonsByAuthorEducator(educator.get());
			return WrapperResult.successWrapper(lessons);
		} catch (Exception exception) {
			return Result.failureWrapper(PersistenceError.exceptionEncountered(exception));
		}
	}

	/**
	 * Removes the lesson together with the underlying segments
	 *
	 * @param lesson lesson to be removed
	 * @return Result object
	 */
	@Override
	public Result deepRemove(Lesson lesson) {
		try {
			segmentRepository.deleteAll(lesson.getSegments());
			lessonRepository.delete(lesson);
			return Result.success();
		} catch (Exception exception) {
			return Result.failureWrapper(PersistenceError.exceptionEncountered(exception));
		}
	}
}

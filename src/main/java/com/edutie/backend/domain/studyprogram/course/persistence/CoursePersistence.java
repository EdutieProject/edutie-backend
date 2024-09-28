package com.edutie.backend.domain.studyprogram.course.persistence;

import com.edutie.backend.domain.common.persistence.Persistence;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

public interface CoursePersistence extends Persistence<Course, CourseId> {
	/**
	 * Retrieve all courses associated with given science
	 *
	 * @param scienceId science id
	 * @return Wrapper result of the desired list
	 */
	WrapperResult<List<Course>> getAllOfScienceId(ScienceId scienceId);

	/**
	 * Retrieve all accessible courses associated with given science
	 *
	 * @param scienceId science id
	 * @return Wrapper result of the desired list
	 */
	WrapperResult<List<Course>> getAllAccessibleOfScienceId(ScienceId scienceId);

	/**
	 * Retrieve all courses created by given educator
	 *
	 * @param educatorId educator id
	 * @return Wrapper result of the desired list
	 */
	WrapperResult<List<Course>> getAllOfEducatorId(EducatorId educatorId);

	/**
	 * Removes the course together with the underlying lessons & segments
	 *
	 * @param course course to be removed
	 * @return Result object
	 */
	Result deepRemove(Course course);
}

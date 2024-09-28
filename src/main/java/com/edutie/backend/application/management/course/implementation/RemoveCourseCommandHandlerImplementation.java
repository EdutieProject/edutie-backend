package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.course.RemoveCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.RemoveCourseCommand;
import com.edutie.backend.domain.education.EducationError;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import validation.Result;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class RemoveCourseCommandHandlerImplementation extends HandlerBase implements RemoveCourseCommandHandler {
	private final CoursePersistence coursePersistence;
	private final EducatorPersistence educatorPersistence;

	@Override
	public Result handle(RemoveCourseCommand command) {
		log.info("Removing course of id {}", command.courseId().identifierValue());
		Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
		Course course = coursePersistence.getById(command.courseId()).getValue();
		if (!educator.isAuthorOf(course)) {
			log.info("Educator does not have sufficient permissions to modify this course");
			return Result.failure(EducationError.educatorMustBeAuthorError(Course.class));
		}
		if (command.removeLessons()) {
			coursePersistence.deepRemove(course).throwIfFailure();
		} else {
			coursePersistence.remove(course).throwIfFailure();
		}
		log.info("Course removed successfully");
		return coursePersistence.remove(course);
	}
}

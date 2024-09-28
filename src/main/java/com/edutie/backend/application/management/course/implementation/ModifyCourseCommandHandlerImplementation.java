package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.course.ModifyCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.ModifyCourseCommand;
import com.edutie.backend.domain.education.EducationError;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import validation.Result;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import lombok.*;
import lombok.extern.slf4j.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ModifyCourseCommandHandlerImplementation extends HandlerBase implements ModifyCourseCommandHandler {
	private final CoursePersistence coursePersistence;
	private final EducatorPersistence educatorPersistence;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Result handle(ModifyCourseCommand command) {
		log.info("Modifying course of id {} by educator of id {}", command.courseId().identifierValue(), command.educatorUserId().identifierValue());
		Course course = coursePersistence.getById(command.courseId()).getValue();
		Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
		if (!educator.isAuthorOf(course)) {
			log.info("Educator does not have sufficient permissions to modify this course");
			return Result.failure(EducationError.educatorMustBeAuthorError(Course.class));
		}
		if (command.courseName() != null)
			course.setName(command.courseName());
		if (command.courseDescription() != null)
			course.setDescription(command.courseDescription());
		if (command.accessibility() != null)
			course.setAccessible(command.accessibility());
		course.update(command.educatorUserId());
		coursePersistence.save(course).throwIfFailure();
		log.info("Course modification successful");
		return Result.success();
	}
}

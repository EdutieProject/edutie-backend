package com.edutie.application.learning.learningresult.implementation;

import com.edutie.application.common.HandlerBase;
import com.edutie.application.learning.learningresult.AssessSolutionCommandHandler;
import com.edutie.application.learning.learningresult.commands.AssessSolutionCommand;
import com.edutie.domain.service.personalization.learningresult.LearningResultPersonalizationService;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.learningresult.entities.submission.common.SolutionSubmissionBase;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.persistence.StudentPersistence;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class AssessSolutionCommandHandlerImplementation extends HandlerBase implements AssessSolutionCommandHandler {
	private final LearningExperiencePersistence learningExperiencePersistence;
	private final StudentPersistence studentPersistence;
	private final SolutionSubmissionPersistence solutionSubmissionPersistence;
	private final LearningResultPersistence learningResultPersistence;
	private final LearningResultPersonalizationService learningResultPersonalizationService;

	@Override
	public WrapperResult<LearningResult> handle(AssessSolutionCommand command) {
		log.info("Handling assessment for student of id {} and learning resource of id {}", command.studentUserId(), command.learningExperienceId());
		Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
		LearningExperience learningExperience = learningExperiencePersistence.getById(command.learningExperienceId()).getValue();

		SolutionSubmissionBase solutionSubmissionBase = SolutionSubmissionBase.create(
				student,
				learningExperience.getId(),
				learningExperience.getDefinitionType(),
				command.solutionSubmissionText(),
				command.hintsRevealedCount()
		);
		solutionSubmissionPersistence.save(solutionSubmissionBase).throwIfFailure();

		LearningResult learningResult = learningResultPersonalizationService.personalize(solutionSubmissionBase, student).getValue();
		learningResultPersistence.save(learningResult).throwIfFailure();
		return WrapperResult.successWrapper(learningResult);
	}
}

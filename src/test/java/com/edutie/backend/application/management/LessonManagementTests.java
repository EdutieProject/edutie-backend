package com.edutie.backend.application.management;

import com.edutie.backend.application.management.lesson.*;
import com.edutie.backend.application.management.lesson.commands.*;
import com.edutie.backend.application.management.lesson.queries.CreatedLessonsQuery;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.enums.EducatorType;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.Result;
import validation.WrapperResult;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import java.util.List;

@SpringBootTest
public class LessonManagementTests {
	private final UserId userId = new UserId();
	private final Administrator administrator = Administrator.create(new UserId());
	@Autowired
	LessonPersistence lessonPersistence;
	@Autowired
	CoursePersistence coursePersistence;
	@Autowired
	SciencePersistence sciencePersistence;
	@Autowired
	EducatorPersistence educatorPersistence;
	@Autowired
	SegmentPersistence segmentPersistence;
	@Autowired
	AdministratorPersistence administratorPersistence;
	@Autowired
	CreateLessonCommandHandler createLessonCommandHandler;
	@Autowired
	CreatedLessonsQueryHandler createdLessonsQueryHandler;
	@Autowired
	ModifyLessonCommandHandler modifyLessonCommandHandler;
	@Autowired
	RemoveLessonCommandHandler removeLessonCommandHandler;
	private Lesson previousLesson;
	private Educator educator;
	private Course course;

	@BeforeEach
	public void testSetup() {
		administratorPersistence.save(administrator);
		educator = Educator.create(userId, administrator);
		educator.setType(EducatorType.ADMINISTRATOR);
		educatorPersistence.save(educator);
		Science science = Science.create(educator).getValue();
		sciencePersistence.save(science);
		course = Course.create(educator, science);
		coursePersistence.save(course);
		previousLesson = Lesson.create(educator, course);
		lessonPersistence.save(previousLesson);
	}

	@Test
	public void createLessonTest() {
		LessonId prevLessonId = previousLesson.getId();

		CreateLessonCommand command = new CreateLessonCommand().educatorUserId(userId).lessonName("Lesson!").previousLessonId(prevLessonId);

		WrapperResult<Lesson> lessonWrapperResult = createLessonCommandHandler.handle(command);
		assert lessonWrapperResult.isSuccess();

		Lesson createdLesson = lessonWrapperResult.getValue();
		assert createdLesson.getName().equals("Lesson!");
		assert createdLesson.getPreviousElement().getId().equals(prevLessonId);
		assert createdLesson.getNextElements().isEmpty();
		assert !segmentPersistence.getAllOfLessonId(createdLesson.getId()).getValue().isEmpty();

		assert lessonPersistence.getById(lessonWrapperResult.getValue().getId()).isSuccess();
		Lesson fetched = lessonPersistence.getById(lessonWrapperResult.getValue().getId()).getValue();
		assert fetched.getPreviousElement().getId().equals(prevLessonId);
	}

	@Test
	@Transactional(propagation = Propagation.SUPPORTS)
	public void lessonInBetweenPlaygroundTest() {
		//TODO: this does not work - what the fuck?
		Lesson lesson = Lesson.create(educator, course);
		lessonPersistence.save(lesson);
		Lesson nextLesson = Lesson.create(educator, lesson);
		Result res = lessonPersistence.save(nextLesson);

		assert res.isSuccess();

		Lesson fetchedNext = lessonPersistence.getById(lesson.getId()).getValue();
		for (Lesson a: fetchedNext.getNextElements()) {
			System.out.println("Next element");
		}
	}

	@Test
	//TODO: fix this automated test - (it works regarding DB)
	@Transactional(propagation = Propagation.SUPPORTS)
	public void createLessonInBetweenTest() {
		// Run this once to have 2 lessons in the beginning
		CreateLessonCommand command1 = new CreateLessonCommand().educatorUserId(userId).lessonName("Lesson!").previousLessonId(previousLesson.getId());
		Lesson alreadyPresent = createLessonCommandHandler.handle(command1).getValue();

		LessonId prevLessonId = alreadyPresent.getPreviousElement().getId();
		LessonId nextLessonId = alreadyPresent.getId();

		CreateLessonCommand command2 = new CreateLessonCommand().educatorUserId(userId).lessonName("Lesson in between").previousLessonId(prevLessonId).nextLessonId(nextLessonId);
		WrapperResult<Lesson> lessonWrapperResult = createLessonCommandHandler.handle(command2);
		assert lessonWrapperResult.isSuccess();
		Lesson lessonInBetween = lessonWrapperResult.getValue();

		assert lessonInBetween.getPreviousElement().getId().equals(prevLessonId);
		assert lessonInBetween.getNextElements().stream().anyMatch(o -> o.getId().equals(nextLessonId));
		assert !segmentPersistence.getAllOfLessonId(lessonInBetween.getId()).getValue().isEmpty();
	}

	@Test
	public void getCreatedLessonsTest() {
		CreatedLessonsQuery query = new CreatedLessonsQuery().educatorUserId(userId);

		WrapperResult<List<Lesson>> createdLessonsResult = createdLessonsQueryHandler.handle(query);

		assert createdLessonsResult.isSuccess();
		assert !createdLessonsResult.getValue().isEmpty();
	}

	@Test
	public void modifyLessonTest() {
		ModifyLessonCommand command = new ModifyLessonCommand().educatorUserId(userId).lessonId(previousLesson.getId()).lessonDescription("Hello world!").lessonName("Changed lesson name");

		Result result = modifyLessonCommandHandler.handle(command);
		assert result.isSuccess();

		Lesson modifiedLesson = lessonPersistence.getById(previousLesson.getId()).getValue();
		assert modifiedLesson.getName().equals("Changed lesson name");
		assert modifiedLesson.getDescription().equals("Hello world!");
	}

	@Test
	public void removeLessonTest() {
		RemoveLessonCommand command = new RemoveLessonCommand().educatorUserId(userId).lessonId(previousLesson.getId());
		Result result = removeLessonCommandHandler.handle(command);

		assert result.isSuccess();
		assert lessonPersistence.getById(previousLesson.getId()).isFailure();
	}

}

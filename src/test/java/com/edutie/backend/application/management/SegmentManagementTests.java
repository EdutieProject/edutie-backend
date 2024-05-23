package com.edutie.backend.application.management;

import com.edutie.backend.application.management.segment.CreateSegmentCommandHandler;
import com.edutie.backend.application.management.segment.CreatedSegmentsQueryHandler;
import com.edutie.backend.application.management.segment.ModifySegmentCommandHandler;
import com.edutie.backend.application.management.segment.RemoveSegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.CreateSegmentCommand;
import com.edutie.backend.application.management.segment.commands.ModifySegmentCommand;
import com.edutie.backend.application.management.segment.commands.RemoveSegmentCommand;
import com.edutie.backend.application.management.segment.queries.CreatedSegmentsQuery;
import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

@SpringBootTest
public class SegmentManagementTests {
    @Autowired
    CreateSegmentCommandHandler createSegmentCommandHandler;
    @Autowired
    CreatedSegmentsQueryHandler createdSegmentsQueryHandler;
    @Autowired
    ModifySegmentCommandHandler modifySegmentCommandHandler;
    @Autowired
    RemoveSegmentCommandHandler removeSegmentCommandHandler;

    @Autowired
    SciencePersistence sciencePersistence;
    @Autowired
    CoursePersistence coursePersistence;
    @Autowired
    LessonPersistence lessonPersistence;
    @Autowired
    SegmentPersistence segmentPersistence;
    @Autowired
    EducatorPersistence educatorPersistence;

    private final UserId userId = new UserId();
    private final AdminId adminId = new AdminId();
    private Segment previousSegment;

    @BeforeEach
    public void testSetup() {
        Educator educator = Educator.create(userId, adminId);
        educatorPersistence.save(educator);
        Science science = Science.create(userId);
        sciencePersistence.save(science);
        Course course = Course.create(educator, science);
        coursePersistence.save(course);
        Lesson lesson = Lesson.create(educator, course);
        lessonPersistence.save(lesson);
        previousSegment = Segment.create(educator, lesson);
        segmentPersistence.save(previousSegment);
    }

    @Test
    public void createSegmentTest() {
        SegmentId previousSegmentId = previousSegment.getId();

        CreateSegmentCommand command = new CreateSegmentCommand()
                .educatorUserId(userId)
                .previousSegmentId(previousSegmentId)
                .segmentName("Hello World!")
                .segmentTheoryDescription("Desc");

        WrapperResult<Segment> segmentWrapperResult = createSegmentCommandHandler.handle(command);

        if (segmentWrapperResult.isFailure()) {
            throw new AssertionError(segmentWrapperResult.getError().toString());
        }

        assert segmentWrapperResult.getValue().getName().equals("Hello World!");
        assert segmentWrapperResult.getValue().getTheoryDescription().text().equals("Desc");
        assert segmentPersistence.getById(segmentWrapperResult.getValue().getId()).isSuccess();
    }

    @Test
    public void getCreatedSegmentsTest() {
        CreatedSegmentsQuery query = new CreatedSegmentsQuery()
                .educatorUserId(userId);

        WrapperResult<List<Segment>> segmentWrapperResult = createdSegmentsQueryHandler.handle(query);

        assert segmentWrapperResult.isSuccess();
        assert segmentWrapperResult.getValue().contains(previousSegment);
    }

    @Test
    public void modifySegmentTest() {
        SegmentId previousSegmentId = previousSegment.getId();

        ModifySegmentCommand command = new ModifySegmentCommand()
                .educatorUserId(userId)
                .segmentId(previousSegmentId)
                .segmentName("Hello World");

        Result result = modifySegmentCommandHandler.handle(command);
        assert result.isSuccess();
        assert segmentPersistence.getById(previousSegmentId).isSuccess();
    }

    @Test
    public void removeSegmentTest() {
        SegmentId previousSegmentId = previousSegment.getId();

        RemoveSegmentCommand command = new RemoveSegmentCommand()
                .educatorUserId(userId)
                .segmentId(previousSegmentId);

        Result result = removeSegmentCommandHandler.handle(command);
        assert result.isSuccess();
        assert segmentPersistence.getById(previousSegmentId).isFailure();
    }
}

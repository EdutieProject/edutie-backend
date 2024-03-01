package com.edutie.backend.application.creator.segment;

import com.edutie.backend.application.creator.segment.commands.*;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lessonsegment.LessonSegment;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

/**
 * Service interface for managing lesson segments in the educational system.
 */
public interface SegmentService {

    /**
     * Retrieves a list of all lesson segments created by the specified educator.
     *
     * @param educatorId The identifier of the educator.
     * @return A list of lesson segments created by the educator.
     */
    List<LessonSegment> getAllSegmentsOfCreator(EducatorId educatorId);

    /**
     * Creates a new lesson segment to be scheduled as the next segment based on the provided command.
     *
     * @param command The command containing information for creating the next segment.
     * @return A {@code WrapperResult} containing the created segment or any associated errors.
     */
    WrapperResult<LessonSegment> createSegmentAsNext(CreateSegmentAsNextCommand command);

    /**
     * Creates a new lesson segment to be scheduled in between existing segments based on the provided command.
     *
     * @param command The command containing information for creating the segment in between.
     * @return A {@code WrapperResult} containing the created segment or any associated errors.
     */
    WrapperResult<LessonSegment> createSegmentInBetween(CreateSegmentInBetweenCommand command);

    /**
     * Changes the properties of an existing lesson segment based on the provided command.
     *
     * @param command The command containing information for changing segment properties.
     * @return A {@code Result} indicating the success or failure of the operation.
     */
    Result changeSegmentProperties(ChangeSegmentPropertiesCommand command);

    /**
     * Moves an existing lesson segment to a different time or date based on the provided command.
     *
     * @param command The command containing information for moving the segment.
     * @return A {@code Result} indicating the success or failure of the operation.
     */
    Result moveSegment(MoveSegmentCommand command);

    /**
     * Removes an existing lesson segment based on the provided command.
     *
     * @param command The command containing information for removing the segment.
     * @return A {@code Result} indicating the success or failure of the operation.
     */
    Result removeSegment(RemoveSegmentCommand command);
}

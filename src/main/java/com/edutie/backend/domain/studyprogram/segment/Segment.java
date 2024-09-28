package com.edutie.backend.domain.studyprogram.segment;

import com.edutie.backend.domain.common.DomainErrors;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.studyprogram.common.TreeElementEntityBase;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import validation.Result;
import lombok.*;


/**
 * A segment of a lesson. Most atomic part of learning which is responsible for describing the goals
 * and requirements for the student to make. Segment is responsible for providing the student with the
 * learning resource adjusted for their needs.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class Segment extends TreeElementEntityBase<Segment, SegmentId> {
	private String name;
	private String snippetDescription;
	@Embedded
	@AttributeOverride(name = "identifierValue", column = @Column(name = "learning_resource_definition_id", unique = true))
	private LearningResourceDefinitionId learningResourceDefinitionId;
	@JoinColumn(name = "lesson_id")
	@ManyToOne(targetEntity = Lesson.class, fetch = FetchType.EAGER)
	@JsonIgnore
	@Setter(AccessLevel.PRIVATE)
	private Lesson lesson;

	/**
	 * Recommended constructor associating Lesson Segment with a creator and lesson
	 *
	 * @param educator creator reference
	 * @param lesson   lesson reference
	 * @return Lesson Segment
	 */
	public static Segment create(Educator educator, Lesson lesson) {
		Segment segment = new Segment();
		segment.setId(new SegmentId());
		segment.setCreatedBy(educator.getOwnerUserId());
		segment.setAuthorEducator(educator);
		segment.setLesson(lesson);
		return segment;
	}

	/**
	 * Recommended constructor associating Lesson Segment with a creator, lesson and a previous element
	 *
	 * @param educator        creator reference
	 * @param previousSegment previous segment reference
	 * @return Lesson Segment
	 */
	public static Segment create(Educator educator, Segment previousSegment) {
		Segment segment = new Segment();
		segment.setId(new SegmentId());
		segment.setCreatedBy(educator.getOwnerUserId());
		segment.setAuthorEducator(educator);
		segment.setLesson(previousSegment.getLesson());
		segment.setPreviousElement(previousSegment);
		return segment;
	}

	/**
	 * Adds next element to the lesson segment tree
	 *
	 * @param segment segment to be added as next
	 * @return Result of the operation
	 */
	@Override
	public Result addNextElement(Segment segment) {
		if (segment.getLesson() != lesson)
			return Result.failure(DomainErrors.invalidParentEntity());
		nextElements.add(segment);
		return Result.success();
	}

	@Override
	public Result setPreviousElement(Segment segment) {
		if (!segment.getLesson().equals(this.lesson)) {
			return Result.failure(DomainErrors.invalidParentEntity());
		}
		this.previousElement = segment;
		return Result.success();
	}
}

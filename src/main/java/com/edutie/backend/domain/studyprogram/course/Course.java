package com.edutie.backend.domain.studyprogram.course;

import com.edutie.backend.api.serialization.serializers.IdOnlySerializer;
import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.course.tag.CourseTag;
import com.edutie.backend.domain.studyprogram.course.tag.indentities.CourseTagId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A group of lessons with a tree-like structure. There are many fundamental lessons, and
 * each of those have a number of lessons assigned as next.
 * Technically a Lesson tree
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class Course extends AuditableEntityBase<CourseId> {
	private String name;
	private String description;
	private boolean accessible = false;
	@OneToMany(mappedBy = "course")
	@Setter(AccessLevel.PRIVATE)
	@JsonIgnore
	private List<Lesson> lessons = new ArrayList<>();
	@ManyToOne(targetEntity = Educator.class, fetch = FetchType.EAGER)
	@Setter(AccessLevel.PRIVATE)
	private Educator educator;
	@ManyToOne(targetEntity = Science.class)
	@JoinColumn(name = "science_id")
	@Setter(AccessLevel.PRIVATE)
	@JsonSerialize(using = IdOnlySerializer.class)
	private Science science;
	@ManyToMany(targetEntity = CourseTag.class, fetch = FetchType.EAGER)
	private Set<CourseTag> courseTags = new HashSet<>();

	/**
	 * Recommended constructor associating course with a creator and science
	 *
	 * @param educator creator reference
	 * @param science  science reference
	 * @return Course
	 */
	public static Course create(Educator educator, Science science) {
		Course course = new Course();
		course.setId(new CourseId());
		course.setCreatedBy(educator.getOwnerUserId());
		course.setEducator(educator);
		course.setScience(science);
		return course;
	}

	/**
	 * Adds a tag to course
	 *
	 * @param courseTag tag to add
	 */
	public void addTag(CourseTag courseTag) {
		courseTags.add(courseTag);
	}

	/**
	 * Removes a tag from course
	 *
	 * @param courseTag tag to remove
	 */
	public void removeTag(CourseTag courseTag) {
		courseTags.remove(courseTag);
	}

	/**
	 * Removes a tag from course by id
	 *
	 * @param courseTagId tag id to remove
	 */
	public void removeTagById(CourseTagId courseTagId) {
		courseTags.removeIf(courseTag -> courseTag.getId().equals(courseTagId));
	}

	/**
	 * Checks if course has a tag
	 *
	 * @param courseTag tag to check
	 * @return true if course has tag
	 */
	public boolean hasTag(CourseTag courseTag) {
		return courseTags.contains(courseTag);
	}
}

package com.edutie.backend.domain.studyprogram.course.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.studyprogram.course.identities.CourseTagId;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class CourseTag extends EntityBase<CourseTagId> {
	private String name;

	public static CourseTag create(String name) {
		CourseTag courseTag = new CourseTag();
		courseTag.setId(new CourseTagId());
		courseTag.name = name;
		return courseTag;
	}
}

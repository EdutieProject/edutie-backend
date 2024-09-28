package com.edutie.backend.domain.studyprogram.course.tag;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.studyprogram.course.tag.indentities.CourseTagId;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class CourseTag extends EntityBase<CourseTagId> {
	private String name;

	public static CourseTag create(String name) {
		CourseTag courseTag = new CourseTag();
		courseTag.setId(new CourseTagId());
		courseTag.name = name;
		return courseTag;
	}
}

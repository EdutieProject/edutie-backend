package com.edutie.backend.domain.personalization.student;

import com.edutie.backend.domain.administration.Role;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Student class conceals all the student characteristics of the user.
 * Note that the relationship with the user is maintained by createdBy field in the base class.
 */
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Student extends Role<StudentId> {
	@OneToMany(targetEntity = LearningResult.class, fetch = FetchType.EAGER, mappedBy = "student")
	//TODO: lazy load
	private final List<LearningResult> learningHistory = new ArrayList<>();

	public static Student create(UserId userId) {
		Student student = new Student();
		student.setOwnerUserId(userId);
		student.setId(new StudentId());
		return student;
	}

	public List<LearningResult> getLearningHistoryByKnowledgeSubject(KnowledgeSubjectId knowledgeSubjectId) {
		return learningHistory.stream().filter(o -> o.getSolutionSubmission().getLearningResource().getDefinition().getLearningRequirements().stream().anyMatch(x -> x.getKnowledgeSubjectId().equals(knowledgeSubjectId))).collect(Collectors.toList());
	}
}

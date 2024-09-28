package com.edutie.backend.domain.personalization.learningresource;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.personalization.learningresource.entities.*;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * A singular form of learning in the application.
 * This is the resource which is used by the learner to exercise
 * his knowledge. It may be exclusively generated for a given student.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class LearningResource extends AuditableEntityBase<LearningResourceId> {
	@OneToMany(targetEntity = LearningResourceProblemDescriptor.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<LearningResourceProblemDescriptor> problemDescriptors = new HashSet<>();
	@ManyToOne(targetEntity = LearningResourceDefinition.class, fetch = FetchType.EAGER)
	@Setter(AccessLevel.PRIVATE)
	private LearningResourceDefinition definition;
	@Embedded
	@AttributeOverride(name = "identifierValue", column = @Column(name = "student_id"))
	@Setter(AccessLevel.PRIVATE)
	private StudentId studentId;
	@OneToOne(targetEntity = Activity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Activity activity;
	@OneToOne(targetEntity = Theory.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Theory theory;

	/**
	 * Recommended constructor that creates learning resource from L.R.G.S. and other different details.
	 *
	 * @param generationSchema                   generation schema
	 * @param activity                           activity
	 * @param theory                             theory
	 * @param learningResourceProblemDescriptors problem details text
	 * @return new Learning Resource
	 */
	public static LearningResource create(LearningResourceGenerationSchema generationSchema, Activity activity, Theory theory, Set<LearningResourceProblemDescriptor> learningResourceProblemDescriptors) {
		LearningResource learningResource = new LearningResource();
		learningResource.setId(new LearningResourceId());
		learningResource.setCreatedBy(generationSchema.getStudent().getOwnerUserId());
		learningResource.setStudentId(generationSchema.getStudent().getId());
		learningResource.setDefinition(generationSchema.getLearningResourceDefinition());
		learningResource.setActivity(activity);
		learningResource.setTheory(theory);
		learningResource.setProblemDescriptors(learningResourceProblemDescriptors);
		return learningResource;
	}
}

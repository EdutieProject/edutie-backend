package com.edutie.domain.core.learning.learningexperience;

import com.edutie.domain.core.common.base.AuditableEntityBase;
import com.edutie.domain.core.learning.learningexperience.entities.LearningExperienceRequirement;
import com.edutie.domain.core.learning.learningexperience.entities.activity.base.Activity;
import com.edutie.domain.core.learning.learningexperience.entities.notes.LearningNotes;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.domain.core.learning.student.identities.StudentId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * A singular form of learning in the application.
 * This is the resource which is used by the learner to exercise
 * his knowledge. It may be exclusively generated for a given student.
 */
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@MappedSuperclass
public class LearningExperience<TActivity extends Activity> extends AuditableEntityBase<LearningExperienceId> {
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<LearningExperienceRequirement> requirements = new HashSet<>();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TActivity activity;
    @OneToOne(targetEntity = LearningNotes.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private LearningNotes notes;
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "student_id"))
    private StudentId studentId;
}

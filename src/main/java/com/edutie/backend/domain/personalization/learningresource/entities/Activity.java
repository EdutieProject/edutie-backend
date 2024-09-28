package com.edutie.backend.domain.personalization.learningresource.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.personalization.learningresource.identities.ActivityId;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
public class Activity extends EntityBase<ActivityId> {
	@OneToMany(targetEntity = Hint.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Hint> hints = new HashSet<>();
	@Column(columnDefinition = "TEXT")
	private String activityText;

	public static Activity create(String activityText, Set<Hint> hints) {
		Activity activity = new Activity();
		activity.setId(new ActivityId());
		activity.setActivityText(activityText);
		activity.setHints(hints);
		return activity;
	}
}

package com.edutie.backend.domain.personalization.learningresource.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.personalization.learningresource.identities.TheoryId;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
public class Theory extends EntityBase<TheoryId> {
	@Column(columnDefinition = "TEXT")
	private String overview;
	@Column(columnDefinition = "TEXT")
	private String mermaidGraph;

	public static Theory create(String overviewText, String mermaidGraphString) {
		Theory theory = new Theory();
		theory.setId(new TheoryId());
		theory.setMermaidGraph(mermaidGraphString);
		theory.setOverview(overviewText);
		return theory;
	}
}

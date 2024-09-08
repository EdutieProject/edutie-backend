package com.edutie.backend.domain.education.knowledgesubject;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import jakarta.persistence.*;
import lombok.*;

/**
 * Knowledge subject reference is an entity that represents only the identifier of the knowledge subject.
 * In the Edutie context the details of the knowledge subject are not important as of now.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class KnowledgeSubjectReference extends EntityBase<KnowledgeSubjectId> {
	public static KnowledgeSubjectReference create(KnowledgeSubjectId id) {
		KnowledgeSubjectReference knowledgeSubjectReference = new KnowledgeSubjectReference();
		knowledgeSubjectReference.setId(id);
		return knowledgeSubjectReference;
	}
}

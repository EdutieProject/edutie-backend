package com.edutie.backend.domain.personalization.knowledgesubject;

import com.edutie.backend.domain.common.base.EntityBase;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class KnowledgeSubjectReference extends EntityBase<KnowledgeSubjectId> {
}

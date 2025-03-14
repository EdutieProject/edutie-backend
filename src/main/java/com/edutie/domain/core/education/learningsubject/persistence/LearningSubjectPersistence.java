package com.edutie.domain.core.education.learningsubject.persistence;

import com.edutie.domain.core.common.persistence.Persistence;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import validation.WrapperResult;

import java.util.List;

public interface LearningSubjectPersistence extends Persistence<LearningSubject, LearningSubjectId> {
    WrapperResult<List<LearningSubject>> getCreatedLearningSubjects(Educator educatorId);
}

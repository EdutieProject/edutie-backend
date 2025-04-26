package com.edutie.application.management.learningsubject.view;

import com.edutie.domain.core.education.knowledgesubject.view.KnowledgeSubjectDetailsView;
import com.edutie.domain.core.education.learningsubject.LearningSubject;

public record LearningSubjectManagementView(
        LearningSubject learningSubject,
        KnowledgeSubjectDetailsView knowledgeSubjectDetails
) {
}

package com.edutie.domain.core.learning.learningresult.persistence;

import com.edutie.domain.core.common.persistence.Persistence;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.identities.LearningResultId;
import com.edutie.domain.core.learning.student.identities.StudentId;
import validation.WrapperResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface LearningResultPersistence extends Persistence<LearningResult, LearningResultId> {

    /**
     * Retrieves latest results associated with given student. Results are ordered from the latest to the older.
     * The retrieved amount is supplied as a result
     *
     * @param studentId student id
     * @param amount    learning result amount
     * @return Wrapper Result of Learning Results
     */
    WrapperResult<List<LearningResult>> getLatestResultsOfStudent(StudentId studentId, Integer amount, LocalDateTime maxPastDate);

    /**
     * Retrieves latest learning result of student, if any.
     *
     * @param studentId student id
     * @return Wrapper Result of Learning Result
     */
    WrapperResult<LearningResult> getSingleLatestResultOfStudent(StudentId studentId);

    /**
     * Provides learning results associated with given learning resource definition ids.
     * @param studentId student id
     * @param learningResourceDefinitionIds learning resource definition ids set
     * @return Wrapper result of Learning Result list
     */
    WrapperResult<List<LearningResult>> getLearningResultsOfStudentByLearningResourceDefinitionIds(StudentId studentId, Set<LearningResourceDefinitionId> learningResourceDefinitionIds);

    /**
     * Provides learning results associated with certain learning resource definition id created by given student.
     *
     * @param learningResourceDefinitionId learning resource definition id
     * @return Learning Result List Wrapper Result
     */
    WrapperResult<List<LearningResult>> getLearningResultsOfStudentByLearningResourceDefinitionId(StudentId studentId, LearningResourceDefinitionId learningResourceDefinitionId);

    /**
     * Provides learning results associated with the L. requirement of certain knowledge subject id created by given student.
     *
     * @param knowledgeSubjectId knowledge subject id
     * @return Learning Result List Wrapper Result
     */
    WrapperResult<List<LearningResult>> getLearningResultsOfStudentByKnowledgeSubjectId(StudentId studentId, KnowledgeSubjectId knowledgeSubjectId);
}

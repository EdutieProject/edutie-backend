package com.edutie.backend.domain.personalization.learningresult.persistence;

import com.edutie.backend.domain.common.persistence.Persistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import validation.WrapperResult;

import java.time.LocalDateTime;
import java.util.List;

public interface LearningResultPersistence extends Persistence<LearningResult, LearningResultId> {

    /**
     * Retrieves latest results associated with given student. Results are ordered from the latest to the older.
     * The retrieved amount is supplied as a result
     *
     * @param studentId student id
     * @param amount    learning result amount
     * @return Wrapper Result of Learning Results
     */
    WrapperResult<List<LearningResult>> getLatestResultsOfStudent(StudentId studentId, Integer amount, LocalDateTime maxDate);

    /**
     * Provides learning results associated with certain learning resource definition id.
     *
     * @param learningResourceDefinitionId learning resource definition id
     * @return Learning Result List Wrapper Result
     */
    WrapperResult<List<LearningResult>> getLearningResultsForStudentByLearningResourceDefinitionId(StudentId studentId, LearningResourceDefinitionId learningResourceDefinitionId);
}

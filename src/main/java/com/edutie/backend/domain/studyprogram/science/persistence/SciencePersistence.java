package com.edutie.backend.domain.studyprogram.science.persistence;

import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.common.persistence.Persistence;
import validation.WrapperResult;

import java.util.List;

public interface SciencePersistence extends Persistence<Science, ScienceId> {
    /**
     * Retrieve all sciences
     * @return Science list
     */
    WrapperResult<List<Science>> getAll();
}

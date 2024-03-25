package com.edutie.backend.domain.studyprogram.science.persistence;

import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.common.persistence.PersistenceBase;

import java.util.List;

public interface SciencePersistence extends PersistenceBase<Science, ScienceId> {
    /**
     * Retrieve all sciences
     * @return Science list
     */
    List<Science> getAll();
}

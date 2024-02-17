package com.edutie.backend.infrastucture.persistence.contexts.studyprogram;

import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.infrastucture.persistence.contexts.base.PersistenceContext;

import java.util.List;

public interface SciencePersistenceContext extends PersistenceContext<Science, ScienceId> {
    /**
     * Retrieve all sciences
     * @return Science list
     */
    List<Science> getAll();
}

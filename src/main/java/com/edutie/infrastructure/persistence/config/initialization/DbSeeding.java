package com.edutie.infrastructure.persistence.config.initialization;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbSeeding {

    @Autowired
    LearningSubjectPersistence learningSubjectPersistence;
    @Autowired
    EducatorPersistence educatorPersistence;

    @PostConstruct
    public void seedDb() {
        Educator educator = Educator.create(new UserId());
        educatorPersistence.save(educator);

        LearningSubject learningSubject = LearningSubject.createBlank(educator, "Banana");
        learningSubject.setRelatedKnowledgeSubjectId(new KnowledgeSubjectId("Q503"));
        learningSubject.appendRequirement(
                "How does a banana tree grow?",
                PromptFragment.of("Student will understand the life cycle of a banana tree, including the growth process from corm to fruit-bearing inflorescence, and will be able to explain the environmental factors that influence each stage of growth, demonstrating this understanding through a visual representation of the banana plant's development.")
        );
        learningSubject.appendRequirement(
                "What is the science behind the banana's taste",
                PromptFragment.of("Student will understand the biochemical processes that contribute to the unique taste of bananas, including the roles of sugars, acids, and volatile compounds, and be able to explain how these elements change during the ripening process to influence flavor and aroma.")
        );
        learningSubject.appendRequirement(
                "How did banana evolve its characteristic shape",
                PromptFragment.of("Student will understand the evolutionary processes and environmental factors that contributed to the characteristic elongated and curved shape of bananas, demonstrating their grasp through the analysis of genetic adaptations and the impact of cultivation practices on banana morphology.")
        );

        learningSubjectPersistence.save(learningSubject).throwIfFailure();

        System.out.println("Seeded banana learning subject of id " + learningSubject.getId());
    }
}

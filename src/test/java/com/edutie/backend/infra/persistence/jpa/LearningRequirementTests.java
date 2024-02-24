package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.creator.Creator;
import com.edutie.backend.domain.studyprogram.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.CreatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.LearningRequirementRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.ScienceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LearningRequirementTests {
    private final UserId testUserId = new UserId();
    private Creator creator;
    private Science science;
    private LearningRequirement learningRequirement;


    @Autowired
    private CreatorRepository creatorRepository;
    @Autowired
    private LearningRequirementRepository learningRequirementRepository;
    @Autowired
    private ScienceRepository scienceRepository;

    @BeforeEach
    public void testSetup() {
        creator = Creator.create(testUserId);
        creatorRepository.save(creator);

        science = Science.create(testUserId);
        scienceRepository.save(science);

        learningRequirement = LearningRequirement.create(creator, science);
    }

    @Test
    public void testLearningRequirementCreation() {

        assertNotNull(learningRequirement);
        assertEquals(creator, learningRequirement.getCreator());
        assertEquals(science, learningRequirement.getScience());

        learningRequirementRepository.save(learningRequirement);

        var fetched = learningRequirementRepository.findById(learningRequirement.getId()).orElseThrow();
        assertEquals(fetched, learningRequirement);
    }

    @Test
    public void testCourseNameAndDescription() {
        learningRequirement.setName("TestName");
        learningRequirement.setDescription(PromptFragment.of("TestDescription"));

        assertEquals("TestName", learningRequirement.getName());
        assertEquals("TestDescription", learningRequirement.getDescription().text());
    }

    @Test
    public void testOneToManyRelationship() {

        LearningRequirement learningRequirement1 = LearningRequirement.create(creator, science);
        LearningRequirement learningRequirement2 = LearningRequirement.create(creator, science);

        learningRequirementRepository.save(learningRequirement);
        learningRequirementRepository.save(learningRequirement1);
        learningRequirementRepository.save(learningRequirement2);

        var fetched = learningRequirementRepository.findById(learningRequirement.getId()).orElseThrow();
        var fetched1 = learningRequirementRepository.findById(learningRequirement1.getId()).orElseThrow();
        var fetched2 = learningRequirementRepository.findById(learningRequirement2.getId()).orElseThrow();

        assertEquals(fetched, learningRequirement);
        assertEquals(fetched1, learningRequirement1);
        assertEquals(fetched2, learningRequirement2);
    }
}

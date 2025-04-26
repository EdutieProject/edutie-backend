package com.edutie.domain.core.education.learningsubject;

import com.edutie.TestUtils;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.enums.EducatorType;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.education.learningsubject.entities.LearningSubjectRequirement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

@SpringBootTest
class LearningSubjectTest {
    private final UserId userId = new UserId();
    private final Educator educator = Educator.create(userId);

    @BeforeEach
    public void testSetup() {
        educator.setType(EducatorType.ADMINISTRATOR);
    }

    @Test
    public void appendRequirement() throws JsonProcessingException {
        LearningSubject learningSubject = LearningSubject.createBlank(educator, "Hey Hey Hey!");
        learningSubject.appendRequirement("R1", PromptFragment.of(""));
        learningSubject.appendRequirement("R2", PromptFragment.of(""));
        learningSubject.appendRequirement("R3", PromptFragment.of(""));

        System.out.println(new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writeValueAsString(learningSubject.getRequirements()));

        assert learningSubject.getRequirements().get(0).getTitle().equals("R1");
        assert learningSubject.getRequirements().get(2).getTitle().equals("R3");
    }

    @Test
    public void insertRequirement() {
        LearningSubject learningSubject = LearningSubject.createBlank(educator, "Hey Hey Hey!");
        learningSubject.appendRequirement("R1", PromptFragment.of(""));
        learningSubject.appendRequirement("R2", PromptFragment.of(""));
        learningSubject.appendRequirement("R3", PromptFragment.of(""));
        assert learningSubject.insertRequirement("Hello!", PromptFragment.of(""), 1).isSuccess();

        assert learningSubject.getRequirements().get(1).getTitle().equals("Hello!");
    }

    @Test
    public void requirementDelete() {
        LearningSubject learningSubject = LearningSubject.createBlank(educator, "Hey Hey Hey!");
        learningSubject.appendRequirement("R1", PromptFragment.of(""));
        learningSubject.appendRequirement("R2", PromptFragment.of(""));
        learningSubject.appendRequirement("R3", PromptFragment.of(""));
        assert learningSubject.getRequirements().size() == 3;

        ElementalRequirementId requirementId = learningSubject.getRequirements().get(0).getId();
        learningSubject.removeRequirement(requirementId);
        assert learningSubject.getRequirements().getFirst().getTitle().equals("R2");
    }

    @Test
    void testEquals() throws Throwable {
        LearningSubject learningSubject1 = LearningSubject.createBlank(educator, "A");
        LearningSubject learningSubject2 = LearningSubject.createBlank(educator, "B");
        LearningSubject learningSubject3 = LearningSubject.createBlank(educator, "C");

        TestUtils.setPrivateField(learningSubject2, "id", learningSubject1.getId());

        assert learningSubject1.equals(learningSubject2);
        assert !learningSubject2.equals(learningSubject3);
    }

    @Test
    void createBlank() {
        LearningSubject learningSubject = LearningSubject.createBlank(educator, "A");

        assert learningSubject.getName().equals("A");
        assert learningSubject.getAuthorEducator().equals(educator);
    }

    @Test
    void isKnowledgeOriginEmpty() {
        LearningSubject learningSubject = LearningSubject.createBlank(educator, "A");

        assert learningSubject.isKnowledgeOriginEmpty();
    }

    @Test
    void setRelatedKnowledgeSubjectId() {
        LearningSubject learningSubject = LearningSubject.createBlank(educator, "A");
        learningSubject.setRelatedKnowledgeSubjectId(new KnowledgeSubjectId());

        assert !learningSubject.isKnowledgeOriginEmpty();
    }

    @Test
    void inferAndInsertRequirement() {
        LearningSubject learningSubject = LearningSubject.createBlank(educator, "A");
        learningSubject.inferAndInsertRequirement(
                "a",
                0,
                (a, b) -> WrapperResult.successWrapper(PromptFragment.empty())
        );

        assert !learningSubject.getRequirements().isEmpty();
        assert learningSubject.getRequirements().getFirst().getTitle().equals("a");
        assert learningSubject.getRequirements().getFirst().getStudentObjective().equals(PromptFragment.empty());
    }


    @Test
    void moveRequirement() {
        LearningSubject learningSubject = LearningSubject.createBlank(educator, "Hey Hey Hey!");
        learningSubject.appendRequirement("R1", PromptFragment.of(""));
        learningSubject.appendRequirement("R2", PromptFragment.of(""));
        learningSubject.appendRequirement("R3", PromptFragment.of(""));
        assert learningSubject.getRequirements().size() == 3;

        learningSubject.moveRequirement(0, 1);
        assert learningSubject.getRequirements().getFirst().getTitle().equals("R2");
    }

    @Test
    public void chooseRequirementStatic() {
        LearningSubject learningSubject = LearningSubject.createBlank(educator, "Hey Hey Hey!");
        learningSubject.appendRequirement("R1", PromptFragment.of(""));
        LearningSubjectRequirement learningSubjectRequirement = learningSubject.getRequirements().getFirst();

        LearningSubjectRequirement requirement = learningSubject.chooseLearningSubjectRequirement(
                learningSubjectRequirement.getId(),
                (a) -> WrapperResult.successWrapper(LearningSubjectRequirement.create(learningSubject, "Abc", PromptFragment.empty(), 0))
        );

        assert requirement.equals(learningSubjectRequirement);
    }

    @Test
    public void chooseRequirementDynamic() {
        LearningSubject learningSubject = LearningSubject.createBlank(educator, "Hey Hey Hey!");

        LearningSubjectRequirement requirement = learningSubject.chooseLearningSubjectRequirement(
                null,
                (a) -> WrapperResult.successWrapper(LearningSubjectRequirement.create(learningSubject, "Abc", PromptFragment.empty(), 0))
        );

        assert requirement.getTitle().equals("Abc");
    }


}
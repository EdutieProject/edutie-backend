package com.edutie.domain.service.education.learningsubject;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.entities.LearningSubjectRequirement;
import com.edutie.domain.core.education.learningsubject.service.DynamicRequirementSelectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class DynamicRequirementSelectionServiceImplementation implements DynamicRequirementSelectionService {
    @Override
    public WrapperResult<LearningSubjectRequirement> chooseRequirement(LearningSubject learningSubject) {
        //TODO
        return WrapperResult.successWrapper(learningSubject.getRequirements().getFirst());
    }
}

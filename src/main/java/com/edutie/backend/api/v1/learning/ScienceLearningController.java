package com.edutie.backend.api.v1.learning;


import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.api.v1.authentication.AuthenticationPlaceholder;
import com.edutie.backend.application.learning.science.AccessibleSciencesQueryHandler;
import com.edutie.backend.application.learning.science.queries.AccessibleSciencesQuery;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.JsonWebToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import validation.WrapperResult;

@RestController
@RequestMapping("api/v1/learning/sciences")
@RequiredArgsConstructor
public class ScienceLearningController {


    private final AccessibleSciencesQueryHandler accessibleSciencesQueryHandler;
    private final AuthenticationPlaceholder authentication;
    private final StudentAuthorization studentAuthorization;

    @GetMapping
    public ResponseEntity<?> getAllSciences(@RequestParam ScienceId scienceId){
        UserId actionUserId = authentication.authenticateUser(new JsonWebToken());
        return new GenericRequestHandler<WrapperResult<?>, StudentAuthorization>()
                .authorize(actionUserId,studentAuthorization)
                .handle(()->accessibleSciencesQueryHandler.handle(new AccessibleSciencesQuery(scienceId)));

    }

}
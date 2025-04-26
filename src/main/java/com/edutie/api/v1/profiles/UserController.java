package com.edutie.api.v1.profiles;

import com.edutie.api.common.ApiResult;
import com.edutie.api.common.GenericRequestHandler;
import com.edutie.application.profiles.user.GetUserDetailsQueryHandler;
import com.edutie.application.profiles.user.query.GetUserDetailsQuery;
import com.edutie.application.profiles.user.viewmodels.UserDetails;
import com.edutie.domain.core.administration.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import validation.Error;
import validation.Result;

@RestController
@RequestMapping("/api/v1/profiles/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = """
        Provides operations associated with the user itself. As the user is handled mostly by an identity provider, this controller
        acts as a higher-level wrapper around operations shared in the Edutie context. Despite that user is not a profile itself,
        for the sake of convention it uses the profiles API path.
        """)
public class UserController {
    private final GetUserDetailsQueryHandler getUserDetailsQueryHandler;

    @GetMapping("/details")
    @Operation(description = "Retrieves user details. For instance user's name or email.")
    public ResponseEntity<ApiResult<UserDetails>> getUserDetails(Authentication authentication, @RequestParam(required = false) UserId userId) {
        if (userId != null)
            return ResponseEntity.unprocessableEntity().body(
                    ApiResult.fromResult(Result.failure(new Error("NOT-IMPLEMENTED-422", "Feature is not implemented")))
            );
        return new GenericRequestHandler<UserDetails>()
                .authenticate(authentication)
                .handle(() -> getUserDetailsQueryHandler.handle(
                        new GetUserDetailsQuery().authentication(authentication)
                ));
    }
}

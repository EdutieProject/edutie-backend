package com.edutie.backend.api.v1.profiles;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.profiles.user.UserDetailsQueryHandler;
import com.edutie.backend.application.profiles.user.query.UserDetailsQuery;
import com.edutie.backend.application.profiles.user.viewmodels.UserDetails;
import com.edutie.backend.domain.administration.UserId;
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
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = """
        Provides operations associated with the user itself. As the user is handled mostly by an identity provider, this controller
        acts as a higher-level wrapper around operations shared in the Edutie context.
        """)
public class UserController {
    private final UserDetailsQueryHandler userDetailsQueryHandler;

    @GetMapping("/details")
    @Operation(description = "Retrieves user details. For instance user's name or email.")
    public ResponseEntity<ApiResult<UserDetails>> getUserDetails(Authentication authentication, @RequestParam(required = false) UserId userId) {
        if (userId == null)
            return ResponseEntity.unprocessableEntity().body(
                    ApiResult.fromResult(Result.failure(new Error("NOT-IMPLEMENTED-422", "Feature is not implemented")))
            );
        return new GenericRequestHandler<UserDetails>()
                .authenticate(authentication)
                .handle(() -> userDetailsQueryHandler.handle(
                        new UserDetailsQuery().authentication(authentication)
                ));
    }
}

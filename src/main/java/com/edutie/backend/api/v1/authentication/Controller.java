package com.edutie.backend.api.v1.authentication;

import lombok.RequiredArgsConstructor;
import org.keycloak.representations.JsonWebToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth-test")
@RequiredArgsConstructor
public class Controller {
    @GetMapping("/common-endpoint")
    public String common() {
        return "This endpoint should be accessible without authentication";
    }

    @PostMapping("/token")
    public String token() {
        return new JsonWebToken().toString() + "This endpoint should return a JWT (?)";
    }

    @GetMapping("/authenticated-endpoint")
    public String authenticated() {
        return "This endpoint should NOT be accessible without authentication.";
    }
}

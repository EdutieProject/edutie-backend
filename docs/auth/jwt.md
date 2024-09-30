# Json Web Token

This document shows the structure of a JSON web token provided by current keycloak identity provider.

```json
{
  "exp": 1727638234,
  "iat": 1727637334,
  "jti": "a48b3629-7c08-49cb-9ce7-acae28c4eaf2",
  "iss": "http://localhost:8080/auth/realms/baeldung",
  "aud": "account",
  "sub": "b4ba1e85-c692-4656-9001-653123305c5e",
  "typ": "Bearer",
  "azp": "baeldung-confidential",
  "session_state": "84c2dd94-6fef-4f7d-a717-00f13059fe88",
  "acr": "1",
  "allowed-origins": [
    "https://host.docker.internal:7080",
    "http://localhost:8081",
    "http://localhost:7080",
    "http://host.docker.internal:7080",
    "*",
    "http://127.0.0.1:7080",
    "https://127.0.0.1:7080",
    "https://localhost:7080"
  ],
  "realm_access": {
    "roles": [
      "default-roles",
      "offline_access",
      "uma_authorization"
    ]
  },
  "resource_access": {
    "account": {
      "roles": [
        "manage-account",
        "manage-account-links",
        "view-profile"
      ]
    }
  },
  "scope": "profile email",
  "sid": "84c2dd94-6fef-4f7d-a717-00f13059fe88",
  "email_verified": true,
  "name": "Test User",
  "preferred_username": "user",
  "given_name": "Test",
  "family_name": "User",
  "email": "user@baeldung"
}
```
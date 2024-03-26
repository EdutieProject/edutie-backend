package com.edutie.backend.application.profiles.errors;

import validation.Error;

public class RoleError {
    public static Error roleAlreadyAssigned() {
        return new Error("ROLE-ALREADY-EXISTS", "The role this operation was supposed to assign is already assigned to the user.");
    }
}

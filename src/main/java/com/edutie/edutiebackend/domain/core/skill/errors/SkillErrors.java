package com.edutie.edutiebackend.domain.core.skill.errors;

import com.edutie.edutiebackend.domain.rule.Error;

public class SkillErrors {
    public static Error unhandledTraitError()
    {
        return new Error("UnsupportedTraitProvided", "Provided trait is not handled by this activity");
    }
}

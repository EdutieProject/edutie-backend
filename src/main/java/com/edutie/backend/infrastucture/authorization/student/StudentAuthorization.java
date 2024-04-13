package com.edutie.backend.infrastucture.authorization.student;

import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.infrastucture.authorization.base.AuthorizationBase;

/**
 * StudentAuthorization is exceptional - the id returned is always present.
 */
public interface StudentAuthorization extends AuthorizationBase {
}

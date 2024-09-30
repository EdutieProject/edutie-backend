package com.edutie.backend.application.profiles.user;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.profiles.user.query.UserDetailsQuery;
import com.edutie.backend.application.profiles.user.viewmodels.UserDetails;
import validation.WrapperResult;

public interface UserDetailsQueryHandler extends UseCaseHandler<WrapperResult<UserDetails>, UserDetailsQuery> {
}

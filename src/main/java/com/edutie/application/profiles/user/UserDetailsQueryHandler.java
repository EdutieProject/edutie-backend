package com.edutie.application.profiles.user;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.profiles.user.query.UserDetailsQuery;
import com.edutie.application.profiles.user.viewmodels.UserDetails;
import validation.WrapperResult;

public interface UserDetailsQueryHandler extends UseCaseHandler<WrapperResult<UserDetails>, UserDetailsQuery> {
}

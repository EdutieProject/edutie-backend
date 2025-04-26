package com.edutie.application.profiles.user;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.profiles.user.query.GetUserDetailsQuery;
import com.edutie.application.profiles.user.viewmodels.UserDetails;
import validation.WrapperResult;

public interface GetUserDetailsQueryHandler extends UseCaseHandler<WrapperResult<UserDetails>, GetUserDetailsQuery> {
}

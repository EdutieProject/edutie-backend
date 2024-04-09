package com.edutie.backend.application.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base use case handler designed to be extended by the implementation classes of the certain use cases. This
 * class wraps common functionality such as logging.
 */
public abstract class UseCaseHandlerBase {
    protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
}

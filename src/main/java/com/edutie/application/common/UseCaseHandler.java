package com.edutie.application.common;

/**
 * Base use case handler interface. This interface should be used as a parent interface
 * for use case handler interfaces.
 *
 * @param <TReturn> Return type of the handler
 * @param <TInput>  Input type of the handler. Usually a command or query type.
 */
public interface UseCaseHandler<TReturn, TInput> {
	TReturn handle(TInput input);
}

package validation;

import lombok.*;

/**
 * Exception that can be thrown when the operation result is failure.
 */
@Getter
public class OperationFailureException extends RuntimeException {
	private final Error error;

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *
	 * @param error the detail message. The detail message is saved for
	 *              later retrieval by the {@link #getMessage()} method.
	 */
	public OperationFailureException(Error error) {
		super(error.toString());
		this.error = error;
	}
}

package validation;

/**
 * Exception that can be thrown when the operation result is failure.
 */
public class OperationFailureException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public OperationFailureException(String message) {
        super(message);
    }
}

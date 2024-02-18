package validation;

import lombok.Getter;
import lombok.Value;

/**
 * An error record representing an unfortunate failure of an operation.
 * Code is meant to represent the unique error cause, the message is the
 * description of the error.
 */
public record Error(String code, String message) {
}

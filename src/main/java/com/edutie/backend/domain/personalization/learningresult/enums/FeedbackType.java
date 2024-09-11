package com.edutie.backend.domain.personalization.learningresult.enums;

import com.edutie.backend.domain.common.enums.PersistableEnum;
import com.edutie.backend.infrastucture.persistence.config.AbstractEnumConverter;
import lombok.*;

/**
 * Enum describing general opinion described by feedback.
 * Serves as a one-off alternative for user to know about
 * feedback opinion.
 */
@Getter
public enum FeedbackType implements PersistableEnum<String> {
	POSITIVE("Positive"), NEUTRAL("Neutral"), NEGATIVE("Negative");

	private final String code;

	FeedbackType(String code) {
		this.code = code;
	}

	public static FeedbackType fromString(String code) {
		for (FeedbackType feedbackType: NEUTRAL.getDeclaringClass().getEnumConstants()) {
			if (feedbackType.code.equalsIgnoreCase(code))
				return feedbackType;
		}
		return NEUTRAL;
	}

	public static class Converter extends AbstractEnumConverter<FeedbackType, String> {
		public Converter() {
			super(FeedbackType.class);
		}
	}
}

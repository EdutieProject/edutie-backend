package com.edutie.api.serialization.serializers;

import com.edutie.domain.core.common.base.EntityBase;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.std.*;

import java.io.IOException;

public class IdOnlySerializer<T extends EntityBase<?>> extends StdSerializer<T> {
	public IdOnlySerializer() {
		super((Class<T>) null);
	}

	protected IdOnlySerializer(Class<T> t) {
		super(t);
	}

	@Override
	public void serialize(T entity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("id", entity.getId().identifierValue().toString());
		jsonGenerator.writeEndObject();
	}
}

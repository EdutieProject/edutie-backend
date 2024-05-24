package com.edutie.backend.api.serialization;

import com.edutie.backend.domain.common.base.EntityBase;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class IdOnlySerializer<T extends EntityBase<?>> extends StdSerializer<T> {
    public IdOnlySerializer() {
        this(null);
    }
    protected IdOnlySerializer(Class<T> t) {
        super(t);
    }

    @Override
    public void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", IdInference.inferId(t));
        jsonGenerator.writeEndObject();
    }
}

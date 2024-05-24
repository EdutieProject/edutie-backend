package com.edutie.backend.api.serialization;

import com.edutie.backend.domain.common.base.EntityBase;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;

public class IdOnlySetSerializer<U extends EntityBase<?>, T extends Set<U>> extends StdSerializer<T> {
    public IdOnlySetSerializer() {
        this(null);
    }
    protected IdOnlySetSerializer(Class<T> t) {
        super(t);
    }

    @Override
    public void serialize(T entityBases, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (U entity : entityBases) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", IdInference.inferId(entity));
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}

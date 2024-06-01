package com.edutie.backend.api.serialization.serializers;

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
    public void serialize(T entity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", entity.getId().identifierValue().toString());
        jsonGenerator.writeEndObject();
    }
}

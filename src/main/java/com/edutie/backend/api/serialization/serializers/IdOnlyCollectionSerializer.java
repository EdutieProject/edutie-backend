package com.edutie.backend.api.serialization.serializers;

import com.edutie.backend.domain.common.base.EntityBase;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Collection;

public class IdOnlyCollectionSerializer<U extends EntityBase<?>, T extends Collection<U>> extends StdSerializer<T> {
    public IdOnlyCollectionSerializer() {
        this(null);
    }
    protected IdOnlyCollectionSerializer(Class<T> t) {
        super(t);
    }

    @Override
    public void serialize(T entityBases, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (U entity : entityBases) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", entity.getId().identifierValue().toString());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}

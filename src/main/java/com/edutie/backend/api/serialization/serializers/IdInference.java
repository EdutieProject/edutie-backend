package com.edutie.backend.api.serialization.serializers;

import com.edutie.backend.domain.common.base.EntityBase;

//TODO: rozwiązanie zalepione na taśmę, do naprawy
class IdInference {
    public static <T extends EntityBase<?>> String inferId(T entity) {
        String idRepresentation = entity.getId().toString();
        return entity.getId().toString().substring(idRepresentation.length()-37, idRepresentation.length()-1);
    }
}

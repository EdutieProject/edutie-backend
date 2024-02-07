package com.edutie.backend.application.services.common.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class ServiceRequest {
    LocalDateTime createdOn = LocalDateTime.now(); //Pobiera obecny czas z zegara systemowego użytkownika na którym uruchomi się aplikacja. To ma tak być?
}

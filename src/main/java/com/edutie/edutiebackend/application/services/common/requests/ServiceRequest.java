package com.edutie.edutiebackend.application.services.common.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class ServiceRequest {
    LocalDateTime createdOn = LocalDateTime.now();
}

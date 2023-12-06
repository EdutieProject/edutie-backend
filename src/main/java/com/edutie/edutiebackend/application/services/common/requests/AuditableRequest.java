package com.edutie.edutiebackend.application.services.common.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AuditableRequest {
    LocalDateTime createdOn = LocalDateTime.now();
}

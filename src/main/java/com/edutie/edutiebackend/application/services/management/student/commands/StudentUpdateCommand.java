package com.edutie.edutiebackend.application.services.management.student.commands;

import com.edutie.edutiebackend.application.services.common.requests.AuthenticatedServiceRequest;
import com.edutie.edutiebackend.application.utils.data.nullableproperty.NullableProperty;
import com.edutie.edutiebackend.domain.core.student.valueobjects.SchoolStage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentUpdateCommand extends AuthenticatedServiceRequest {
    public NullableProperty<SchoolStage> schoolStage = new NullableProperty<>();
    public NullableProperty<LocalDate> birthdate = new NullableProperty<>();
}

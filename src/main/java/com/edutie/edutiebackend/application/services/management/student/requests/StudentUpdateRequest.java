package com.edutie.edutiebackend.application.services.management.student.requests;

import com.edutie.edutiebackend.application.services.common.requests.AuthenticatedRequest;
import com.edutie.edutiebackend.application.utils.data.nullableproperty.NullableProperty;
import com.edutie.edutiebackend.domain.core.student.valueobjects.SchoolStage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
//TODO!: Review
public class StudentUpdateRequest extends AuthenticatedRequest {
    public NullableProperty<SchoolStage> schoolStage = new NullableProperty<>();
    public NullableProperty<LocalDate> birthdate = new NullableProperty<>();
}

package com.edutie.infrastructure.common;

public abstract class ExternalServiceDto<TDomainEntity, TExternalServiceRequest> {
    public abstract TDomainEntity intoDomainEntity(TExternalServiceRequest externalServiceRequest);
}

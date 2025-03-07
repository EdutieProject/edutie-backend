package com.edutie.infrastructure.common;

public  interface ExternalServiceDto<TDomainEntity, TExternalServiceRequest> {
     TDomainEntity intoDomainEntity(TExternalServiceRequest externalServiceRequest);
}

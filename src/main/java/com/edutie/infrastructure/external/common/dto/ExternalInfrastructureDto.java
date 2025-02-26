package com.edutie.infrastructure.external.common.dto;

/**
 * A DTO usually defined in the external service incoming to the Edutie backend app.
 *
 * @param <TDomainEntity>   Type of domain entity to be mapped into.
 * @param <TDomainDetailsSchema> Internal schema type. Entity of this type is used to fill in the details of the domain
 *                          entity that are not contained within the DTO
 */
public interface ExternalInfrastructureDto<TDomainEntity, TDomainDetailsSchema> {
    TDomainEntity intoDomainEntity(TDomainDetailsSchema domainDetailsSchema);
}

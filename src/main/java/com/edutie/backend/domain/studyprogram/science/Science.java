package com.edutie.backend.domain.studyprogram.science;

import com.edutie.backend.domain.common.base.EducatorCreatedAuditableEntity;
import com.edutie.backend.domain.education.EducationError;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.enums.EducatorType;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import jakarta.persistence.Entity;
import lombok.*;
import validation.WrapperResult;

/**
 * Science entity - the category that each course is assigned to.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Science extends EducatorCreatedAuditableEntity<ScienceId> {
    private String name;
    private String description;
    private String imageSource;

    /**
     * Recommended constructor associating science with a creator user.
     * Science may be only added by administrators thus it is not related with
     * educators.
     *
     * @param educator educator creating entity
     * @return Science
     */
    public static WrapperResult<Science> create(Educator educator) {
        if (!educator.hasPermissionsOf(EducatorType.ADMINISTRATOR))
            return WrapperResult.failureWrapper(EducationError.educatorInsufficientPermissions());
        Science science = new Science();
        science.setId(new ScienceId());
        science.setAuthorEducator(educator);
        science.setCreatedBy(educator.getOwnerUserId());
        return WrapperResult.successWrapper(science);
    }
}

package com.edutie.backend.domain.studyprogram.science;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import jakarta.persistence.Entity;
import lombok.*;

/**
 * Science entity - the category that each course is assigned to.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Science extends AuditableEntityBase<ScienceId> {
    private String name;
    private String description;

    /**
     * Recommended constructor associating science with a creator - user.
     * Science may be only added by administrators thus it is not related with
     * creators.
     *
     * @param userId user id
     * @return Science
     */
    public static Science create(UserId userId) {
        Science science = new Science();
        science.setId(new ScienceId());
        science.setCreatedBy(userId);
        return science;
    }
}

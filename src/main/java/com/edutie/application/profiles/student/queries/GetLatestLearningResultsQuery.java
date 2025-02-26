package com.edutie.application.profiles.student.queries;

import com.edutie.application.common.actions.StudentAction;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class GetLatestLearningResultsQuery extends StudentAction<GetLatestLearningResultsQuery> {
    private Integer amount;
    private LocalDateTime maxDate;

    @Override
    protected GetLatestLearningResultsQuery getThis() {
        return this;
    }

    /**
     * Note that this implementation requires a rest endpoint function to call the amount setter first.
     *
     * @param maxDate max Date
     * @return this query
     */
    public GetLatestLearningResultsQuery maxDate(LocalDateTime maxDate) {
        this.maxDate = maxDate;
        if (this.maxDate == null && this.amount == null)
            this.amount = 5;
        return getThis();
    }
}

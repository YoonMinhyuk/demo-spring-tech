package me.demo.reactive.domain.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;
import java.util.Optional;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public abstract class DomainDateTimeAudit {
    @CreatedDate
    private ZonedDateTime createdDateTime;

    @LastModifiedDate
    private ZonedDateTime lastModifiedDateTime;

    public DomainDateTimeAudit(final ZonedDateTime createdDateTime) {
        setCreatedDateTime(createdDateTime);
        setLastModifiedDateTime(createdDateTime);
    }

    private void setCreatedDateTime(final ZonedDateTime createdDateTime) {
        this.createdDateTime = Optional.ofNullable(createdDateTime)
                                       .orElseThrow(() ->
                                               new IllegalArgumentException("createdDateTime cannot be null")
                                       );
    }

    private void setLastModifiedDateTime(final ZonedDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = Optional.ofNullable(lastModifiedDateTime)
                                            .orElseThrow(() ->
                                                    new IllegalArgumentException("lastModifiedDateTime cannot be null")
                                            );
    }
}

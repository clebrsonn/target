package br.com.hyteck.platform.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AbstractEntity<U> {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(length = 60, unique = true, nullable = false)
    protected UUID id;

    @Version
    private Long version;

    @CreatedBy
    private U createdBy;

    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    private U lastModifiedBy;

    @LastModifiedDate
    private Date lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return getId().equals(that.getId()) &&
                getVersion().equals(that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVersion());
    }
}

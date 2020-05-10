package br.com.hyteck.platform.entity;

import br.com.hyteck.platform.entity.discounts.Coupon;
import br.com.hyteck.platform.framework.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema
public class Partner extends AbstractEntity<String> {

    @ManyToMany(mappedBy = "affiliates", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Schema
    @JsonIgnore
    @JsonManagedReference
    @Builder.Default
    private Set<Coupon> coupons =new  HashSet<>();

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partner)) return false;
        if (!super.equals(o)) return false;
        Partner partner = (Partner) o;
        return getName().equals(partner.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }
}

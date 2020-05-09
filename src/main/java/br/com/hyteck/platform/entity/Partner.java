package br.com.hyteck.platform.entity;

import br.com.hyteck.platform.entity.discounts.Coupon;
import br.com.hyteck.platform.frw.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
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
    private Set<Coupon> coupons;

    private String name;

}

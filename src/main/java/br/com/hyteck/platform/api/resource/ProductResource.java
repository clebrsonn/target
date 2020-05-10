//package br.com.hyteck.platform.api.resource;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import org.springframework.hateoas.RepresentationModel;
//import org.springframework.hateoas.server.core.Relation;
//
//import java.math.BigDecimal;
//
//
//@Data
//@EqualsAndHashCode(callSuper = true)
//@Relation(value = "product", collectionRelation = "products")
//public class ProductResource extends RepresentationModel<ProductResource> {
//
//    @Schema
//    private Long id;
//
//    @Schema
//    private BigDecimal price;
//
//    @Schema
//    private String description;
//
//    @Schema
//    private String name;
//
//}

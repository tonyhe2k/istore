package xyz.tonyhe.catalogservice.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Document
@Data
@Builder
public class Product {

    @Id
    private long id;

    @NotNull
    private String name;

    private String description;

    private BigDecimal price;

    private int totalInventory;


}

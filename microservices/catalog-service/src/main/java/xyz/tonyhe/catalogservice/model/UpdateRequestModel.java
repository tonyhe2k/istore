package xyz.tonyhe.catalogservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateRequestModel {

    @NotNull
    private Long productId;
    @NotNull
    private Integer quantity;

}

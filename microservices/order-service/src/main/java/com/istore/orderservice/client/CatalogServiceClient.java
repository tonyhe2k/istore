package com.istore.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name="catalog-service")
public interface CatalogServiceClient {

    @PostMapping("/inventory/decrease")
    public ResponseEntity<String> updateInventory(@RequestBody @Valid List<UpdateRequestModel> model);

}

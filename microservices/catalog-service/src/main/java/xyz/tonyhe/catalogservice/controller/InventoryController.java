package xyz.tonyhe.catalogservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.tonyhe.catalogservice.domain.Product;
import xyz.tonyhe.catalogservice.model.UpdateRequestModel;
import xyz.tonyhe.catalogservice.repository.ProductRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/decrease")
    @Transactional
    public ResponseEntity<String> updateInventory(@RequestBody @Valid List<UpdateRequestModel> models) {

        log.info("CatalogService: entering updateInventory : " + models.toString());

        models.stream().forEach(
                model -> {
                    // get product by id, if not found, throw exception
                    Optional<Product> result = productRepository.findById(model.getProductId());
                    result.orElseThrow(() -> new ProductNotFoundException("No Product found with id " + model.getProductId()));

                    // increase or decrease inventory
                    Product product = result.get();
                    int total = product.getTotalInventory();
                    total = total - model.getQuantity();
                    product.setTotalInventory(total);

                    // persist to db
                    productRepository.save(product);
                }
        );


        return ResponseEntity.ok().body("Product created");
    }
}

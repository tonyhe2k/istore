package xyz.tonyhe.catalogservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.tonyhe.catalogservice.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}

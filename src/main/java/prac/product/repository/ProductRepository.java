package prac.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prac.product.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

package ite_3rd_ecommerce.co.stad.project.repository;

import ite_3rd_ecommerce.co.stad.project.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Integer> {
}

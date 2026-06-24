package ite_3rd_ecommerce.co.stad.project.feature.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Integer> {


    Boolean existsByName(String name);
}

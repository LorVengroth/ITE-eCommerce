package ite_3rd_ecommerce.co.stad.project.feature.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product , Integer> {


    Boolean existsByName(String name);
    Optional<Product> findByCode(String code);
}

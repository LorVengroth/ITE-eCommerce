package ite_3rd_ecommerce.co.stad.project.repository;

import ite_3rd_ecommerce.co.stad.project.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , Integer> {
}

package ite_3rd_ecommerce.co.stad.project.feature.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category , Integer> {

    Boolean existsByName(String name);
    List<Category> findByParentCategoryId(Integer parentId);

}

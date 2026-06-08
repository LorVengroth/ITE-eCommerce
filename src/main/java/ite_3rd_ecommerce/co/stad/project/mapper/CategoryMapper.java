package ite_3rd_ecommerce.co.stad.project.mapper;

import ite_3rd_ecommerce.co.stad.project.domain.Category;
import ite_3rd_ecommerce.co.stad.project.dto.CategoryResponse;
import ite_3rd_ecommerce.co.stad.project.dto.CategorySubAndParentCategoryResponse;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  @Mapping(target = "parentCategory.parentCategory", ignore = true)
  CategoryResponse mapCategoryToCategoryResponse(Category category);

  @BeforeMapping
  default void breakCycle(Category target) {
    if (target != null && target.getSubCategories() != null) {
      for (Category child : target.getSubCategories()) {
        child.setParentCategory(null);
      }
    }
  }


  CategorySubAndParentCategoryResponse mapCategoryToCategorySubAndParentCategoryResponse(Category category);
}

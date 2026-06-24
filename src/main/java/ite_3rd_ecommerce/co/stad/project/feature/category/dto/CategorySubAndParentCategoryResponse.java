package ite_3rd_ecommerce.co.stad.project.feature.category.dto;

import java.util.List;

public record CategorySubAndParentCategoryResponse(
        Integer id ,
        String name ,
        String description ,
        String icon ,
        Boolean isDeleted ,
        List<CategorySubAndParentCategoryResponse> subCategories
) {
}

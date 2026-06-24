package ite_3rd_ecommerce.co.stad.project.feature.category.dto;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Integer id ,
        String name ,
        String description ,
        String icon ,
        Boolean isDeleted ,
        CategoryResponse parentCategory 
) {
}

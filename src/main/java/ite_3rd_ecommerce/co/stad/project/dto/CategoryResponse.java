package ite_3rd_ecommerce.co.stad.project.dto;

import lombok.Builder;

import java.util.List;

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

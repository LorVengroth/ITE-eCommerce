package ite_3rd_ecommerce.co.stad.project.feature.product.dto;

import ite_3rd_ecommerce.co.stad.project.feature.category.dto.CategorySnippetResponse;

import java.math.BigDecimal;

public record ProductResponse(
        String code,
        String slug,
        String name,
        String description,
        String thumbnail,
        BigDecimal unitPrice,
        Integer qty,
        Boolean isAvailable,
        Boolean isDeleted,
        CategorySnippetResponse category
) {
}

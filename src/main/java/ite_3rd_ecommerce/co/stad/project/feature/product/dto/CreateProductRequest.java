package ite_3rd_ecommerce.co.stad.project.feature.product.dto;

import ite_3rd_ecommerce.co.stad.project.feature.category.dto.CategorySnippetResponse;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 255)
        String name, // Mouse Logitech S4 -> mouse-logitech-s4
        @Size(max = 500)
        String description,
        @Size(max = 255)
        String thumbnail,
        @NotNull(message = "Unit price is required")
        @Min(0)
        BigDecimal unitPrice,
        @NotNull(message = "QTY is required")
        @Min(0)
        Integer qty,
        @NotNull(message = "Category ID is required")
        @Positive
        Integer categoryId
) {
}

package ite_3rd_ecommerce.co.stad.project.feature.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderLineDto(
        @NotBlank(message = "code is required")
        String code ,
        @Positive
        @NotNull(message = "qty is required")
        Integer qty ,
        @Positive
        @NotNull(message = "unitPrice is required")
        BigDecimal unitPrice
) {
}

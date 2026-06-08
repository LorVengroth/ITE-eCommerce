package ite_3rd_ecommerce.co.stad.project.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record CreateCategoryRequest(
        @NotBlank(message = "name is required")
        @Column(nullable = false , unique = true , length = 50)
        String name ,
        String description ,
        @Size(max = 255)
        String icon ,
        Boolean isDeleted ,
        Integer parentCategoryId
) {
}

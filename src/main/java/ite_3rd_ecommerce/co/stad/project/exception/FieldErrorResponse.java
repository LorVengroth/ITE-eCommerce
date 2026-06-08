package ite_3rd_ecommerce.co.stad.project.exception;

import lombok.Builder;

@Builder
public record FieldErrorResponse(
        String field ,
        Integer code ,
        String message

){
}

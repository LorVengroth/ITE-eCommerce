package ite_3rd_ecommerce.co.stad.project.exception;

import lombok.Builder;

@Builder
public record ErrorResponse<T>(
        Boolean status ,
        Integer code ,
        String message ,
        T error

) {
}

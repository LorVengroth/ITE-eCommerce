package ite_3rd_ecommerce.co.stad.project.feature.file.dto;

import lombok.Builder;

@Builder
public record FileUploadResponse(
        String name ,
        String caption ,
        Long size ,
        String mediaType ,
        String uri
) {
}

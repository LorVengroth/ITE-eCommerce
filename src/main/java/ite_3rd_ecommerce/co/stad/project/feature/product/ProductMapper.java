package ite_3rd_ecommerce.co.stad.project.feature.product;

import ite_3rd_ecommerce.co.stad.project.feature.product.dto.CreateProductRequest;
import ite_3rd_ecommerce.co.stad.project.feature.product.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product mapProductCreateRequestToProduct(CreateProductRequest createProductRequest);

    ProductResponse mapProductToProductResponse(Product product);


}

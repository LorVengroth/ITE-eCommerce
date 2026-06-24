package ite_3rd_ecommerce.co.stad.project.feature.product.service;

import ite_3rd_ecommerce.co.stad.project.feature.product.dto.CreateProductRequest;
import ite_3rd_ecommerce.co.stad.project.feature.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    /**
     * Create new product
      * @param createProductRequest is requesting data for creating product
     * @return {@link ProductResponse}
     * @author lor_veng_roth
     * @since 6-23-2026
     */
ProductResponse createNewProduct(CreateProductRequest createProductRequest);


    /**
     * find all product
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<ProductResponse> findAll(int pageNumber , int pageSize);

}

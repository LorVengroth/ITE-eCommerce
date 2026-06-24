package ite_3rd_ecommerce.co.stad.project.feature.product;


import ite_3rd_ecommerce.co.stad.project.feature.product.dto.CreateProductRequest;
import ite_3rd_ecommerce.co.stad.project.feature.product.dto.ProductResponse;
import ite_3rd_ecommerce.co.stad.project.feature.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {



    private final ProductService productService;


    @GetMapping
    public Page<ProductResponse> findAll(
            @RequestParam(required = false , defaultValue = "0") int pageNumber,
            @RequestParam(required = false , defaultValue = "25") int pageSize
    ){
        return productService.findAll(pageNumber , pageSize);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse createNew(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return productService.createNewProduct(createProductRequest);
    }

}

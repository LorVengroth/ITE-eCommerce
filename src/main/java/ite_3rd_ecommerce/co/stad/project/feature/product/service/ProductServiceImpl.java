package ite_3rd_ecommerce.co.stad.project.feature.product.service;

import ite_3rd_ecommerce.co.stad.project.feature.category.Category;
import ite_3rd_ecommerce.co.stad.project.feature.category.CategoryRepository;
import ite_3rd_ecommerce.co.stad.project.feature.product.Product;
import ite_3rd_ecommerce.co.stad.project.feature.product.ProductMapper;
import ite_3rd_ecommerce.co.stad.project.feature.product.ProductRepository;
import ite_3rd_ecommerce.co.stad.project.feature.product.dto.CreateProductRequest;
import ite_3rd_ecommerce.co.stad.project.feature.product.dto.ProductResponse;
import ite_3rd_ecommerce.co.stad.project.util.GenerateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository ;
    private final ProductMapper productMapper ;


    @Override
    public ProductResponse createNewProduct(CreateProductRequest createProductRequest) {

        // Validate product name
        if (productRepository.existsByName(createProductRequest.name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Product name has already been used");
        }

        // Validate category ID
        Category category = categoryRepository
                .findById(createProductRequest.categoryId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Category has not been found"));

        // Transfer data from DTO to Model
        Product product = productMapper
                .mapProductCreateRequestToProduct(createProductRequest);

        // Set generated system data
        product.setCategory(category);
        product.setCode(GenerateUtils.generateProductCode()); // ITE-3RD-1234
        product.setSlug(GenerateUtils.generateSlug(createProductRequest.name()));
        product.setIsAvailable(true);
        product.setIsDeleted(false);

        product = productRepository.save(product);

        return productMapper.mapProductToProductResponse(product);
    }


    @Override
    public Page<ProductResponse> findAll(int pageNumber, int pageSize) {

        Sort sortById = Sort.by(Sort.Direction.DESC , "id");
        PageRequest pageRequest = PageRequest.of(pageNumber , pageSize);


        Page<Product> products = productRepository
                .findAll(pageRequest);


        return products.map(productMapper::mapProductToProductResponse);
    }
}

package ite_3rd_ecommerce.co.stad.project.feature.category.service;

import ite_3rd_ecommerce.co.stad.project.feature.category.Category;
import ite_3rd_ecommerce.co.stad.project.feature.category.dto.CategoryResponse;
import ite_3rd_ecommerce.co.stad.project.feature.category.dto.CategorySubAndParentCategoryResponse;
import ite_3rd_ecommerce.co.stad.project.feature.category.dto.CreateCategoryRequest;
import ite_3rd_ecommerce.co.stad.project.feature.category.CategoryMapper;
import ite_3rd_ecommerce.co.stad.project.feature.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

//    public CategoryServiceImpl (CategoryRepository categoryRepository){
//        this.categoryRepository = categoryRepository ;
//    }

    @Override
    public CategoryResponse createNew(CreateCategoryRequest categoryRequest) {
        log.info("create new {}", categoryRequest);

        // validation category name
        boolean isExistsByName = categoryRepository.existsByName(categoryRequest.name());

        if (isExistsByName) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Category has already been used"
            );
        }


        Category parentCategory = null;


        if (categoryRequest.parentCategoryId()!= null) {

            parentCategory = categoryRepository.findById(categoryRequest.parentCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Category has already been used"));
        }

        Category category = new Category();
//        category.setId(categoryRequest.parentCategotyId());
        category.setName(categoryRequest.name());
        category.setDescription(categoryRequest.description());
        category.setIcon(categoryRequest.icon());
        category.setIsDeleted(categoryRequest.isDeleted());
        category.setParentCategory(parentCategory);

        category = categoryRepository.save(category);


        return categoryMapper.mapCategoryToCategoryResponse(category);
    }


    @Override
    public List<CategorySubAndParentCategoryResponse> getAllCategory(int page , int size ) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<CategorySubAndParentCategoryResponse> categoryResponseList = categoryPage.getContent().stream()
                .map(categoryMapper::mapCategoryToCategorySubAndParentCategoryResponse)
                .toList();

        return  categoryResponseList;
    }


    @Override
    public CategoryResponse getCategoryById(Integer id) {
        Category foundCategory = categoryRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND , "Category not found"));

        CategoryResponse categoryResponse = categoryMapper.mapCategoryToCategoryResponse(foundCategory);



        return  categoryResponse;
    }


    @Override
    public CategoryResponse softDeleteCategoryById(Integer id) {
        Category softDeleteCategory = categoryRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Categoty not found"));

        softDeleteCategory.setIsDeleted(true);
        softDeleteCategory = categoryRepository.save(softDeleteCategory);

        return categoryMapper.mapCategoryToCategoryResponse(softDeleteCategory);
    }

    @Override
    public CategoryResponse updateCategoryById(CreateCategoryRequest categoryRequest, Integer id) {

        Category foundCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        if (categoryRequest.name() != null) {
            foundCategory.setName(categoryRequest.name());
        }

        if (categoryRequest.icon() != null) {
            foundCategory.setIcon(categoryRequest.icon());
        }

        if (categoryRequest.description() != null) {
            foundCategory.setDescription(categoryRequest.description());
        }

        if (categoryRequest.isDeleted() != null) {
            foundCategory.setIsDeleted(categoryRequest.isDeleted());
        }


        if (categoryRequest.parentCategoryId() != null) {

            Category parentCategory = categoryRepository.findById(categoryRequest.parentCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parent Category not found"));
            foundCategory.setParentCategory(parentCategory);
        }

        foundCategory = categoryRepository.save(foundCategory);

        CategoryResponse updatedCategory = categoryMapper.mapCategoryToCategoryResponse(foundCategory);

        return updatedCategory;
    }

    @Override
    public void hardDeleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }


    @Override
    public List<CategorySubAndParentCategoryResponse> getSubCategoryByParentCategoryId(Integer id) {

        if (!categoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parent Category not found with ID: " + id);
        }


        List<Category> subCategories = categoryRepository.findByParentCategoryId(id);


        return subCategories.stream()
                .map(categoryMapper::mapCategoryToCategorySubAndParentCategoryResponse)
                .toList();
    }
}

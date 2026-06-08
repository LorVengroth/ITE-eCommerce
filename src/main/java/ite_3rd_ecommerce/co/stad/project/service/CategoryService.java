package ite_3rd_ecommerce.co.stad.project.service;

import ite_3rd_ecommerce.co.stad.project.dto.*;

import java.util.List;

public interface CategoryService {

    CategoryResponse createNew(CreateCategoryRequest categoryRequest);
    List<CategorySubAndParentCategoryResponse> getAllCategory(int page , int size );
    CategoryResponse getCategoryById(Integer id);
    CategoryResponse softDeleteCategoryById(Integer id);
    CategoryResponse updateCategoryById(CreateCategoryRequest categoryRequest , Integer id);
    void hardDeleteCategoryById(Integer id);
    List<CategorySubAndParentCategoryResponse> getSubCategoryByParentCategoryId(Integer id);
}

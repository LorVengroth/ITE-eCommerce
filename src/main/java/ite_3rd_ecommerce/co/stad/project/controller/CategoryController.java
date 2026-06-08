package ite_3rd_ecommerce.co.stad.project.controller;

import ite_3rd_ecommerce.co.stad.project.dto.*;
import ite_3rd_ecommerce.co.stad.project.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    //TODO

    private final CategoryService categoryService ;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createNew(@Valid @RequestBody CreateCategoryRequest categoryRequest){
        return categoryService.createNew(categoryRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CategorySubAndParentCategoryResponse> getAllCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ){
        return categoryService.getAllCategory(page , size);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CategoryResponse findCategoryById(@PathVariable Integer id ){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse softDeleteCategoryById(@PathVariable Integer id){
        return categoryService.softDeleteCategoryById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse updateCategoryById(@Valid @RequestBody  CreateCategoryRequest categoryRequest , @PathVariable Integer id){
        return categoryService.updateCategoryById(categoryRequest , id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void hardDeleteCategoryById(Integer id){
        categoryService.hardDeleteCategoryById(id);
    }

    @GetMapping("/{id}/subCategory")
    @ResponseStatus(HttpStatus.FOUND)
    public List<CategorySubAndParentCategoryResponse> getSubCategoryByParentCategoryId(@PathVariable Integer id){
        return categoryService.getSubCategoryByParentCategoryId(id);
    }

}

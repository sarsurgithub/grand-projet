package io.swagger.api.endpoints;

import io.swagger.articles.api.CategoriesApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.articles.api.model.*;
import io.swagger.entities.CategoryEntity;
import io.swagger.repositories.ArticleRepository;
import io.swagger.repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-08T14:21:38.963Z[GMT]")
@Controller
public class CategoriesApiController implements CategoriesApi {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ArticleRepository articleRepository;

    private static final Logger log = LoggerFactory.getLogger(CategoriesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CategoriesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addCategory(@ApiParam(value = "Category that needs to be added" ,required=true )  @Valid @RequestBody CreateCategory createCategory) {
        CategoryEntity categoryEntity = fromCreateCategoryToCategoryEntity(createCategory);
        categoryRepository.save(categoryEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoryEntity.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    public ResponseEntity<Void> deleteACategory(@ApiParam(value = "category to delete",required=true) @PathVariable("categoryId") Long categoryId) {
         categoryRepository.deleteById(categoryId);
         return ResponseEntity.ok().build();

    }

    public ResponseEntity<List<GetCategory>> findCategories() {
        List<GetCategory> categories = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryRepository.findAll()) {
            categories.add(toGetCategory(categoryEntity));
        }

        return ResponseEntity.ok(categories);

    }


    public ResponseEntity<GetCategory> findCategoryById(@ApiParam(value = "category to find",required=true) @PathVariable("categoryId") Long categoryId) {

        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("This category does not exist"));
        GetCategory category = toGetCategory(categoryEntity);

        return ResponseEntity.ok(category);

    }

    public ResponseEntity<Void> updateCategoryById(@ApiParam(value = "category to update",required=true) @PathVariable("categoryId") Long categoryId,@ApiParam(value = "updated category" ,required=true )  @Valid @RequestBody CreateCategory createCategory) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("This category does not exist"));

        categoryEntity = updateCategory(createCategory, categoryEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri();

        categoryRepository.save(categoryEntity);

        return ResponseEntity.created(location).build();

    }

    private GetCategory toGetCategory (CategoryEntity entity) {
        GetCategory category = new GetCategory();
        category.setId(entity.getId());
        category.setName(entity.getName());
        return category ;
    }

    private CategoryEntity fromCreateCategoryToCategoryEntity( CreateCategory category){
        CategoryEntity entity = new CategoryEntity();
        entity.setName(category.getName());

        return entity;
    }

    private CategoryEntity updateCategory(CreateCategory category, CategoryEntity entity) {
        entity.setName(category.getName());
        return entity;
    }

}

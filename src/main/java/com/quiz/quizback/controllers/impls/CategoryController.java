package com.quiz.quizback.controllers.impls;

import com.quiz.quizback.config.exceptions.CustomException;
import com.quiz.quizback.controllers.specs.ICategoryController;
import com.quiz.quizback.domain.entities.Category;
import com.quiz.quizback.services.specs.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController implements ICategoryController {
    private final ICategoryService categoryService;
    @Override
    public ResponseEntity<Category> addCategory(Category category) {
      return new ResponseEntity<>(this.categoryService.addCategory(category), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Category> updateCategory(String categoryId, Category category) {

        try {
            return new ResponseEntity<>(this.categoryService.updateCategory(categoryId,category), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories() {

        return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> getCategoryById(String categoryId) throws CustomException {
        try {
            Category category = this.categoryService.getCategoryById(categoryId);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") String categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

package com.quiz.quizback.controllers.specs;

import com.quiz.quizback.domain.entities.Category;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
public interface ICategoryController {
    @PostMapping("")
    ResponseEntity<Category> addCategory(@RequestBody Category category);

    @PutMapping("/{categoryId}")
    ResponseEntity<Category> updateCategory(@PathVariable("categoryId") String categoryId,@RequestBody @Valid Category category);

    @GetMapping("/all")
    ResponseEntity<List<Category>> getAllCategories();

    @GetMapping("/{categoryId}")
    ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") String categoryId);
    @DeleteMapping("/{categoryId}")
    ResponseEntity<Void>deleteCategory(@PathVariable("categoryId") String categoryId);
}

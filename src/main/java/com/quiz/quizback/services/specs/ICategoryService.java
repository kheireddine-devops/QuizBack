package com.quiz.quizback.services.specs;

import com.quiz.quizback.config.exceptions.CustomException;
import com.quiz.quizback.domain.entities.Category;

import java.util.List;

public interface ICategoryService {
     Category getCategoryById(String categoryId) throws CustomException ;
     List<Category> getAllCategories();
     Category updateCategory(String id, Category category) throws CustomException;
     Category addCategory(Category category);
     void deleteCategory(String id) throws CustomException;


}



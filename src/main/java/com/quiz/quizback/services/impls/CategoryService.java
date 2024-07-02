package com.quiz.quizback.services.impls;

import com.quiz.quizback.config.exceptions.CustomException;
import com.quiz.quizback.domain.entities.Category;
import com.quiz.quizback.repositories.ICategoryRepository;
import com.quiz.quizback.services.specs.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    @Override
    public Category updateCategory(String id, Category category) throws CustomException {
        Optional<Category> optionalCategory=categoryRepository.findById(id);
        if(optionalCategory.isEmpty())
        {
            throw new CustomException("pas de category avec cet Id");

        }
        Category categoryUpdate =optionalCategory.get();
        if(category.getName()!=null){
            categoryUpdate.setName(category.getName());
        }
        if(category.getQuiz()!=null){
            categoryUpdate.setQuiz(category.getQuiz());
        }
        return categoryRepository.save(categoryUpdate);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(String categoryId) throws CustomException {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new CustomException("Category not found with ID: " + categoryId);
        }
         categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    @Override
    public Category getCategoryById(String categoryId) throws CustomException {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new CustomException("Category not found with ID: " + categoryId);
        }
        return category;
    }

}

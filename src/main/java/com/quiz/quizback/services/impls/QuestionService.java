package com.quiz.quizback.services.impls;

import com.quiz.quizback.config.exceptions.CustomException;
import com.quiz.quizback.domain.entities.Category;
import com.quiz.quizback.domain.entities.Question;
import com.quiz.quizback.repositories.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final ICategoryRepository categoryRepository;
    public void addQuestion(Question question){
        Optional<Category> optionalCategory=categoryRepository.findById(question.getIdCategory());
        if(optionalCategory.isEmpty())
        {
            throw new CustomException("Category not found with ID: " + question.getIdCategory());
        }
        optionalCategory.get().getQuiz().add(question);
        categoryRepository.save(optionalCategory.get());
    }
    public void updateQuestion(String questionId, Question question){
        Optional<Category> optionalCategory=categoryRepository.findById(question.getIdCategory());
        if(optionalCategory.isEmpty())
        {
            throw new CustomException("Category not found with ID: " + question.getIdCategory());
        }
        Optional<Question> optionalQuestion =  optionalCategory.get().getQuiz().stream().filter(question1 -> question1.getId().equals(questionId)).findFirst();
        if(optionalQuestion.isEmpty())
        {
            throw new CustomException("Question not found with ID: " + questionId);
        }
        Question questionUpdate =optionalQuestion.get();
        if(questionUpdate.getDescription()!=null){
           questionUpdate.setDescription(question.getDescription());
        }
        if(questionUpdate.getIdCategory()!=null){
            questionUpdate.setIdCategory(question.getIdCategory());
        }
        if(questionUpdate.getScore()>0){
            questionUpdate.setScore(question.getScore());
        }
        if(questionUpdate.getOptions()!=null){
            questionUpdate.setOptions(question.getOptions());
        }
        optionalCategory.get().getQuiz().set(optionalCategory.get().getQuiz().indexOf(optionalQuestion.get()),questionUpdate);

        categoryRepository.save(optionalCategory.get());
    }
    public void deleteQuestion(String categoryId,  String questionId){
        Optional<Category> optionalCategory=categoryRepository.findById(categoryId);
        if(optionalCategory.isEmpty())
        {
            throw new CustomException("Category not found with ID: " + categoryId);
        }
        Optional<Question> optionalQuestion =  optionalCategory.get().getQuiz().stream().filter(question1 -> question1.getId().equals(questionId)).findFirst();
        if(optionalQuestion.isEmpty())
        {
            throw new CustomException("Question not found with ID: " + questionId);
        }

        Question questionToDelete = optionalQuestion.get();
        optionalCategory.get().getQuiz().remove(questionToDelete);

        categoryRepository.save(optionalCategory.get());
    }
}

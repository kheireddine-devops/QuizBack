package com.quiz.quizback.services.impls;

import com.quiz.quizback.config.exceptions.CustomException;
import com.quiz.quizback.domain.entities.Category;
import com.quiz.quizback.domain.entities.Result;
import com.quiz.quizback.repositories.ICategoryRepository;
import com.quiz.quizback.repositories.IResultRepository;
import com.quiz.quizback.repositories.IUserRepository;
import com.quiz.quizback.services.specs.ICategoryService;
import com.quiz.quizback.services.specs.IResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultService implements IResultService {
    private final IResultRepository resultRepository;
    private final ICategoryRepository categoryRepository;
    private final IUserRepository userRepository;
    @Override
    public boolean checkTestTaker(String resultId,String userId) {
      return resultRepository.findByUserId(userId).isPresent();
    }

    @Override
    public Result addResult(Result result) {
        if(!userRepository.existsById(result.getUserId())){
            throw new CustomException("User not found with ID: " + result.getUserId());
        }
        if(!categoryRepository.existsById(result.getCategoryId())){
            throw new CustomException("Category not found with ID: " + result.getCategoryId());
        }
        return resultRepository.save(result);


    }

    @Override
    public void deleteResult(String resultId) {
        Result result = resultRepository.findById(resultId).orElse(null);
        if (result == null) {
            throw new CustomException("Category not found with ID: " + resultId);
        }
        resultRepository.deleteById(resultId);

    }

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }
}

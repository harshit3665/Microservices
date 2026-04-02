package com.example.quiz_service.service;

import com.example.quiz_service.payload.QuestionWrapper;
import com.example.quiz_service.payload.QuizResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    String createQuiz(String category, int numQ, String title);
    List<QuestionWrapper> getQuizQuestion(Integer id);
    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> responses);
}

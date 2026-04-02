package com.example.question_service.service;

import com.example.question_service.model.Question;
import com.example.question_service.payload.QuestionWrapper;
import com.example.question_service.payload.QuizResponse;


import java.util.List;


public interface QuestionService {

    List<Question> getAllQuestion();

    List<Question> getQuestionByCategory( String categoryName);

    Question addQuestion(Question question);
List<Integer> getQuestionForQuiz(String categoryName, int  namQ);
List<QuestionWrapper> getQuestionFromId(List<Integer> questionIds);
Integer calculateScore(List<QuizResponse> responses);}

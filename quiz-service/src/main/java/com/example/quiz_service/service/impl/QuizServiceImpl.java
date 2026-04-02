package com.example.quiz_service.service.impl;

import com.example.quiz_service.feign.QuizInterface;
import com.example.quiz_service.model.Question;
import com.example.quiz_service.model.Quiz;
import com.example.quiz_service.payload.QuestionWrapper;
import com.example.quiz_service.payload.QuizResponse;

import com.example.quiz_service.repository.QuizRepository;
import com.example.quiz_service.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizInterface quizInterface;

    public String createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.getQuestionForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);

        quizRepository.save(quiz);

        return "Quiz created successfully";
    }

    @Override
    public List<QuestionWrapper> getQuizQuestion(Integer id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        List<Integer> questionIds = quiz.getQuestionIds();
        return quizInterface.getQuestionsFromId(questionIds).getBody();
    }

    @Override
    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
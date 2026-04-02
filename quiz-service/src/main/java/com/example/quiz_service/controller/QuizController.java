package com.example.quiz_service.controller;

import com.example.quiz_service.payload.QuestionWrapper;
import com.example.quiz_service.payload.QuizDto;
import com.example.quiz_service.payload.QuizResponse;
import com.example.quiz_service.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz")
@AllArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {

        String response = quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestion(),quizDto.getTitle());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQUizQuestion(@PathVariable Integer id) {
        return ResponseEntity.ok(quizService.getQuizQuestion(id));
    }
    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(
            @PathVariable Integer quizId,
            @RequestBody List<QuizResponse> quizResponses) {

        ResponseEntity<Integer> score = quizService.calculateResult(quizId, quizResponses);
        return score;
    }
}

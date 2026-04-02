package com.example.question_service.controller;


import com.example.question_service.model.Question;
import com.example.question_service.payload.QuestionWrapper;
import com.example.question_service.payload.QuizResponse;
import com.example.question_service.service.QuestionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question")
@AllArgsConstructor
@Transactional
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/all-question")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return ResponseEntity.ok(questionService.getAllQuestion());
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String categoryName) {
        return ResponseEntity.ok(questionService.getQuestionByCategory(categoryName));
    }

    @PostMapping("/add-question")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.addQuestion(question);
        return ResponseEntity.status(201).body(savedQuestion);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName, @RequestParam int numQ) {
        return ResponseEntity.ok(questionService.getQuestionForQuiz(categoryName, numQ));
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        return ResponseEntity.ok(questionService.getQuestionFromId(questionIds));
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> responses) {
        return ResponseEntity.ok(questionService.calculateScore(responses));
    }

}

package com.example.question_service.service.impl;

import com.example.question_service.model.Question;
import com.example.question_service.payload.QuestionWrapper;
import com.example.question_service.payload.QuizResponse;
import com.example.question_service.repository.QuestionRepository;
import com.example.question_service.service.QuestionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class QuestionServiceImpl  implements QuestionService {
    private final QuestionRepository questionRepository;
    @Override
    public List<Question> getAllQuestion() {
        log.info("all question fetch successfully");
        return  questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionByCategory(String categoryName) {
        log.info("question fetch according to category");
        return  questionRepository.findQuestionsByCategory(categoryName);
    }

    @Override
    public Question addQuestion(Question question) {
        return  questionRepository.save(question);
    }

    @Override
    public List<Integer> getQuestionForQuiz(String categoryName, int numQ) {
        List<Integer> questions =questionRepository.findRandomQuestionsByCategory(categoryName,numQ);
        return  questions;
    }

    @Override
    public List<QuestionWrapper> getQuestionFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers=new ArrayList<>();
        List<Question> questions=new ArrayList<>();
        for(Integer id:questionIds){
            questions.add(questionRepository.findById(id).get());
        }
        for (Question question : questions){
            QuestionWrapper wrapper=new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestion_title(question.getQuestion_title());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);

        }
        return  wrappers;
    }
    @Override
    public Integer calculateScore(List<QuizResponse> responses) {

        int score = 0;

        for (QuizResponse response : responses) {

            Optional<Question> questionOptional = questionRepository.findById(response.getId());

            if (questionOptional.isPresent()) {
                Question question = questionOptional.get();

                if (question.getRight_answer() != null &&
                        response.getResponse() != null &&
                        question.getRight_answer().trim()
                                .equalsIgnoreCase(response.getResponse().trim())) {

                    score++;
                }
            }
        }

        return score;
    }
}

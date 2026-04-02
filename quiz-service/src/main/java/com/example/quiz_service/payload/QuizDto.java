package com.example.quiz_service.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizDto {
    String categoryName;
    Integer numQuestion;
    String title;
}

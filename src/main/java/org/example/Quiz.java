package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz {
    List<Question> Questions =  new ArrayList<>();
    Map<Question, Boolean> userAnswers = new HashMap<>();

    public void addQuestion(Question question){
        Questions.add(question);
    }

    public void recordAnswer(Question question, int chosenOption){
        userAnswers.put(question, question.isCorrect(chosenOption));
    }
}

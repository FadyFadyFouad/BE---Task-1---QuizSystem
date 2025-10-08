package org.example;

import java.util.ArrayList;
import java.util.List;

public class Question {
    String QuestionText;
    List<String> Options = new ArrayList<>();
    int CorrectOption;

    public Question(String questionText, String option1, String option2, String option3, String option4, int correctOption) {
        QuestionText = questionText;
        Options.add(option1);
        Options.add(option2);
        Options.add(option3);
        Options.add(option4);
        CorrectOption = correctOption;
    }

    public String getQuestionText(){
        return QuestionText;
    }

    public List<String> getOptions() {
        return Options;
    }

    public int getCorrectOption() {
        return CorrectOption;
    }

    public boolean isCorrect(int selectedOption){
        return selectedOption == CorrectOption;
    }
}

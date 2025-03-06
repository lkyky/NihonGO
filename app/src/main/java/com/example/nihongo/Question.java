package com.example.nihongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {
    private String questionText;
    private List<String> options;
    private String correctAnswer;

    public Question() {}
    public Question(String questionText, List<String> options, String correctAnswer){
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("questionText", questionText);
        map.put("options", options);
        map.put("correctAnswer", correctAnswer);
        return map;
    }

    public String getQuestionText(){
        return questionText;
    }

    public List<String> getOptions(){
        return options;
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }
}

package com.example.chatuiplaygroud;


import com.google.gson.Gson;

public class QuestionAnswer {
    static public class Choices {
        public String A;
        public String B;
        public String C;
        public String D;
    }
    public String question;
    public Choices choices;
    public String answer;

    static public QuestionAnswer fromJson(String jsonText) {
        Gson gson = new Gson();
        return gson.fromJson(jsonText, QuestionAnswer.class);
    }
}

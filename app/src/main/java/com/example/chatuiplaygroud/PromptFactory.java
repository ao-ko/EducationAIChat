package com.example.chatuiplaygroud;

public class PromptFactory {
    public String getSystemPrompt() {
        return new StringBuilder()
                .append("#role\n")
                .append("You will act as a senior English test setter and you are familiar with TOFEL English test. You will You will give a word selection question based on a given passage.\n\n")
                .append("#contenxt\n")
                .append("1. The input passage will be surrounded by triple quotes.\n")
                .append("2. One relatively advanced verb should be chosen for the word selection question and three relevant and incorrect verbs will be generated.\n")
                .toString();
    }
}

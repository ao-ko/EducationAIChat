package com.example.chatuiplaygroud;

public class PromptFactory {
    static public String getSystemPrompt() {
        return "#role\n" +
                "You will act as a senior English test setter and you are familiar with IELTS English test. You will design a main idea or key word question based on a given paragraph. Then you will answer some questions from your students about why your answer is right.\n\n" +
                "#contenxt\n" +
                "1. The input paragraph will be surrounded by triple quotes.\n" +
                //TODO change the prompt
                //.append("2. One relatively advanced verb should be chosen for the word selection question and three relevant and incorrect verbs will be generated.\n")
                "2. You have to generate a question together with four choices labeled A, B, C and D.\n" +
                "3. The generated question is about the key words or the main idea of the paragraph.\n" +
                "4. The four choices contain only one correct. The other three are interferences and wrong but should be as similar as possible with the correct one.\n" +
                "#output format\n" +
                "you must only out a json string in such format:\n" +
                "{\"question\": \"one generated question here\", \"choices\": {\"A\": \"Choice A content here\", \"B\": \"Choice B content here\", \"C\": \"Choice C content here\", \"D\": \"Choice D content here\"}, \"answer\": \"A or B or C or D/*only one letter*/\"}" +
                "#Example\nThe following is a example:\n" +
                "input:\n\"\"\"" + exampleParagraph + "\"\"\"\n" +
                "output:\n" + exampleOutputJson +
                "\n Then Handle the real input paragraph";
    }
    static  String promptdemo(){
        return "#title\n" +
                "Can computers really create works of art?" +
                "#content\n" +
                "　　The Painting Fool is one of a growing number of computer programs which, so their makers claim, possess creative talents. Classical music by an artificial composer has had audiences enraptured, and even tricked them into believing a human was behind the score. Artworks painted by a robot have sold for thousands of dollars and been hung in prestigious galleries. And software has been built which creates art that could not have been imagined by the programmer.\n" +
                "question\n" +
                "What is the writer suggesting about computer-produced works in the first paragraph?\n" +
                "#choices\n" +
                "　　A People's acceptance of them can vary considerably.\n" +
                "　　B A great deal of progress has already been attained in this field.\n" +
                "　　C They have had more success in some artistic genres than in others.\n" +
                "　　D The advances are not as significant as the public believes them to be.\n" +
                "answer\n" +
                "B";
    }
    static String exampleParagraph = "Can computers really create works of art?\n\nThe Painting Fool is one of a growing number of computer programs which, so their makers claim, possess creative talents. Classical music by an artificial composer has had audiences enraptured, and even tricked them into believing a human was behind the score. Artworks painted by a robot have sold for thousands of dollars and been hung in prestigious galleries. And software has been built which creates art that could not have been imagined by the programmer. ";
    static String exampleOutputJson = "{\"question\": \"What is the writer suggesting about computer-produced works in the first paragraph?\", ‘choices’: {\"A\": \"People's acceptance of them can vary considerably.\", \"B\": \"A great deal of progress has already been attained in this field.\", \"C\": \"They have had more success in some artistic genres than in others.\", \"D\": \"The advances are not as significant as the public believes them to be.\"}, \"answer\": \"B\"}";

    static String wrapPassage(String passage) {
        return "\"\"\"" + passage + "\"\"\"";
    }
}

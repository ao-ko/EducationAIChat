package com.example.chatuiplaygroud;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuestionAnswerTest {

    static String testJson = "{\"question\": \"What is the writer suggesting about computer-produced works in the first paragraph?\",  \"choices\": {\"A\": \"People's acceptance of them can vary considerably.\", \"B\": \"A great deal of progress has already been attained in this field.\", \"C\": \"They have had more success in some artistic genres than in others.\", \"D\": \"The advances are not as significant as the public believes them to be.\"}, \"answer\": \"B\"}";
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fromJson() {
        QuestionAnswer questionAnswer = QuestionAnswer.fromJson(testJson);
        Assert.assertEquals("What is the writer suggesting about computer-produced works in the first paragraph?",questionAnswer.question);
        Assert.assertEquals("B",questionAnswer.answer);
    }
}
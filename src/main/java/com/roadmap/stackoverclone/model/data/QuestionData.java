package com.roadmap.stackoverclone.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class QuestionData {
    @JsonProperty("text")
    String text;

    @JsonProperty("text")
    ArrayList<AnswerData> answers;
}

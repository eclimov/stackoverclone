package com.roadmap.stackoverclone.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import org.springframework.validation.annotation.Validated;

@Validated
public class QuestionData {
    @JsonProperty("id")
    Long id = null;

    @JsonProperty("text")
    String text;

    @JsonProperty("text")
    ArrayList<AnswerData> answers;
}

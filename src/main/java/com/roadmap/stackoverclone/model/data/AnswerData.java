package com.roadmap.stackoverclone.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class AnswerData {
    @JsonProperty("id")
    Long id = null;

    @JsonProperty("text")
    String text;
}

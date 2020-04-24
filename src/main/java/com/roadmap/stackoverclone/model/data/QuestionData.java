package com.roadmap.stackoverclone.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
public class QuestionData {
    @JsonProperty("id")
    Long id = null;

    @JsonProperty("text")
    String text;

    public QuestionData setId(Long id) {
        this.id = id;
        return this;
    }

    public QuestionData setText(String text) {
        this.text = text;
        return this;
    }
}

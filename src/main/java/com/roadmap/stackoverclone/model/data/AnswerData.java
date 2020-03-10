package com.roadmap.stackoverclone.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
public class AnswerData {
    @JsonProperty("id")
    Long id = null;

    @JsonProperty("userId")
    Long userId = null;

    @JsonProperty("text")
    String text;

    public AnswerData setId(Long id) {
        this.id = id;
        return this;
    }

    public AnswerData setText(String text) {
        this.text = text;
        return this;
    }

    public AnswerData setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}

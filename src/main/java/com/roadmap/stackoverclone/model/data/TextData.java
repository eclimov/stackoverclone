package com.roadmap.stackoverclone.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
public class TextData {
    @JsonProperty("id")
    Long id = null;

    @JsonProperty("text")
    String text;

    public TextData setId(Long id) {
        this.id = id;
        return this;
    }

    public TextData setText(String text) {
        this.text = text;
        return this;
    }
}

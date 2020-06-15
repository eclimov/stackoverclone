package com.roadmap.stackoverclone.converter.entity;

import com.roadmap.stackoverclone.model.data.TextData;
import com.roadmap.stackoverclone.model.entity.Answer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverter implements Converter<Answer, TextData> {
    @Override
    public TextData convert(Answer answer) {
        return new TextData()
                .setId(answer.getId())
                .setText(answer.getText());
    }
}

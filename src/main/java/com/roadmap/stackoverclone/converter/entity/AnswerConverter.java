package com.roadmap.stackoverclone.converter.entity;

import com.roadmap.stackoverclone.model.data.AnswerData;
import com.roadmap.stackoverclone.model.entity.Answer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverter implements Converter<Answer, AnswerData> {
    @Override
    public AnswerData convert(Answer answer) {
        return new AnswerData()
                .setId(answer.getId())
                .setText(answer.getText())
                .setUserId(answer.getUser().getId());
    }
}

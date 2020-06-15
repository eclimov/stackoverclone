package com.roadmap.stackoverclone.converter.entity;

import com.roadmap.stackoverclone.model.data.TextData;
import com.roadmap.stackoverclone.model.entity.Question;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter implements Converter<Question, TextData>  {
    @Override
    public TextData convert(Question question) {
        return new TextData()
                .setText(question.getText())
                .setId(question.getId());
    }
}

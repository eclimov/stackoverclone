package com.roadmap.stackoverclone.converter.entity;

import com.roadmap.stackoverclone.model.data.QuestionData;
import com.roadmap.stackoverclone.model.entity.Question;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter implements Converter<Question, QuestionData>  {
    @Override
    public QuestionData convert(Question question) {
        return new QuestionData()
                .setText(question.getText())
                .setId(question.getId());
    }
}

package com.roadmap.stackoverclone.converter.data;

import com.roadmap.stackoverclone.model.data.TextData;
import com.roadmap.stackoverclone.model.entity.Question;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QuestionDataConverter implements Converter<TextData, Question> {
    @Override
    public Question convert(TextData questionData) {
        return (Question) new Question()
                .setText(questionData.getText());
    }
}

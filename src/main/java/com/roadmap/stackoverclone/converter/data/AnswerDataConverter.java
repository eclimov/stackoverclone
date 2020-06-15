package com.roadmap.stackoverclone.converter.data;

import com.roadmap.stackoverclone.model.data.TextData;
import com.roadmap.stackoverclone.model.entity.Answer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AnswerDataConverter implements Converter<TextData, Answer> {
    @Override
    public Answer convert(TextData answerData) {
        return (Answer) new Answer()
                .setText(answerData.getText());
    }
}

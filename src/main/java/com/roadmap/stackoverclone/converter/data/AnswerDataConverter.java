package com.roadmap.stackoverclone.converter.data;

import com.roadmap.stackoverclone.model.data.AnswerData;
import com.roadmap.stackoverclone.model.entity.Answer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AnswerDataConverter implements Converter<AnswerData, Answer> {
    @Override
    public Answer convert(AnswerData answerData) {
        return (Answer) new Answer()
                .setText(answerData.getText());
    }
}

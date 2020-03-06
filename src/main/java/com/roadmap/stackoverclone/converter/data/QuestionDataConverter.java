package com.roadmap.stackoverclone.converter.data;

import com.roadmap.stackoverclone.model.data.QuestionData;
import com.roadmap.stackoverclone.model.entity.Question;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QuestionDataConverter implements Converter<QuestionData, Question> {
    @Override
    public Question convert(QuestionData questionData) {
        return (Question) new Question()
                // set user in service
                .setText(questionData.getText());
    }
}

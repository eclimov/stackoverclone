package com.roadmap.stackoverclone.converter.data;

import com.roadmap.stackoverclone.model.data.TextData;
import com.roadmap.stackoverclone.model.entity.Question;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionDataConverterTest {
    @Test
    public void convert () {
        QuestionDataConverter questionDataConverter = new QuestionDataConverter();

        String text = "some question text ?";

        TextData questionData = (new TextData())
                .setText(text);
        Question question = questionDataConverter.convert(questionData);

        Assert.assertEquals(question.getText(), text);
    }
}

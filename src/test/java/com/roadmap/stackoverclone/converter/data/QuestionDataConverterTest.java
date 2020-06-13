package com.roadmap.stackoverclone.converter.data;

import com.roadmap.stackoverclone.model.data.QuestionData;
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

        QuestionData questionData = (new QuestionData())
                .setText(text);
        Question question = questionDataConverter.convert(questionData);

        Assert.assertEquals(question.getText(), text);
    }
}

package com.roadmap.stackoverclone.converter.entity;

import com.roadmap.stackoverclone.model.data.TextData;
import com.roadmap.stackoverclone.model.entity.Question;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
public class QuestionConverterTest {
    @Test
    public void convert () {
        QuestionConverter questionConverter = new QuestionConverter();

        Long id = 3L;
        String text = "some question text ?";

        Question question = (new Question());
        ReflectionTestUtils.setField(question, "id", id);
        question.setText(text);

        TextData questionData = questionConverter.convert(question);

        Assert.assertEquals(questionData.getId(), id);
        Assert.assertEquals(questionData.getText(), text);
    }
}

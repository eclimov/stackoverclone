package com.roadmap.stackoverclone.converter.data;

import com.roadmap.stackoverclone.model.data.TextData;
import com.roadmap.stackoverclone.model.entity.Answer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnswerDataConverterTest {
    @Test
    public void convert () {
        AnswerDataConverter answerDataConverter = new AnswerDataConverter();

        String text = "some answer text";

        TextData answerData = (new TextData())
                .setText(text);
        Answer answer = answerDataConverter.convert(answerData);

        Assert.assertEquals(answer.getText(), text);
    }
}

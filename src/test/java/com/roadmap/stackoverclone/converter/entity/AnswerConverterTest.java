package com.roadmap.stackoverclone.converter.entity;

import com.roadmap.stackoverclone.model.data.AnswerData;
import com.roadmap.stackoverclone.model.entity.Answer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
public class AnswerConverterTest {
    @Test
    public void convert () {
        AnswerConverter answerConverter = new AnswerConverter();

        Long id = 3L;
        String text = "some answer text";

        Answer answer = (new Answer());
        ReflectionTestUtils.setField(answer, "id", id);
        answer.setText(text);

        AnswerData answerData = answerConverter.convert(answer);

        Assert.assertEquals(answerData.getId(), id);
        Assert.assertEquals(answerData.getText(), text);
    }
}

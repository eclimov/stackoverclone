package com.roadmap.stackoverclone.converter.entity;

import com.roadmap.stackoverclone.model.data.UserData;
import com.roadmap.stackoverclone.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
public class UserConverterTest {
    @Test
    public void convert () {
        UserConverter userConverter = new UserConverter();

        Long id = 3L;
        String username = "some answer text";

        User user = (new User());
        ReflectionTestUtils.setField(user, "id", id);
        user.setUsername(username);

        UserData userData = userConverter.convert(user);

        Assert.assertEquals(userData.getId(), id);
        Assert.assertEquals(userData.getUsername(), username);
    }
}

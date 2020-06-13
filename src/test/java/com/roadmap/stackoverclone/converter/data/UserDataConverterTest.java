package com.roadmap.stackoverclone.converter.data;

import com.roadmap.stackoverclone.model.data.UserData;
import com.roadmap.stackoverclone.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDataConverterTest {
    @Test
    public void convert () {
        UserDataConverter userDataConverter = new UserDataConverter();

        String username = "myUsername";

        UserData userData = (new UserData())
                .setUsername(username);
        User user = userDataConverter.convert(userData);

        Assert.assertEquals(user.getUsername(), username);
    }
}

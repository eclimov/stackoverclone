package com.roadmap.stackoverclone.converter.data;

import com.roadmap.stackoverclone.model.data.UserData;
import com.roadmap.stackoverclone.model.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDataConverter implements Converter<UserData, User> {

  @Override
  public User convert(UserData userData) {
    return new User()
        .setUsername(userData.getUsername());
  }
}

package com.roadmap.stackoverclone.converter.entity;

import com.roadmap.stackoverclone.model.data.UserData;
import com.roadmap.stackoverclone.model.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserData> {

  @Override
  public UserData convert(User user) {
    return new UserData()
        .setId(user.getId())
        .setUsername(user.getUsername());
  }
}

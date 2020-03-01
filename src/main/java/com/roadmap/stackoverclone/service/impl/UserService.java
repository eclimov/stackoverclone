package com.roadmap.stackoverclone.service.impl;

import com.roadmap.stackoverclone.exception.ResourceNotFoundException;
import com.roadmap.stackoverclone.model.data.UserData;
import com.roadmap.stackoverclone.model.entity.User;
import com.roadmap.stackoverclone.repository.UserRepository;
import com.roadmap.stackoverclone.service.IUserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ConversionService conversionService;

  @Override
  public List<UserData> get() {
    return  userRepository
        .findAll().stream()
        .map(e -> conversionService.convert(e, UserData.class))
        .collect(Collectors.toList());
  }

  @Override
  public UserData create(UserData source) {
    User user = conversionService.convert(source, User.class);
    userRepository.save(user);
    return source.setId(user.getId());
  }

  @Override
  public boolean delete(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      userRepository.delete(user.get());
      return true;
    }

    return false;
  }

  @Override
  public UserData findById(Long id) {
    return conversionService.convert(
        userRepository.findById(id).orElse(null),
        UserData.class
    );
  }

  @Override
  public UserData update(Long id, UserData source) {
    User user =  userRepository.findOneById(id).orElseThrow(ResourceNotFoundException::new);
    user.setName(source.getName());
    // TODO: potential improvement - user.setUpdateDateTime(Timestamp.valueOf(LocalDateTime.now()));
    userRepository.save(user);

    return source
        .setId(user.getId());
  }
}

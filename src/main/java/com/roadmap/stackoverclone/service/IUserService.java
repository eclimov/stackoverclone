package com.roadmap.stackoverclone.service;

import com.roadmap.stackoverclone.model.data.UserData;
import com.roadmap.stackoverclone.model.data.UserStatisticsDataInterface;

import java.util.List;

public interface IUserService {
  List<UserData> get();

  UserData create(UserData source);

  boolean delete(Long id);

  UserData findById(Long id);

  UserData update(Long id, UserData source);

  UserStatisticsDataInterface getUserStatistics(Long userId);
}

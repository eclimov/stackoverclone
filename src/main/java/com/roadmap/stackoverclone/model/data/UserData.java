package com.roadmap.stackoverclone.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
public class UserData {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String username = null;

  public UserData setId(Long id) {
    this.id = id;
    return this;
  }

  public UserData setUsername(String username) {
    this.username = username;
    return this;
  }
}

package com.roadmap.stackoverclone.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Builder
@Getter
@Validated
public class UserData {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;
}

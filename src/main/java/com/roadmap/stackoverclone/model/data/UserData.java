package com.roadmap.stackoverclone.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Builder // Use Lombok builder vs chained setters (to avoid calling 'builder()' and 'build()' methods) ?
@Getter
@Setter
@Validated
public class UserData {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;
}

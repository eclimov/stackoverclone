package com.roadmap.stackoverclone.model.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {
  @ManyToOne
  @JoinColumn(name="user_id", nullable=false)
  private User user;

  public Question setUser(User user) {
    this.user = user;
    return this;
  }

  @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
  private Set<Answer> answers = new HashSet<>();

  public Question addAnswer(Answer answer) {
    this.answers.add(answer);
    return this;
  }
}

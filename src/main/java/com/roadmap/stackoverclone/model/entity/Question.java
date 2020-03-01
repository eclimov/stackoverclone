package com.roadmap.stackoverclone.model.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "questions")
public class Question extends BaseTextEntity {
  @ManyToOne
  @JoinColumn(name="user_id", nullable = false)
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

package com.roadmap.stackoverclone.model.entity;

import com.roadmap.stackoverclone.constant.EntityConstants;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = EntityConstants.TYPE_QUESTIONS)
public class Question extends BaseTextEntity {
  @OneToMany(mappedBy = "question", orphanRemoval = true, cascade = CascadeType.PERSIST)
  private Set<RatingQuestion> ratings = new HashSet<>();

  @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
  private Set<Answer> answers = new HashSet<>();

  public Question addAnswer(Answer answer) {
    this.answers.add(answer);
    return this;
  }
}

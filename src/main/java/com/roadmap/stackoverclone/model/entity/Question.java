package com.roadmap.stackoverclone.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {
  @ManyToOne
  @JoinColumn(name="user_id", nullable=false)
  private User user;
}

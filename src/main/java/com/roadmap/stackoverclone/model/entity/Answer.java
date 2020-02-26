package com.roadmap.stackoverclone.model.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "answers")
public class Answer extends BaseTextEntity {
    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;

    public Answer setQuestion(Question question) {
        this.question = question;
        return this;
    }

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Answer setUser(User user) {
        this.user = user;
        return this;
    }
}

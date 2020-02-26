package com.roadmap.stackoverclone.model.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "answers")
public class Answer extends BaseEntity {
    @Column(name = "text")
    private String text;

    public Answer setText(String text) {
        this.text = text;
        return this;
    }

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

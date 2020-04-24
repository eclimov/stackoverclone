package com.roadmap.stackoverclone.model.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "rating_question")
public class RatingQuestion extends BaseEntity {
    @Column(name = "value")
    private int value;

    @ManyToOne
    @JoinColumn(name="question_id", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public RatingQuestion setValue(int value) {
        this.value = value;
        return this;
    }

    public RatingQuestion setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public RatingQuestion setUser(User user) {
        this.user = user;
        return this;
    }
}
